package org.spigotmc.gui.modals;

import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import lombok.SneakyThrows;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.utils.Constants;
import org.spigotmc.utils.SwingUtils;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class MessageModal extends JFrame implements Themeable {

    private JTextPane messagePane;

    private final ImageIcon icon;
    private final String labelText;

    protected MessageModal(final String title, final ImageIcon icon, final String labelText, final String message) {
        this.icon = icon;
        this.labelText = labelText;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        setSize(new Dimension(600, 500));
        SwingUtils.applyIcon(this);

        initComponents();
        messagePane.setText(message);
        messagePane.setCaretPosition(0);

        SwingUtils.changeTheme(this, SwingUtils.getTheme());
    }

    protected void initComponents() {
        final JScrollPane messageScrollPane = new JScrollPane();

        messagePane = new JTextPane();
        messagePane.setEditable(false);
        messagePane.setContentType("text/html");
        messagePane.addHyperlinkListener(this::hyperLinkClick);
        messageScrollPane.setViewportView(messagePane);

        final JLabel label = new JLabel(labelText);
        label.setIcon(icon);
        label.setIconTextGap(12);

        final JPanel labelPanel = new JPanel();
        GroupLayout labelLayout = new GroupLayout(labelPanel);
        labelPanel.setLayout(labelLayout);
        labelLayout.setHorizontalGroup(
                labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(labelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addContainerGap(492, Short.MAX_VALUE)));
        labelLayout.setVerticalGroup(
                labelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, labelLayout.createSequentialGroup()
                                .addGap(0, 0, 0).addComponent(label)));

        final JButton closeButton = new JButton("Close");
        closeButton.setMargin(new Insets(8, 16, 8, 16));
        closeButton.addActionListener(this::closeButtonActionPerformed);

        final JPanel buttonPanel = new JPanel();
        GroupLayout buttonLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonLayout);
        buttonLayout.setHorizontalGroup(
                buttonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(buttonLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        buttonLayout.setVerticalGroup(buttonLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(closeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup().addContainerGap().addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(labelPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(messageScrollPane)
                                        .addComponent(buttonPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
                        .addComponent(labelPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(messageScrollPane, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        pack();
    }

    private void hyperLinkClick(HyperlinkEvent event) {
        if (!HyperlinkEvent.EventType.ACTIVATED.equals(event.getEventType())) {
            return;
        }

        final String url = event.getURL().toString();
        if (url.startsWith("file:/")) {
            SwingUtils.open(url.replace("file:", ""));
        } else {
            SwingUtils.browse(event.getURL().toString());
        }

    }

    private void closeButtonActionPerformed(ActionEvent event) {
        dispose();
    }

    @Override
    @SneakyThrows
    public void onThemeChange(Theme theme) {
        final ThemePack pack = ThemePack.fromTheme(theme);
        final String lookAndFeelClass = (String) pack.getAsset(ThemePack.Asset.LOOK_AND_FEEL_CLASS);

        UIManager.setLookAndFeel(lookAndFeelClass);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static void displayError(final String message) {
        displayMessage("Error", Constants.ERROR, message);
    }

    public static void displayWarning(final String message) {
        displayMessage("Warning", Constants.WARNING, message);
    }

    public static void displaySuccess(final String message) {
        displayMessage("Success", Constants.SUCCESS, message);
    }

    private static void displayMessage(final String title, final ImageIcon icon, String message) {
        final MessageModal modal = new MessageModal("BuildTools " + title, icon, title.toUpperCase(), message);
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }
}
