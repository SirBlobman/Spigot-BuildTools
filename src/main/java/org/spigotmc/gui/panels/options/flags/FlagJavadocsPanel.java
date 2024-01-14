package org.spigotmc.gui.panels.options.flags;

import javax.swing.GroupLayout;

import org.spigotmc.gui.data.BuildSettings;

import java.awt.event.ActionEvent;

public class FlagJavadocsPanel extends AbstractFlagPanel {

    public FlagJavadocsPanel(BuildSettings buildSettings) {
        super(buildSettings);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        flagCheckbox.setText("Generate Javadocs");
        flagCheckbox.setToolTipText("--generate-javadocs");

        flagLabel.setText("Generates the javadoc jars. NOTE: Sources aren't installed locally.");

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
        buildSettings.setGenerateJavadocs(flagCheckbox.isSelected());
    }

}
