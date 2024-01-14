package org.spigotmc.gui.panels.about;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.spigotmc.utils.SwingUtils;

public class AboutPanelFooter extends JPanel {

    public AboutPanelFooter() {
        initComponents();
    }

    private void initComponents() {
        final JLabel footerNameLabel = new JLabel("© 2024 SpigotMC");
        final JButton footerLicenseButton = SwingUtils.buildRedirectButton("Licensed under BSD-3", "https://hub.spigotmc.org/stash/projects/SPIGOT/repos/buildtools/browse/LICENSE");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(footerLicenseButton)
                    .addContainerGap(366, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(footerNameLabel)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(footerLicenseButton)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(footerNameLabel))
        );

    }

}
