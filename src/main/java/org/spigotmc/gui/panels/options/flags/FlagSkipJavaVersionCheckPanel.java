package org.spigotmc.gui.panels.options.flags;

import javax.swing.GroupLayout;

import org.spigotmc.gui.data.BuildSettings;

import java.awt.event.ActionEvent;

public class FlagSkipJavaVersionCheckPanel extends AbstractFlagPanel {

    public FlagSkipJavaVersionCheckPanel(BuildSettings buildSettings) {
        super(buildSettings);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        flagCheckbox.setText("Skip Java Version Check");
        flagCheckbox.setToolTipText("--disable-java-check");

        flagLabel.setText("Disables the internal java check.");

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
        buildSettings.setSkipJavaVersionCheck(flagCheckbox.isSelected());
    }
}
