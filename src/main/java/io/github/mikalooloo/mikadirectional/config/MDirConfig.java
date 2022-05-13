package io.github.mikalooloo.mikadirectional.config;
// Mika classes
import io.github.mikalooloo.mikadirectional.MikaDirectional;

/**
 * MDirConfig handles the default config file, finding the values set within it and providing defaults if not found.
 *
 * @author  Mikalooloo
 * @since   1.0
 */
public class MDirConfig {

    // VARIABLES
    final private boolean coords;
    final private boolean direction;
    final private String labelsColor;
    final private String valuesColor;

    // CONSTRUCTOR
    public MDirConfig(MikaDirectional plugin) {

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
            labelsColor = "#FFAA00";
        }

        if (plugin.getConfig().contains("ValuesColor")) {
            valuesColor = plugin.getConfig().getString("ValuesColor");
        }
        else {
            valuesColor = "#FFFFFF";
        }
    }

    // GETTERS
    public boolean getDefaultCoords() { return coords; }
    public boolean getDefaultDirection() { return direction; }
    public String getDefaultLabelsColor() { return labelsColor; }
    public String getDefaultValuesColor() { return valuesColor; }
}
