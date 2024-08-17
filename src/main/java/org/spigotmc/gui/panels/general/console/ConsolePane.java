package org.spigotmc.gui.panels.general.console;

import org.spigotmc.builder.Builder;
import org.spigotmc.gui.components.TextAreaOutputStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.io.PrintStream;
import java.util.List;

public class ConsolePane extends JScrollPane {

    private JTextArea consoleArea;

    public ConsolePane() {
        initComponents();
    }

    private void initComponents() {
        this.consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setColumns(1);
        consoleArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        consoleArea.setLineWrap(true);
        consoleArea.setRows(5);
        consoleArea.setWrapStyleWord(true);
        consoleArea.setMargin(new Insets(12, 12, 12, 12));
        setViewportView(consoleArea);
    }

    public void updateConsoleAreaText(List<String> lines) {
        consoleArea.setText("");
        for (String line : lines) {
            consoleArea.append(line + "\n");
        }
    }

    public String getConsoleText() {
        return consoleArea.getText();
    }

    public void updateStreams() {
        PrintStream outputStream = new PrintStream(new TextAreaOutputStream(consoleArea), true);
        PrintStream errorStream = new PrintStream(new TextAreaOutputStream(consoleArea, Color.RED), true);
        Builder.logOutput(outputStream, errorStream);
    }
}
