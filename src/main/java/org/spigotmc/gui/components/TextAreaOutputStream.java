package org.spigotmc.gui.components;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TextAreaOutputStream extends ByteArrayOutputStream
{

    private final String EOL = System.getProperty( "line.separator" );
    private final StringBuffer buffer = new StringBuffer( 80 );

    private final JTextArea textArea;
    private final Document document;
    private final SimpleAttributeSet attributes;

    public TextAreaOutputStream(JTextArea output)
    {
        this.textArea = output;
        this.document = output.getDocument();
        this.attributes = new SimpleAttributeSet();
    }

    public TextAreaOutputStream(JTextArea output, Color color)
    {
        this(output);
        StyleConstants.setForeground( attributes, color );
    }

    @Override
    public void flush()
    {
        String message = toString();
        if ( message.isEmpty() ) return;

        message = message.replaceAll("\u001B\\[[;\\d]*m", "");

        append( message );
        reset();
    }

    private void append(String message)
    {
        if ( document.getLength() == 0 )
        {
            buffer.setLength( 0 );
        }

        if ( EOL.equals( message ) )
        {
            buffer.append( message );
        } else
        {
            buffer.append( message );
            writeBuffer();
        }
    }

    private void writeBuffer()
    {
        try
        {
            int offset = document.getLength();
            document.insertString( offset, buffer.toString(), attributes );
            textArea.setCaretPosition( document.getLength() );
        } catch ( BadLocationException ignored )
        {
            // Ignored
        }

        buffer.setLength( 0 );
    }
}
