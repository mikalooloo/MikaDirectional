package io.github.mikalooloo.mikadirectional.config;

import io.github.mikalooloo.mikadirectional.MikaDirectional;

import org.jetbrains.annotations.NotNull;

/**
 * Handles the default config file, finding the values set within it and providing defaults if not found
 *
 * @author  Mikalooloo
 * @version 1.0
 * @since   1.0
 */
public class MDirConfig {

    // VARIABLES
    final private MikaDirectional plugin;
    private boolean defaultCoords;
    private boolean defaultDirection;
    private String defaultLabelsColor;
    private String defaultValuesColor;
    private boolean needUserPerms;
    private boolean needReloadPerms;

    // CONSTRUCTOR
    public MDirConfig(@NotNull MikaDirectional plugin) {
        this.plugin = plugin;
        load();
    }

    // GETTERS
    public boolean getDefaultCoords() { return defaultCoords; }
    public boolean getDefaultDirection() { return defaultDirection; }
    public String getDefaultLabelsColor() { return defaultLabelsColor; }
    public String getDefaultValuesColor() { return defaultValuesColor; }
    public boolean getNeedUserPerms() { return needUserPerms; }
    public boolean getNeedReloadPerms() { return needReloadPerms; }

    // METHODS
    /** Retrieves the default config values from the config.yml file. */
    public void load() {
        plugin.reloadConfig();
        if (plugin.getConfig().contains("Coords")) {
            defaultCoords = plugin.getConfig().getBoolean("Coords");
        }
        else { defaultCoords = false; }

        if (plugin.getConfig().contains("Direction")) {
            defaultDirection = plugin.getConfig().getBoolean("Direction");
        }
        else { defaultDirection = false; }

        if (plugin.getConfig().contains("LabelsColor")) {
            defaultLabelsColor = plugin.getConfig().getString("LabelsColor");
        }
        else { defaultLabelsColor = "#FFAA00"; }

        if (plugin.getConfig().contains("ValuesColor")) {
            defaultValuesColor = plugin.getConfig().getString("ValuesColor");
        }
        else { defaultValuesColor = "#FFFFFF"; }

        if (plugin.getConfig().contains("NeedUserPerms")) {
            needUserPerms = plugin.getConfig().getBoolean("NeedUserPerms");
        }
        else { needUserPerms = false; }

        if (plugin.getConfig().contains("NeedReloadPerms")) {
            needReloadPerms = plugin.getConfig().getBoolean("NeedReloadPerms");
        }
        else { needReloadPerms = false; }
    }
}
