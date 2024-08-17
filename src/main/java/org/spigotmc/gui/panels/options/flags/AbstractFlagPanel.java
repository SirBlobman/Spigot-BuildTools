package org.spigotmc.gui.panels.options.flags;

import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.BuildSettings;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.utils.Constants;

import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

public abstract class AbstractFlagPanel extends JPanel implements Themeable {

    protected final BuildSettings buildSettings;

    protected JCheckBox flagCheckbox;
    protected JLabel flagLabel;
    protected GroupLayout layout;

    protected AbstractFlagPanel(BuildSettings buildSettings) {
        this.buildSettings = buildSettings;
        initComponents();
    }

    protected void initComponents() {
        this.flagCheckbox = new JCheckBox();
        flagCheckbox.setFont(Constants.GENERAL_FONT);
        flagCheckbox.setBorder(null);
        flagCheckbox.setIconTextGap(8);
        flagCheckbox.addActionListener(this::checkboxActionPerformed);

        this.flagLabel = new JLabel();
        flagLabel.setFont(Constants.GENERAL_FONT);

        this.layout = new GroupLayout(this);
        setLayout(this.layout);

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(flagCheckbox)
                    .addGap(4, 4, 4)
                    .addComponent(flagLabel)
                    .addGap(0, 0, 0))
        );
    }

    protected abstract void checkboxActionPerformed(final ActionEvent event);

    public Component getTraversableComponent() {
        return this.flagCheckbox;
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack themePack = ThemePack.fromTheme(theme);

        final Color textSecondary = (Color) themePack.getAsset(ThemePack.Asset.TEXT_SECONDARY_COLOR);
        this.flagLabel.setForeground(textSecondary);
    }
}
