package org.spigotmc.gui.panels.general.console;

import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Lockable;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import java.awt.Color;

public class ConsolePanelHeader extends JPanel implements Lockable, Themeable {

    private JCheckBox copyLogCheckbox;

    public ConsolePanelHeader() {
        initComponents();
    }

    private void initComponents() {
        final JLabel consoleOutputHeader = new JLabel("<html><b>Console Output</b></html>");

        this.copyLogCheckbox = new JCheckBox("Copy Log When Finished");

        final GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(consoleOutputHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(copyLogCheckbox).addGap(12, 12, 12)));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(consoleOutputHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.PREFERRED_SIZE)
                        .addComponent(copyLogCheckbox))
                    .addContainerGap()));
    }

    public boolean copyLogsOnFinish() {
        return copyLogCheckbox.isSelected();
    }

    @Override
    public void onLockToggle(final LockReason reason) {
        if (reason == LockReason.START) {
            copyLogCheckbox.setEnabled(!copyLogCheckbox.isEnabled());
        }
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack themePack = ThemePack.fromTheme(theme);

        final Color panelHeaderBackground = (Color) themePack.getAsset(ThemePack.Asset.PANEL_HEADER_BACKGROUND_COLOR);
        setBackground(panelHeaderBackground);

        final Color broderColor = (Color) themePack.getAsset(ThemePack.Asset.BORDER_COLOR);
        setBorder(BorderFactory.createLineBorder(broderColor));
    }
}
