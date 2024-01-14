package org.spigotmc.gui.data;

import org.spigotmc.gui.Theme;
import org.spigotmc.utils.Utils;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ThemePack {

    public static final ThemePack LIGHT;
    public static final ThemePack DARK;

    static {
        final Map<Asset, Object> lightAssets = new HashMap<>();
        lightAssets.put(Asset.EXTERNAL, Utils.getIcon("light/external.png"));
        lightAssets.put(Asset.FOLDER, Utils.getIcon("light/folder.png"));
        lightAssets.put(Asset.MOON, Utils.getIcon("light/moon_16x16.png"));
        lightAssets.put(Asset.SUN, Utils.getIcon("light/sun_16x16.png"));
        lightAssets.put(Asset.BORDER_COLOR, new Color(185, 185, 185));
        lightAssets.put(Asset.PANEL_HEADER_BACKGROUND_COLOR, new Color(255, 255, 255));
        lightAssets.put(Asset.TEXT_PRIMARY_COLOR, new Color(0, 0, 0));
        lightAssets.put(Asset.TEXT_SECONDARY_COLOR, new Color(119, 119, 119));
        lightAssets.put(Asset.LOOK_AND_FEEL_CLASS, "com.formdev.flatlaf.FlatLightLaf");

        LIGHT = new ThemePack(Collections.unmodifiableMap(lightAssets));

        final Map<Asset, Object> darkAssets = new HashMap<>();
        darkAssets.put(Asset.EXTERNAL, Utils.getIcon("dark/external.png"));
        darkAssets.put(Asset.FOLDER, Utils.getIcon("dark/folder.png"));
        darkAssets.put(Asset.MOON, Utils.getIcon("dark/moon_16x16.png"));
        darkAssets.put(Asset.SUN, Utils.getIcon("dark/sun_16x16.png"));
        darkAssets.put(Asset.BORDER_COLOR, new Color(86, 86, 86));
        darkAssets.put(Asset.PANEL_HEADER_BACKGROUND_COLOR, new Color(70, 73, 75));
        darkAssets.put(Asset.TEXT_PRIMARY_COLOR, new Color(255, 255, 255));
        darkAssets.put(Asset.TEXT_SECONDARY_COLOR, new Color(153, 153, 153, 217));
        darkAssets.put(Asset.LOOK_AND_FEEL_CLASS, "com.formdev.flatlaf.FlatDarkLaf");

        DARK = new ThemePack(Collections.unmodifiableMap(darkAssets));
    }

    private final Map<Asset, Object> assets;

    private ThemePack(Map<Asset, Object> assets) {
        this.assets = assets;
    }

    public Object getAsset(Asset asset) {
        return assets.get(asset);
    }

    public static ThemePack fromTheme(final Theme theme) {
        switch (theme) {
            case DARK:
                return DARK;
            case LIGHT:
            case UNKNOWN:
            default:
                return LIGHT;
        }
    }

    public enum Asset {
        EXTERNAL("external", ImageIcon.class),
        FOLDER("folder", ImageIcon.class),
        MOON("moon", ImageIcon.class),
        SUN("sun", ImageIcon.class),
        BORDER_COLOR("border_color", Color.class),
        PANEL_HEADER_BACKGROUND_COLOR("panel_header_background_color", Color.class),
        TEXT_PRIMARY_COLOR("text_primary_color", Color.class),
        TEXT_SECONDARY_COLOR("text_secondary_color", Color.class),
        LOOK_AND_FEEL_CLASS("look_and_feel_class", String.class);

        private final String key;
        private final Class<?> type;

        Asset(final String key, final Class<?> type) {
            this.key = key;
            this.type = type;
        }

        public String getKey() {
            return this.key;
        }

        public Class<?> getType() {
            return type;
        }
    }

}
