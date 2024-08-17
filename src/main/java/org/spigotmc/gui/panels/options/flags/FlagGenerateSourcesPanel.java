package org.spigotmc.gui.panels.options.flags;

import javax.swing.GroupLayout;

import org.spigotmc.gui.data.BuildSettings;

import java.awt.event.ActionEvent;

public class FlagGenerateSourcesPanel extends AbstractFlagPanel {

    public FlagGenerateSourcesPanel(BuildSettings buildSettings) {
        super(buildSettings);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        flagCheckbox.setText("Generate Source Jars");
        flagCheckbox.setToolTipText("--generate-sources");

        flagLabel.setText("Generates the source jars.");

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(flagLabel))
                        .addComponent(flagCheckbox))
                    .addContainerGap(8, Short.MAX_VALUE))
        );
    }

    @Override
    protected void checkboxActionPerformed(ActionEvent event) {
        this.buildSettings.setGenerateSource(flagCheckbox.isSelected());
    }
}
