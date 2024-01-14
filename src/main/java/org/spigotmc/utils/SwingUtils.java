package org.spigotmc.utils;

import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import org.spigotmc.gui.BuildToolsGui;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Lockable;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.modals.MessageModal;

public final class SwingUtils {

    private static final ImageIcon ICON = ThreadLocalRandom.current().nextInt(1000) == 0 ? Constants.LOGO_EASTER_EGG : Constants.LOGO;
    private static Theme theme = Theme.LIGHT;

    private SwingUtils() {

    }

    public static void applyIcon(JFrame frame) {
        frame.setIconImage(ICON.getImage());
    }

    public static void open(String directory) {
        runDesktopOperation(() -> {
            try {
                Desktop.getDesktop().open(new File(directory));
            } catch (IOException e) {
                Logger.getLogger(BuildToolsGui.class.getName()).log(Level.SEVERE, null, e);
            }
        }, () -> MessageModal.displayWarning("Desktop is not supported!"));
    }

    public static void browse(String link) {
        runDesktopOperation(() -> {
            try {
                Desktop.getDesktop().browse(URI.create(link));
            } catch (IOException e) {
                Logger.getLogger(BuildToolsGui.class.getName()).log(Level.SEVERE, null, e);
            }
        }, () -> MessageModal.displayWarning("Desktop is not supported!"));
    }

    public static void copyToClipboard(final String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }

    public static void copyToClipboard(File file) {
        if (!file.exists()) {
            return;
        }

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {

            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            //handle exception
        }

        String fileContent = contentBuilder.toString();

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(fileContent), null);
    }

    private static void runDesktopOperation(Runnable desktopOperation, Runnable display) {
        if (Desktop.isDesktopSupported()) {
            desktopOperation.run();
            return;
        }

        display.run();
    }

    public static void buttonCooldown(AbstractButton button, final int seconds, String newText) {
        String oldText = button.getText();

        button.setText(newText);
        button.setEnabled(false);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setText(oldText);
                button.setEnabled(true);
            }
        }, (seconds * 1000L));
    }

    public static void validatePath(JTextField textField) {
        // Use #isBlank() whenever this project updates to JDK 11 or greater.
        if (textField.getText().isEmpty()) {
            textField.setBorder(null);
            textField.setBackground(null);
            textField.updateUI();
            return;
        }

        File file = new File(textField.getText());
        if (!file.exists()) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 0, 0, 64));
        } else if (!Utils.isValidPath(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.ORANGE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 136, 0, 64));
        } else {
            textField.setBorder(null);
            textField.setBackground(null);
        }

        textField.updateUI();
    }

    public static void validateSpigotPR(JTextField textField) {
        // Use #isBlank() whenever this project updates to JDK 11 or greater.
        if (textField.getText().isEmpty()) {
            textField.setBorder(null);
            textField.setBackground(null);
            textField.updateUI();
            return;
        }

        if (Utils.isNumeric(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.ORANGE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 136, 0, 64));
        } else if (!Utils.isValidSpigotPR(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 0, 0, 64));
        } else {
            textField.setBorder(null);
            textField.setBackground(null);
        }

        textField.updateUI();
    }

    public static void validateBukkitPR(JTextField textField) {
        // Use #isBlank() whenever this project updates to JDK 11 or greater.
        if (textField.getText().isEmpty()) {
            textField.setBorder(null);
            textField.setBackground(null);
            textField.updateUI();
            return;
        }

        if (Utils.isNumeric(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.ORANGE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 136, 0, 64));
        } else if (!Utils.isValidBukkitPR(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 0, 0, 64));
        } else {
            textField.setBorder(null);
            textField.setBackground(null);
        }

        textField.updateUI();
    }

    public static void validateCraftbukkitPR(JTextField textField) {
        // Use #isBlank() whenever this project updates to JDK 11 or greater.
        if (textField.getText().isEmpty()) {
            textField.setBorder(null);
            textField.setBackground(null);
            textField.updateUI();
            return;
        }

        if (Utils.isNumeric(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.ORANGE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 136, 0, 64));
        } else if (!Utils.isValidCraftbukkitPR(textField.getText())) {
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            ));
            textField.setBackground(new Color(255, 0, 0, 64));
        } else {
            textField.setBorder(null);
            textField.setBackground(null);
        }

        textField.updateUI();
    }

    public static JButton buildRedirectButton(final String label, final String link) {
        final JButton redirectButton = new JButton(label);
        redirectButton.setForeground(Constants.ACCENT_COLOR);
        redirectButton.setBorder(null);
        redirectButton.setBorderPainted(false);
        redirectButton.setContentAreaFilled(false);
        redirectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        redirectButton.setMargin(new Insets(0, 0, 0, 0));
        redirectButton.addActionListener((ActionEvent event) -> SwingUtils.browse(link));

        return redirectButton;
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void toggleLockComponents(final Container parent, final Lockable.LockReason reason) {
        if (parent instanceof Lockable) {
            ((Lockable) parent).onLockToggle(reason);
        }

        toggleLockComponents0(parent, reason);
    }

    private static void toggleLockComponents0(final Container parent, final Lockable.LockReason reason) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Lockable) {
                ((Lockable) component).onLockToggle(reason);
            }

            if (component instanceof Container) {
                toggleLockComponents0((Container) component, reason);
            }
        }
    }

    public static void changeTheme(final Container parent, final Theme theme) {
        SwingUtils.theme = theme;
        if (parent instanceof Themeable) {
            ((Themeable) parent).onThemeChange(theme);
        }

        changeTheme0(parent, theme);
    }

    private static void changeTheme0(final Container parent, final Theme theme) {
        for (Component component : parent.getComponents()) {
            if (component instanceof Themeable) {
                ((Themeable) component).onThemeChange(theme);
            }

            if (component instanceof Container) {
                changeTheme0((Container) component, theme);
            }
        }
    }

    public static void applyInitialTheme() {
        String hex = Utils.getHexFromColor(Constants.ACCENT_COLOR);

        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", hex));

        // Checkbox - General
        UIManager.put("CheckBox.icon.checkmarkColor", Color.WHITE);
        UIManager.put("CheckBox.icon.focusColor", Constants.ACCENT_COLOR);

        UIManager.put("CheckBox.icon.hoverBorderColor", Constants.ACCENT_COLOR);
        UIManager.put("CheckBox.icon.hoverBackground", Constants.ACCENT_COLOR_DESATURATED);

        // Checkbox - Not Selected
        UIManager.put("CheckBox.icon.borderColor", Constants.ACCENT_COLOR);

        // Checkbox - Selected
        UIManager.put("CheckBox.icon.selectedBorderColor", Constants.ACCENT_COLOR);
        UIManager.put("CheckBox.icon.selectedBackground", Constants.ACCENT_COLOR);
        UIManager.put("CheckBox.icon.focusedSelectedBorderColor", Constants.ACCENT_COLOR);
        UIManager.put("CheckBox.icon.focusedSelectedBackground", Constants.ACCENT_COLOR);
    }

}
