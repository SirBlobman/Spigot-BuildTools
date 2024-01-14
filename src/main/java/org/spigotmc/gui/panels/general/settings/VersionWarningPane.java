package org.spigotmc.gui.panels.general.settings;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Insets;

public class VersionWarningPane extends JScrollPane {

    private JTextPane warningPane;

    public VersionWarningPane() {
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        initComponents();
    }

    private void initComponents() {
        this.warningPane = new JTextPane();
        warningPane.setEditable(false);
        warningPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        warningPane.setText("Please wait while BuildTools loads the necessary information.");
        warningPane.setFocusable(false);
        warningPane.setMargin(new Insets(0, 0, 0, 0));
        setViewportView(warningPane);
    }

    public void setWarningText(String line, Color color) {
        if (line == null) {
            warningPane.setVisible(false);
            return;
        }

        warningPane.setText(line);
        warningPane.setForeground(color);
        warningPane.setVisible(true);
    }

}
