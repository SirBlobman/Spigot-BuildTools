package org.spigotmc.gui.panels.options;

import com.jeff_media.javafinder.JavaFinder;
import com.jeff_media.javafinder.JavaInstallation;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import org.spigotmc.builder.Builder;
import org.spigotmc.gui.components.CustomFocusTraversalPolicy;
import org.spigotmc.gui.components.PlaceholderTextField;
import org.spigotmc.gui.data.BuildData;
import org.spigotmc.gui.data.BuildSettings;
import org.spigotmc.gui.modals.SelectJavaModal;
import org.spigotmc.gui.panels.general.GeneralPanel;
import org.spigotmc.gui.panels.options.flags.FlagCompileChangesPanel;
import org.spigotmc.gui.panels.options.flags.FlagDontUpdatePanel;
import org.spigotmc.gui.panels.options.flags.FlagGenerateSourcesPanel;
import org.spigotmc.gui.panels.options.flags.FlagJavadocsPanel;
import org.spigotmc.gui.panels.options.flags.FlagRemapPanel;
import org.spigotmc.gui.panels.options.flags.FlagSkipHTTPSCheckPanel;
import org.spigotmc.gui.panels.options.flags.FlagSkipJavaVersionCheckPanel;
import org.spigotmc.utils.Constants;
import org.spigotmc.utils.SwingUtils;
import org.spigotmc.utils.Utils;

public class OptionsPanel extends JPanel {

    private CustomFocusTraversalPolicy traversalPolicy;

    private JTextField javaExecutableField;
    private JButton detectJavaButton;
    private JButton javaSelectionButton;
    private JCheckBox compileNoneCheckbox;
    private JCheckBox compileSpigotCheckbox;
    private JCheckBox compileCraftbukkitCheckbox;

    private final GeneralPanel panel;
    private final BuildData buildData;
    private final BuildSettings buildSettings;

    public OptionsPanel(GeneralPanel generalPanel, final BuildData buildData, final BuildSettings buildSettings) {
        this.buildData = buildData;
        this.buildSettings = buildSettings;
        this.panel = generalPanel;

        setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        setPreferredSize(new Dimension(1200, 700));

        initComponents();
    }

