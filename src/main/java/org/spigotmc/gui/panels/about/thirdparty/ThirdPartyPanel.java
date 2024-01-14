package org.spigotmc.gui.panels.about.thirdparty;

import org.spigotmc.utils.SwingUtils;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ThirdPartyPanel extends JPanel {

    public ThirdPartyPanel() {
        initComponents();
    }

    private void initComponents() {
        final JButton fontAwesomeButton = SwingUtils.buildRedirectButton("Font Awesome (Icons)", "https://creativecommons.org/licenses/by/4.0/");
        final JButton flatlafButton = SwingUtils.buildRedirectButton("FormDev (FlatLaf UI)", "https://www.formdev.com");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fontAwesomeButton)
                        .addComponent(flatlafButton))
                    .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(fontAwesomeButton)
                    .addGap(8, 8, 8)
                    .addComponent(flatlafButton)
                    .addContainerGap(40, Short.MAX_VALUE))
        );
    }

}
