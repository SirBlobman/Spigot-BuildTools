package org.spigotmc.gui.panels;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.utils.SwingUtils;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

public class ThemeSwitcherPanel extends JPanel implements Themeable {

    private Theme currentTheme = Theme.LIGHT;

    private final JFrame parent;

    private JButton lightButton;
    private JButton darkButton;

    public ThemeSwitcherPanel(final JFrame parent) {
        this.parent = parent;

        setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

        initComponents();
    }

    private void initComponents() {
        this.lightButton = new JButton();
        lightButton.setBackground(new Color(222, 222, 222));
        lightButton.setToolTipText("Switch to the light theme.");
        lightButton.setBorderPainted(false);
        lightButton.setMargin(new Insets(4, 12, 4, 12));
        lightButton.addActionListener(this::lightModeSwitch);
        lightButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lightButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentTheme == Theme.DARK) {
                    lightButton.setContentAreaFilled(true);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (currentTheme == Theme.DARK) {
                    lightButton.setContentAreaFilled(false);
                }
            }
        });

        this.darkButton = new JButton();
        darkButton.setToolTipText("Switch to the dark theme.");
        darkButton.setBackground(new Color(222, 222, 222));
        darkButton.setBorderPainted(false);
        darkButton.setContentAreaFilled(false);
        darkButton.setMargin(new Insets(4, 12, 4, 12));
        darkButton.addActionListener(this::darkModeSwitch);
        darkButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        darkButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentTheme == Theme.LIGHT) {
                    darkButton.setContentAreaFilled(true);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (currentTheme == Theme.LIGHT) {
                    darkButton.setContentAreaFilled(false);
                }
            }
        });

        add(this.lightButton);
        add(this.darkButton);
    }

    private void lightModeSwitch(ActionEvent event) {
        if (currentTheme == Theme.LIGHT) {
            return;
        }

        FlatLightLaf.setup();

        lightButton.setContentAreaFilled(true);
        darkButton.setContentAreaFilled(false);

        lightButton.setBackground(new Color(222, 222, 222));
        darkButton.setBackground(new Color(222, 222, 222));


        SwingUtils.changeTheme(parent, Theme.LIGHT);
        FlatLaf.updateUI();
        currentTheme = Theme.LIGHT;
    }

    private void darkModeSwitch(ActionEvent event) {
        if (currentTheme == Theme.DARK) {
            return;
        }

        FlatDarkLaf.setup();

        lightButton.setContentAreaFilled(false);
        darkButton.setContentAreaFilled(true);

        lightButton.setBackground(new Color(70, 73, 75));
        darkButton.setBackground(new Color(70, 73, 75));

        SwingUtils.changeTheme(parent, Theme.DARK);
        FlatLaf.updateUI();
        currentTheme = Theme.DARK;
    }

    @Override
    public void onThemeChange(Theme theme) {
        final ThemePack pack = ThemePack.fromTheme(theme);

        final Color borderColor = (Color) pack.getAsset(ThemePack.Asset.BORDER_COLOR);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 1, true));

        final ImageIcon sun = (ImageIcon) pack.getAsset(ThemePack.Asset.SUN);
        lightButton.setIcon(sun);

        final ImageIcon moon = (ImageIcon) pack.getAsset(ThemePack.Asset.MOON);
        darkButton.setIcon(moon);
    }
}
