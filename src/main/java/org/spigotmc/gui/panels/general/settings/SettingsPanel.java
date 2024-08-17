package org.spigotmc.gui.panels.general.settings;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import org.spigotmc.builder.Builder;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Lockable;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.components.PlaceholderTextField;
import org.spigotmc.gui.data.BuildData;
import org.spigotmc.gui.data.BuildSettings;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.gui.panels.general.console.ConsolePane;
import org.spigotmc.utils.Constants;
import org.spigotmc.utils.SwingUtils;

public class SettingsPanel extends JPanel implements Lockable, Themeable {

    private JComboBox<String> versionComboBox;
    private PlaceholderTextField outputDirField;
    private PlaceholderTextField finalNameField;
    private JButton openDirButton;
    private JButton outputSelectionButton;

    private final BuildData buildData;
    private final BuildSettings buildSettings;
    private final ConsolePane consolePane;
    private final VersionWarningPane versionWarningPane;

    public SettingsPanel(BuildData buildData, BuildSettings buildSettings, final ConsolePane consolePane, final VersionWarningPane versionWarningPane) {
        this.buildData = buildData;
        this.buildSettings = buildSettings;
        this.consolePane = consolePane;
        this.versionWarningPane = versionWarningPane;

        initComponents();
    }

    private void initComponents() {
        this.versionComboBox = new JComboBox<>();
        versionComboBox.addItem("Loading...");
        versionComboBox.setFont(Constants.GENERAL_FONT);
        versionComboBox.addActionListener(this::versionComboBoxActionPerformed);
        versionComboBox.setToolTipText("The version you want to build.");

        final JLabel selectLabel = new JLabel("Select Version");
        final JLabel finalLabel = new JLabel("Final Name (Optional)");

        this.finalNameField = new PlaceholderTextField();
        finalNameField.setPlaceholder("server.jar");
        finalNameField.setFont(Constants.GENERAL_FONT);
        finalNameField.setToolTipText("The name of the final jar.");
        finalNameField.setMargin(new Insets(8, 8, 8, 8));
        finalNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                finalNameFieldKeyReleased(keyEvent);
            }
        });
        // TODO: Find a way to detect differences between mouse focus and keyboard focus.
//        finalNameField.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                textFieldFocusGained(e);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//
//            }
//        });

        final JLabel outputDirLabel = new JLabel("Output Directory (Optional)");
        this.outputDirField = new PlaceholderTextField();
        outputDirField.setPlaceholder(Builder.CWD.toString());
        outputDirField.setFont(Constants.GENERAL_FONT);
        outputDirField.setToolTipText("The directory where the final jar will be copied to.");
        outputDirField.setMargin(new Insets(8, 8, 8, 8));
        outputDirField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                outputDirFieldKeyReleased(event);
            }
        });
