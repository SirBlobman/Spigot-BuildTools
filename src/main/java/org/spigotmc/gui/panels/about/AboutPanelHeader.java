package org.spigotmc.gui.panels.about;

import org.spigotmc.builder.Builder;
import org.spigotmc.utils.Constants;
import org.spigotmc.utils.SwingUtils;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

public class AboutPanelHeader extends JPanel {

    private JLabel logoIconLabel;

    public AboutPanelHeader() {
        initComponents();
    }

    private void initComponents() {
        final JLabel titleLabel = new JLabel("<html><b>BuildTools</b></html>");
        titleLabel.setFont(new Font("Liberation Sans", Font.PLAIN, 16));

        this.logoIconLabel = new JLabel();
        logoIconLabel.setMaximumSize(new Dimension(64, 64));
        logoIconLabel.setPreferredSize(new Dimension(64, 64));

        final JLabel buildToolsVersionLabel = new JLabel(Builder.class.getPackage().getImplementationVersion());

        final JButton websiteButton = SwingUtils.buildRedirectButton("<html><u>Website</u></html>", Constants.WEBSITE_LINK);
        final JButton wikiButton = SwingUtils.buildRedirectButton("<html><u>Wiki</u></html>", Constants.WIKI_LINK);
        final JButton discordButton = SwingUtils.buildRedirectButton("<html><u>Discord</u></html>", Constants.DISCORD_LINK);
        final JButton stashButton = SwingUtils.buildRedirectButton("<html><u>Source Code</u></html>", Constants.STASH_LINK);
        final JButton reportBugButton = SwingUtils.buildRedirectButton("<html><u>Report a Bug</u></html>", Constants.BUG_TRACKER_LINK);
        final JButton donateButton = SwingUtils.buildRedirectButton("<html><u>Donate</u></html>", Constants.DONATE_LINK);

        final GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(logoIconLabel, GroupLayout.PREFERRED_SIZE, 64,
                        GroupLayout.PREFERRED_SIZE)
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(buildToolsVersionLabel)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(websiteButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(wikiButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(discordButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(stashButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(reportBugButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(donateButton, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 337, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4, 4, 4)
                    .addComponent(buildToolsVersionLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(websiteButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(wikiButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(discordButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(stashButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(reportBugButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(donateButton, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addComponent(logoIconLabel, GroupLayout.PREFERRED_SIZE, 64,
                    GroupLayout.PREFERRED_SIZE)
        );
    }

    public void setLogoImage(Image image) {
        logoIconLabel.setIcon(new ImageIcon(image));
    }

}
