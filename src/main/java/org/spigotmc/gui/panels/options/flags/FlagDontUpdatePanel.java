package org.spigotmc.gui.panels.options.flags;

import javax.swing.GroupLayout;

import org.spigotmc.gui.data.BuildSettings;

import java.awt.event.ActionEvent;

public class FlagDontUpdatePanel extends AbstractFlagPanel {

    public FlagDontUpdatePanel(BuildSettings buildSettings) {
        super(buildSettings);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        flagCheckbox.setText("Don't Pull Updates");
        flagCheckbox.setToolTipText("--dont-update");

        flagLabel.setText("BuildTools won't pull updates from Stash.");

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(flagLabel))
                        .addComponent(flagCheckbox))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }

    @Override
    protected void checkboxActionPerformed(ActionEvent event) {
        buildSettings.setDontPullUpdates(flagCheckbox.isSelected());
    }
}
