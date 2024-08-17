package org.spigotmc.gui.panels.about;

import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class AboutInternalPanelHeader extends JPanel implements Themeable {

    private final String label;

    public AboutInternalPanelHeader(final String label) {
        this.label = label;
        setPreferredSize(new Dimension(391, 32));
        initComponents();
    }

    private void initComponents() {
        final JLabel titleLabel = new JLabel(label);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(10, Short.MAX_VALUE))
        );

    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack themePack = ThemePack.fromTheme(theme);
        final Color color = (Color) themePack.getAsset(ThemePack.Asset.PANEL_HEADER_BACKGROUND_COLOR);
        setBackground(color);
    }
}
