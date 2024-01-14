package org.spigotmc.gui.modals;

import com.jeff_media.javafinder.JavaInstallation;
import org.spigotmc.builder.Builder;
import org.spigotmc.builder.JavaVersion;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.BuildSettings;
import org.spigotmc.gui.data.BuildData;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.utils.Constants;
import org.spigotmc.utils.SwingUtils;
import org.spigotmc.utils.Utils;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class DebugInfoModal extends JFrame implements Themeable {

    private JTextPane debugInfoTextPane;

    private JButton reportBugButton;

    private final BuildData buildData;
    private final BuildSettings buildSettings;

    public DebugInfoModal(final BuildData buildData, BuildSettings buildSettings) {
        this.buildData = buildData;
        this.buildSettings = buildSettings;

        SwingUtils.applyIcon(this);
        initComponents();

        SwingUtils.changeTheme(this, SwingUtils.getTheme());

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BuildTools Debug");
        setSize(new Dimension(600, 500));

        debugInfoTextPane.setCaretPosition(0);
    }

    private void initComponents() {
        final JButton closeButton = new JButton("Close");
        closeButton.setMargin(new Insets(8, 16, 8, 16));
        closeButton.addActionListener((ActionEvent event) -> dispose());

        reportBugButton = new JButton("Report a Bug");
        reportBugButton.setHorizontalTextPosition(SwingConstants.LEFT);
        reportBugButton.setIconTextGap(8);
        reportBugButton.setMargin(new Insets(8, 16, 8, 16));
        reportBugButton.addActionListener((ActionEvent event) -> SwingUtils.browse(Constants.BUG_TRACKER_LINK));

        debugInfoTextPane = new JTextPane();
        debugInfoTextPane.setContentType("text/html");
        debugInfoTextPane.setEditable(false);
        debugInfoTextPane.setText(gatherInfo());

        final JButton copyToClipboardButton = new JButton("Copy To Clipboard");
        copyToClipboardButton.setMargin(new Insets(8, 16, 8, 16));
        copyToClipboardButton.addActionListener((ActionEvent event) -> {
            debugInfoTextPane.selectAll();
            debugInfoTextPane.copy();
            debugInfoTextPane.setCaretPosition(0);
            SwingUtils.buttonCooldown(copyToClipboardButton, 3, "Copied To Clipboard");
        });

        final JScrollPane debugScrollPane = new JScrollPane();
        debugScrollPane.setViewportView(debugInfoTextPane);

        final JLabel debugIconLabel = new JLabel();
        debugIconLabel.setIcon(Constants.LOGO_32x32);
        debugIconLabel.setIconTextGap(12);

        final JLabel debugTitleLabel = new JLabel("<html><b>BuildTools Debug Information</b></html>");
        debugTitleLabel.setFont(new java.awt.Font("Liberation Sans", Font.PLAIN, 14)); // NOI18N

        final JLabel bugReportLabel = new JLabel("Include the following information whenever you report a bug:");

        final JPanel debugHeader = new JPanel();
        GroupLayout debugHeaderLayout = new GroupLayout(debugHeader);
        debugHeader.setLayout(debugHeaderLayout);
        debugHeaderLayout.setHorizontalGroup(debugHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(debugHeaderLayout.createSequentialGroup().addContainerGap().addComponent(debugIconLabel)
                .addGap(12, 12, 12).addGroup(debugHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(debugTitleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE).addComponent(bugReportLabel))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        debugHeaderLayout.setVerticalGroup(debugHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(debugHeaderLayout.createSequentialGroup().addContainerGap().addGroup(
                    debugHeaderLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(bugReportLabel)
                        .addGroup(debugHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(debugTitleLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addComponent(debugIconLabel)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
            layout.createSequentialGroup().addContainerGap().addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(debugScrollPane)
                    .addGroup(GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addComponent(copyToClipboardButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(reportBugButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                            .addComponent(closeButton))
                    .addComponent(debugHeader, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
            layout.createSequentialGroup().addContainerGap()
                .addComponent(debugHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE).addGap(12, 12, 12)
                .addComponent(debugScrollPane, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(closeButton)
                        .addComponent(copyToClipboardButton).addComponent(reportBugButton)).addContainerGap()));

        pack();
    }

    private String gatherInfo() {
        StringBuilder args = new StringBuilder();

        for (String s : buildSettings.getArguments()) {
            args.append(s);
            args.append(" ");
        }

        StringBuilder javaVersions = new StringBuilder();

        for (JavaInstallation installation : buildData.getJavaInstallationManager().getInstallations()) {
            javaVersions.append("<p>");

            if (installation.isCurrentJavaVersion()) {
                javaVersions.append("* ");
            } else {
                javaVersions.append("- ");
            }

            javaVersions.append(installation.getType());
            javaVersions.append(" ");
            javaVersions.append(installation.getVersion().getMajor());
            javaVersions.append(" at ");
            javaVersions.append(installation.getJavaExecutable().getPath());
            javaVersions.append("</p>");
        }

        long allocatedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        long totalFree = (Runtime.getRuntime().maxMemory() - allocatedMemory);
        String memUsage = Utils.readableFileSize(allocatedMemory) + " / " + Utils.readableFileSize(totalFree);

        String style = Utils.getFileContentsFromResource("web/reset.css");
        String debug = Utils.getFileContentsFromResource("web/debug.html");
        debug = debug.replace("%STYLESHEET%", "<style>" + style + "</style>");
        debug = debug.replace("%BUILDTOOLS_VERSION%", Builder.class.getPackage().getImplementationVersion());
        debug = debug.replace("%JAVA_VERSION%", JavaVersion.getCurrentVersion().toString());
        debug = debug.replace("%CURRENT_PATH%", buildSettings.getWorkDirectory());
        debug = debug.replace("%BUILDTOOLS_FLAGS%", args);
        debug = debug.replace("%OS_NAME%", System.getProperty("os.name"));
        debug = debug.replace("%OS_ARCH%", System.getProperty("os.arch"));
        debug = debug.replace("%KERNEL_VERSION%", System.getProperty("os.version"));
        debug = debug.replace("%MEM_USAGE%", memUsage);
        debug = debug.replace("%JAVA_INSTALLATIONS%", javaVersions);
        // debug = debug.replace("%%", "");

        return debug;
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack pack = ThemePack.fromTheme(theme);
        final ImageIcon external = (ImageIcon) pack.getAsset(ThemePack.Asset.EXTERNAL);
        reportBugButton.setIcon(external);
    }
}
