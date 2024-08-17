package org.spigotmc.gui.panels.options.flags;

import org.spigotmc.gui.data.BuildSettings;

import javax.swing.GroupLayout;
import java.awt.event.ActionEvent;

public class FlagRemapPanel extends AbstractFlagPanel {

    public FlagRemapPanel(BuildSettings buildSettings) {
        super(buildSettings);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        flagCheckbox.setText("Generate Remapped Jars");
        flagCheckbox.setToolTipText("--remapped");

        flagLabel.setText("Automatically installs the remapped versions to your local .m2 directory.");

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(flagCheckbox)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(flagLabel)))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    @Override
    protected void checkboxActionPerformed(ActionEvent event) {
        buildSettings.setRemapped(flagCheckbox.isSelected());
    }

}