    private void initComponents() {
        final JLabel buildFlagsLabel = new JLabel("<html><b>Build Flags</b></html>");
        final JLabel pullRequestsLabel = new JLabel("<html><b>Pull Request IDs (Optional)</b></html>");
        final JLabel compilationOptionsLabel = new JLabel("<html><b>Compilation Options</b></html>");
        final JLabel javaConfigurationLabel = new JLabel("<html><b>Additional Configuration</b></html>");

        PlaceholderTextField spigotPrTextField = new PlaceholderTextField();
        spigotPrTextField.setPlaceholder("SPIGOT PR:ID");
        spigotPrTextField.setToolTipText("Example: SPIGOT:42");
        spigotPrTextField.setMargin(new Insets(8, 8, 8, 8));
        spigotPrTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                spigotPrFieldKeyReleased(keyEvent);
            }
        });

        PlaceholderTextField bukkitPrTextField = new PlaceholderTextField();
        bukkitPrTextField.setPlaceholder("BUKKIT PR:ID");
        bukkitPrTextField.setToolTipText("Example: BUKKIT:28");
        bukkitPrTextField.setMargin(new Insets(8, 8, 8, 8));
        bukkitPrTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                bukkitPrFieldKeyEvent(keyEvent);
            }
        });

        PlaceholderTextField craftbukkitPrTextField = new PlaceholderTextField();
        craftbukkitPrTextField.setPlaceholder("CRAFTBUKKIT PR:ID");
        craftbukkitPrTextField.setToolTipText("Example: CRAFTBUKKIT:207");
        craftbukkitPrTextField.setMargin(new Insets(8, 8, 8, 8));
        craftbukkitPrTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                craftbukkitPrFieldKeyEvent(keyEvent);
            }
        });

        this.compileNoneCheckbox = createCompilationOption("NONE", "--compile none", this::compileNoneCheckboxActionPerformed);
        this.compileSpigotCheckbox = createCompilationOption("SPIGOT", "--compile spigot", this::compileSpigotCheckboxActionPerformed);
        this.compileCraftbukkitCheckbox = createCompilationOption("CRAFTBUKKIT", "--compile craftbukkit", this::compileCraftbukkitCheckboxActionPerformed);

        final JCheckBox overrideJavaCheckbox = new JCheckBox("Override Java Executable");
        overrideJavaCheckbox.addActionListener((ActionEvent event) -> overrideJavaCheckboxActionPerformed(overrideJavaCheckbox));

        final JLabel javaExecutableLabel = new JLabel("Java Executable");

        this.javaExecutableField = new JTextField();
        javaExecutableField.setFont(Constants.GENERAL_FONT);
        javaExecutableField.setToolTipText("Path to the java executable you want to use.");
        javaExecutableField.setMargin(new Insets(8, 8, 8, 8));
        javaExecutableField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                javaExecutableFieldKeyReleased(event);
            }
        });
        javaExecutableField.setEnabled(false);

        this.detectJavaButton = new JButton("Detect & Choose");
        detectJavaButton.setToolTipText("Select the java executable.");
        detectJavaButton.setMargin(new Insets(8, 16, 8, 16));
        detectJavaButton.addActionListener(this::detectJavaButtonActionPerformed);
        detectJavaButton.setEnabled(false);
        detectJavaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.javaSelectionButton = new JButton("Select");
        javaSelectionButton.setToolTipText("Select the java executable");
        javaSelectionButton.setMargin(new Insets(8, 16, 8, 16));
        javaSelectionButton.addActionListener(this::selectJavaButtonActionPerformed);
        javaSelectionButton.setEnabled(false);
        javaSelectionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        final JLabel jvmArgumentsLabel = new JLabel("JVM Arguments");

        final JTextField jvmArgumentsField = new JTextField("-Xms512M");
        jvmArgumentsField.setToolTipText("Any additional JVM flags that you want to set.");
        jvmArgumentsField.setFont(Constants.GENERAL_FONT);
        jvmArgumentsField.setMargin(new Insets(8, 8, 8, 8));
        jvmArgumentsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                jvmArgumentsFieldKeyReleased(keyEvent);
            }
        });

        final JLabel workDirLabel = new JLabel("Working Directory");

        final JTextField workDirField = new JTextField(Builder.CWD.toString());
        workDirField.setToolTipText("The directory BuildTools will run in.");
        workDirField.setMargin(new Insets(4, 8, 4, 8));
        workDirField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                workDirectoryFieldKeyRelease(keyEvent);
            }
        });
        buildSettings.setWorkDirectory(workDirField.getText());

        final FlagRemapPanel flagRemapPanel = new FlagRemapPanel(buildSettings);
        final FlagJavadocsPanel flagJavadocsPanel = new FlagJavadocsPanel(buildSettings);
        final FlagGenerateSourcesPanel flagGenerateSourcesPanel = new FlagGenerateSourcesPanel(buildSettings);
        final FlagCompileChangesPanel flagCompileChangesPanel = new FlagCompileChangesPanel(buildSettings);
        final FlagSkipHTTPSCheckPanel flagSkipHTTPSCheckPanel = new FlagSkipHTTPSCheckPanel(buildSettings);
        final FlagSkipJavaVersionCheckPanel flagSkipJavaVersionCheckPanel = new FlagSkipJavaVersionCheckPanel(buildSettings);
        final FlagDontUpdatePanel flagDontUpdatePanel = new FlagDontUpdatePanel(buildSettings);

        final JSeparator verticalSeparator0 = new JSeparator(SwingConstants.VERTICAL);
        final JSeparator verticalSeparator1 = new JSeparator(SwingConstants.VERTICAL);

        final GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(flagGenerateSourcesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagCompileChangesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagSkipHTTPSCheckPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagSkipJavaVersionCheckPanel, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildFlagsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagDontUpdatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagRemapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(flagJavadocsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(spigotPrTextField)
                                        .addComponent(bukkitPrTextField)
                                        .addComponent(craftbukkitPrTextField)))
                                .addComponent(pullRequestsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addComponent(verticalSeparator0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(compilationOptionsLabel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(compileNoneCheckbox)
                                .addComponent(compileSpigotCheckbox)
                                .addComponent(compileCraftbukkitCheckbox))
                            .addGap(32, 32, 32)
                            .addComponent(verticalSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(javaConfigurationLabel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addComponent(overrideJavaCheckbox))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(javaExecutableLabel)
                                        .addComponent(jvmArgumentsLabel)
                                        .addComponent(workDirLabel))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(javaExecutableField, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(detectJavaButton)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(javaSelectionButton))
                                        .addComponent(jvmArgumentsField)
                                        .addComponent(workDirField))
                                    ))))
                    .addGap(0, 0, 0))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(buildFlagsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addComponent(flagRemapPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagJavadocsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagGenerateSourcesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagCompileChangesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagSkipHTTPSCheckPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagSkipJavaVersionCheckPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addComponent(flagDontUpdatePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(pullRequestsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(spigotPrTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bukkitPrTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(craftbukkitPrTextField, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(javaConfigurationLabel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(overrideJavaCheckbox))
                            .addGap(9, 9, 9)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(javaExecutableField, GroupLayout.PREFERRED_SIZE, 32,
                                    GroupLayout.PREFERRED_SIZE)
                                .addComponent(javaExecutableLabel)
                                .addComponent(javaSelectionButton, GroupLayout.PREFERRED_SIZE, 32,
                                    GroupLayout.PREFERRED_SIZE)
                                .addComponent(detectJavaButton, GroupLayout.PREFERRED_SIZE, 32,
                                    GroupLayout.PREFERRED_SIZE))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jvmArgumentsField, GroupLayout.PREFERRED_SIZE, 32,
                                    GroupLayout.PREFERRED_SIZE)
                                .addComponent(jvmArgumentsLabel))
                            .addGap(8, 8, 8)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(workDirField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addComponent(workDirLabel)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(compilationOptionsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(compileNoneCheckbox)
                            .addGap(26, 26, 26)
                            .addComponent(compileSpigotCheckbox)
                            .addGap(26, 26, 26)
                            .addComponent(compileCraftbukkitCheckbox))
                        .addComponent(verticalSeparator0)
                        .addComponent(verticalSeparator1, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, 0))
        );


        List<Component> order = new ArrayList<>();
        order.add(flagRemapPanel.getTraversableComponent());
        order.add(flagJavadocsPanel.getTraversableComponent());
        order.add(flagGenerateSourcesPanel.getTraversableComponent());
        order.add(flagCompileChangesPanel.getTraversableComponent());
        order.add(flagSkipHTTPSCheckPanel.getTraversableComponent());
        order.add(flagSkipJavaVersionCheckPanel.getTraversableComponent());
        order.add(flagDontUpdatePanel.getTraversableComponent());

        order.add(spigotPrTextField);
        order.add(bukkitPrTextField);
        order.add(craftbukkitPrTextField);

        order.add(compileNoneCheckbox);
        order.add(compileSpigotCheckbox);
        order.add(compileCraftbukkitCheckbox);

        order.add(overrideJavaCheckbox);
        order.add(javaExecutableField);
        order.add(detectJavaButton);
        order.add(javaSelectionButton);
        order.add(jvmArgumentsField);
        order.add(workDirField);

        setFocusTraversalPolicyProvider(true);

        traversalPolicy = new CustomFocusTraversalPolicy(order);
        setFocusTraversalPolicy(traversalPolicy);
    }

    // PR Field Events
    private void spigotPrFieldKeyReleased(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();

        if (Utils.isNumeric(field.getText()) && !field.getText().isEmpty()) {
            buildSettings.setSpigotPrId("SPIGOT:" + field.getText());
        } else if (Utils.isValidSpigotPR(field.getText())) {
            buildSettings.setSpigotPrId(field.getText());
        } else {
            buildSettings.setSpigotPrId("");
        }

        SwingUtils.validateSpigotPR(field);
    }

    private void craftbukkitPrFieldKeyEvent(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();

        if (Utils.isNumeric(field.getText()) && !field.getText().isEmpty()) {
            buildSettings.setCraftbukkitPrId("CRAFTBUKKIT:" + field.getText());
        } else if (Utils.isValidCraftbukkitPR(field.getText())) {
            buildSettings.setCraftbukkitPrId(field.getText());
        } else {
            buildSettings.setCraftbukkitPrId("");
        }

        SwingUtils.validateCraftbukkitPR(field);
    }

    private void bukkitPrFieldKeyEvent(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();

        if (Utils.isNumeric(field.getText()) && !field.getText().isEmpty()) {
            buildSettings.setBukkitPrId("BUKKIT:" + field.getText());
        } else if (Utils.isValidBukkitPR(field.getText())) {
            buildSettings.setBukkitPrId(field.getText());
        } else {
            buildSettings.setBukkitPrId("");
        }

        SwingUtils.validateBukkitPR(field);
    }

    // Compilation Option Events
    private void compileNoneCheckboxActionPerformed(ActionEvent event) {
        final boolean selected = this.compileNoneCheckbox.isSelected();
        buildSettings.setCompileNone(selected);

        this.compileCraftbukkitCheckbox.setEnabled(!selected);
        this.compileCraftbukkitCheckbox.setSelected(false);
        buildSettings.setCompileCraftbukkit(false);

        this.compileSpigotCheckbox.setEnabled(!selected);
        this.compileSpigotCheckbox.setSelected(false);
        buildSettings.setCompileSpigot(false);
    }

    private void compileSpigotCheckboxActionPerformed(ActionEvent event) {
        final boolean selectedSpigot = this.compileSpigotCheckbox.isSelected();
        buildSettings.setCompileSpigot(selectedSpigot);
    }

    private void compileCraftbukkitCheckboxActionPerformed(ActionEvent event) {
        final boolean selectedCraftbukkit = this.compileCraftbukkitCheckbox.isSelected();
        buildSettings.setCompileCraftbukkit(selectedCraftbukkit);
    }

    // Additional Configuration Options
    private void overrideJavaCheckboxActionPerformed(JCheckBox checkbox) {
        if (checkbox.isSelected()) {
            this.javaExecutableField.setEnabled(true);
            this.detectJavaButton.setEnabled(true);
            this.javaSelectionButton.setEnabled(true);
            buildSettings.setOverrideJavaExecutable(true);
            return;
        }

        this.javaExecutableField.setEnabled(false);
        this.detectJavaButton.setEnabled(false);
        this.javaSelectionButton.setEnabled(false);
        buildSettings.setOverrideJavaExecutable(false);
        buildData.updateJavaExecutable(buildSettings, false);
    }

    // Text Fields
    private void javaExecutableFieldKeyReleased(KeyEvent event) {
//        final JTextField field = (JTextField) event.getSource();
//
//        File file = new File(field.getText());
//
//        if (!file.exists() && !file.canExecute()) {
//            return;
//        }
//
//        List<JavaInstallation> installations = JavaFinder.builder()
//            .checkDefaultLocations(false)
//            .addSearchDirectories(file.getAbsoluteFile().getParentFile().getParentFile())
//            .build().findInstallations();
//
//        if (!installations.isEmpty()) {
//            buildData.getJavaInstallationManager().setSelectedInstallation(installations.get(0));
//        }
    }

    private void detectJavaButtonActionPerformed(ActionEvent event) {
        final SelectJavaModal modal = new SelectJavaModal(this, this.buildData.getJavaInstallationManager());
        modal.setLocationRelativeTo(null);
        modal.setVisible(true);
    }

    private void selectJavaButtonActionPerformed(ActionEvent event) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.OPEN_DIALOG) {
            final File selection = chooser.getSelectedFile();
            javaExecutableField.setText(selection.getAbsolutePath());

            List<JavaInstallation> installations = JavaFinder.builder()
                .checkDefaultLocations(false)
                .addSearchDirectories(selection.getAbsoluteFile().getParentFile().getParentFile())
                .build().findInstallations();

            if (!installations.isEmpty()) {
                buildData.getJavaInstallationManager().setSelectedInstallation(installations.get(0));
            }
        }
    }

    private void jvmArgumentsFieldKeyReleased(KeyEvent event) {
        final JTextField field = (JTextField) event.getSource();
        buildData.getJavaInstallationManager().setJvmArguments(field.getText());
    }

    private void workDirectoryFieldKeyRelease(KeyEvent event) {
        final JTextField textField = (JTextField) event.getSource();
        buildSettings.setWorkDirectory(textField.getText());

        SwingUtils.validatePath(textField);

        if (textField.getText().isEmpty()) {
            panel.getSettingsPanel().getOutputDirField().setPlaceholder(Builder.CWD.toString());
        } else {
            panel.getSettingsPanel().getOutputDirField().setPlaceholder(textField.getText());
        }

    }

    public void setJavaExecutable(JavaInstallation executable) {
        boolean wasDisabled = false;
        if (!javaExecutableField.isEnabled()) {
            this.javaExecutableField.setEnabled(true);
            wasDisabled = true;
        }
        this.javaExecutableField.setText(executable.getJavaExecutable().getAbsolutePath());

        if (wasDisabled) {
            this.javaExecutableField.setEnabled(false);
        }
    }

    private static JCheckBox createCompilationOption(final String text, final String tooltip, ActionListener actionListener) {
        final JCheckBox checkBox = new JCheckBox(text);
        checkBox.setToolTipText(tooltip);
        checkBox.setMargin(new Insets(0, 0, 0, 0));
        checkBox.addActionListener(actionListener);
        return checkBox;
    }

}