//        outputDirField.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                textFieldFocusGained(e);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//
//            }
//        });

        this.outputSelectionButton = new JButton("Select");
        outputSelectionButton.setToolTipText("Select the output directory.");
        outputSelectionButton.setMargin(new Insets(8, 16, 8, 16));
        outputSelectionButton.addActionListener(this::outputSelectionButtonActionPerformed);
        outputSelectionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.openDirButton = new JButton();
        openDirButton.setToolTipText("Open the output directory.");
        openDirButton.setMargin(new Insets(8, 16, 8, 16));
        openDirButton.addActionListener(this::openDirButtonActionPerformed);
        openDirButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        final GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).
                addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(versionComboBox, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
                        .addComponent(selectLabel))
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING
                        ).addComponent(finalNameField, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
                        .addComponent(finalLabel)).addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(outputDirField, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(outputSelectionButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(openDirButton))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(outputDirLabel)
                            .addGap(0, 0, Short.MAX_VALUE)))));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(finalLabel)
                            .addComponent(selectLabel))
                        .addComponent(outputDirLabel))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(versionComboBox, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(finalNameField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(outputDirField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(openDirButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                        .addComponent(outputSelectionButton, GroupLayout.PREFERRED_SIZE, 32,
                            GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0)));
    }

    @SuppressWarnings("unchecked")
    private void versionComboBoxActionPerformed(final ActionEvent event) {
        final JComboBox<String> box = (JComboBox<String>) event.getSource();

        final String selected = (String) box.getSelectedItem();
        if (selected == null) {
            return;
        }

        String name;
        switch (selected) {
            case "experimental":
                versionWarningPane.setWarningText(Constants.EXPERIMENTAL_WARNING, Constants.EXPERIMENTAL_WARNING_TEXT_PRIMARY);
                finalNameField.setPlaceholder(Constants.VERSION_SCHEME_EXPERIMENTAL);
                break;
            case "latest":
                versionWarningPane.setWarningText(Constants.LATEST_WARNING, null);
                name = Constants.VERSION_SCHEME_NORMAL.replace("%version%", buildData.getLatestVersion());
                finalNameField.setPlaceholder(name);
                break;
            default:
                versionWarningPane.setWarningText(null, null);
                name = Constants.VERSION_SCHEME_NORMAL.replace("%version%", selected);
                finalNameField.setPlaceholder(name);
                break;
        }

        finalNameField.updateUI();

        buildSettings.setVersion(selected);
        if (!buildSettings.isOverrideJavaExecutable()) {
            buildData.updateJavaExecutable(buildSettings, false);
        }

        consolePane.updateConsoleAreaText(buildData.generatePreCompilationText(buildSettings));
    }

//    private void textFieldFocusGained(FocusEvent event) {
//        JTextField textField = (JTextField) event.getSource();
//        textField.selectAll();
//    }

    private void finalNameFieldKeyReleased(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();
        buildSettings.setFinalName(field.getText());
        consolePane.updateConsoleAreaText(buildData.generatePreCompilationText(buildSettings));
    }

    private void outputDirFieldKeyReleased(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();
        buildSettings.setOutputDirectory(field.getText());
        consolePane.updateConsoleAreaText(buildData.generatePreCompilationText(buildSettings));

        SwingUtils.validatePath(field);
    }

    private void outputSelectionButtonActionPerformed(final ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.OPEN_DIALOG) {
            final File selection = chooser.getSelectedFile();
            outputDirField.setText(selection.getAbsolutePath());
            buildSettings.setOutputDirectory(selection.getAbsolutePath());
            consolePane.updateConsoleAreaText(buildData.generatePreCompilationText(buildSettings));
        }

        SwingUtils.validatePath(outputDirField);
    }

    private void openDirButtonActionPerformed(final ActionEvent event) {
        if (!buildSettings.getWorkDirectory().isEmpty() && buildSettings.getOutputDirectory().isEmpty()) {
            SwingUtils.open(buildSettings.getWorkDirectory());
        } else if (!buildSettings.getOutputDirectory().isEmpty()) {
            SwingUtils.open(buildSettings.getOutputDirectory());
        } else {
            SwingUtils.open(Builder.CWD.toString());
        }
    }

    public void setVersions(List<String> versions) {
        versionComboBox.removeAllItems();
        for (String version : versions) {
            versionComboBox.addItem(version);
        }
        versionComboBox.setSelectedIndex(1);
    }

    public PlaceholderTextField getOutputDirField() {
        return outputDirField;
    }

    @Override
    public void onLockToggle(LockReason reason) {
        versionComboBox.setEnabled(!versionComboBox.isEnabled());
        finalNameField.setEnabled(!finalNameField.isEnabled());
        outputDirField.setEnabled(!outputDirField.isEnabled());
        outputSelectionButton.setEnabled(!outputSelectionButton.isEnabled());

        if (versionComboBox.isEnabled()) {
            versionComboBox.requestFocus();
        }
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack themePack = ThemePack.fromTheme(theme);
        final Color borderColor = (Color) themePack.getAsset(ThemePack.Asset.BORDER_COLOR);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        final ImageIcon folderIcon = (ImageIcon) themePack.getAsset(ThemePack.Asset.FOLDER);
        openDirButton.setIcon(folderIcon);
    }
}
