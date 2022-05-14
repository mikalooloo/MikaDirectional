package io.github.mikalooloo.mikadirectional.config;
// Mika packages
import io.github.mikalooloo.mikadirectional.MikaDirectional;
// Jetbrains packages
import org.jetbrains.annotations.NotNull;

/**
 * MDirConfig handles the default config file, finding the values set within it and providing defaults if not found.
 *
 * @author  Mikalooloo
 * @since   1.0
 */
public class MDirConfig {

    // VARIABLES
    final private MikaDirectional plugin;
    private boolean coords;
    private boolean direction;
    private String labelsColor;
    private String valuesColor;
    private boolean needUserPerms;
    private boolean needReloadPerms;

    // CONSTRUCTOR
    public MDirConfig(@NotNull MikaDirectional plugin) {
        this.plugin = plugin;
        load();
    }

    // GETTERS
    public boolean getDefaultCoords() { return coords; }
    public boolean getDefaultDirection() { return direction; }
    public String getDefaultLabelsColor() { return labelsColor; }
    public String getDefaultValuesColor() { return valuesColor; }
    public boolean getNeedUserPerms() { return needUserPerms; }
    public boolean getNeedReloadPerms() { return needReloadPerms; }

    // METHODS
    /**
     * Retrieves the default config values from the config.yml file.
     */
    public void load() {
        plugin.reloadConfig();
        if (plugin.getConfig().contains("Coords")) {
            coords = plugin.getConfig().getBoolean("Coords");
        }
        else { coords = false; }

        if (plugin.getConfig().contains("Direction")) {
            direction = plugin.getConfig().getBoolean("Direction");
        }
        else { direction = false; }

        if (plugin.getConfig().contains("LabelsColor")) {
            labelsColor = plugin.getConfig().getString("LabelsColor");
        }
        else { labelsColor = "#FFAA00"; }

        if (plugin.getConfig().contains("ValuesColor")) {
            valuesColor = plugin.getConfig().getString("ValuesColor");
        }
        else { valuesColor = "#FFFFFF"; }

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
