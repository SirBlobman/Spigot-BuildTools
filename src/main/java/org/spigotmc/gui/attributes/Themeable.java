package org.spigotmc.gui.attributes;

import org.spigotmc.gui.Theme;

/**
 * Should be implemented by containers in which themes change in a nonstandard way.
 */
public interface Themeable {

    /**
     * Triggered when the theme is changing
     *
     * @param theme the new theme
     */
    void onThemeChange(final Theme theme);
}
