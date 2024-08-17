package org.spigotmc.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import org.spigotmc.gui.Theme;
import org.spigotmc.gui.attributes.Themeable;
import org.spigotmc.gui.data.ThemePack;
import org.spigotmc.gui.data.ThemePack.Asset;

public class PlaceholderTextField extends JTextField implements Themeable {

  private String placeholder;
  private Color color = Color.BLACK;

  public PlaceholderTextField() { }

  public PlaceholderTextField(String text) {
    this.setText(text);
  }

  public PlaceholderTextField(String text, String placeholder) {
    this.setText(text);
    this.setPlaceholder(placeholder);
  }

  @Override
  protected void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);

    if (placeholder == null || placeholder.isEmpty() || !getText().isEmpty()) {
      return;
    }

    final Graphics2D g = (Graphics2D) graphics;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setColor(color);
    g.drawString(placeholder, getInsets().left, graphics.getFontMetrics().getMaxAscent() + getInsets().top);
  }

  public void setPlaceholder(final String placeholder) {
    this.placeholder = placeholder;
  }

  @Override
  public void onThemeChange(Theme theme) {
    ThemePack themePack = ThemePack.fromTheme(theme);
    color = (Color) themePack.getAsset(Asset.TEXT_SECONDARY_COLOR);
  }
}