package io.github.mikalooloo.mikadirectional.config;

import io.github.mikalooloo.mikadirectional.MikaDirectional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DirectionalConfig handles the config files for Mika's Directional.
 *
 * @author  Mikalooloo
 * @since   1.0-SNAPSHOT
 */
public class DirectionalConfig {

    // Config Settings
    public boolean coords;
    public boolean direction;
    public String labelsColor;
    public String valuesColor;

    public DirectionalConfig(MikaDirectional plugin) {

        if (plugin.getConfig().contains("Coords")) {
            coords = plugin.getConfig().getBoolean("Coords");
        }
        else {
            coords = false;
        }

        if (plugin.getConfig().contains("Direction")) {
            direction = plugin.getConfig().getBoolean("Direction");
        }
        else {
            direction = false;
        }

        if (plugin.getConfig().contains("LabelsColor")) {
            labelsColor = plugin.getConfig().getString("LabelsColor");
        }
        else {
            labelsColor = "#AAAAAA";
        }

        if (plugin.getConfig().contains("ValuesColor")) {
            valuesColor = plugin.getConfig().getString("ValuesColor");
        }
        else {
            valuesColor = "#FFFFFF";
        }
    }

}
