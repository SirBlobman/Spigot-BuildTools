package org.spigotmc.gui.panels.general.settings;

import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class SettingsPanelHeader extends JPanel implements Themeable {

    public SettingsPanelHeader() {
        initComponents();
    }

    private void initComponents() {
        final JLabel settingsPanelHeaderLabel = new JLabel();
        settingsPanelHeaderLabel.setText("<html><b>Settings</b></html>");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(12, 12, 12)
                    .addComponent(settingsPanelHeaderLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(settingsPanelHeaderLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)));
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack themePack = ThemePack.fromTheme(theme);

        final Color panelHeaderBackground = (Color) themePack.getAsset(ThemePack.Asset.PANEL_HEADER_BACKGROUND_COLOR);
        setBackground(panelHeaderBackground);

        final Color borderColor = (Color) themePack.getAsset(ThemePack.Asset.BORDER_COLOR);
        setBorder(BorderFactory.createLineBorder(borderColor));
    }
}
