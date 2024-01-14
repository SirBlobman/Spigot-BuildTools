package org.spigotmc.gui.panels.about.tester;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestersPanel extends JPanel {

    public TestersPanel() {
        initComponents();
    }

    private void initComponents() {
        final JLabel thanksLabel = new JLabel("Special thanks to those who tested the early iterations.");
        final JLabel kaspian = new JLabel("Kaspian");
        final JLabel epicebic = new JLabel("EpicEbic");
        final JLabel ntdi = new JLabel("Ntdi");
        final JLabel kyllian = new JLabel("Kyllian");
        final JLabel imillusion = new JLabel("ImIllusion (I guess)");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(imillusion)
                        .addComponent(kyllian)
                        .addComponent(ntdi)
                        .addComponent(kaspian)
                        .addComponent(epicebic)
                        .addComponent(thanksLabel))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(thanksLabel)
                    .addGap(8, 8, 8)
                    .addComponent(epicebic)
                    .addGap(8, 8, 8)
                    .addComponent(kaspian)
                    .addGap(8, 8, 8)
                    .addComponent(ntdi)
                    .addGap(8, 8, 8)
                    .addComponent(kyllian)
                    .addGap(8, 8, 8)
                    .addComponent(imillusion))
        );

    }

}
