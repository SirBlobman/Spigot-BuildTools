package org.spigotmc.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public enum Theme
{
    LIGHT(FlatLightLaf::setup),
    DARK(FlatDarkLaf::setup),
    UNKNOWN(() -> {});

    private final Runnable apply;

    Theme(final Runnable apply) {
        this.apply = apply;
    }

    public void apply() {
        apply.run();
    }
}