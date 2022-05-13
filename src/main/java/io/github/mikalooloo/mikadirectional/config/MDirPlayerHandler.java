package io.github.mikalooloo.mikadirectional.config;
// Mika classes
import io.github.mikalooloo.mikadirectional.MikaDirectional;
// Bukkit classes
import org.bukkit.configuration.file.YamlConfiguration;
// Java classes
import java.util.UUID;
import java.io.File;

/**
 * MDirPlayerHandler handles the per player config files, creating files and
 * allowing other classes access to its contents.
 *
 * @author  Mikalooloo
 * @since   1.0
 */
public class MDirPlayerHandler {

    // VARIABLES
    final private MikaDirectional plugin;
    final private UUID playerID;
    final private File playerFile;
    final private YamlConfiguration playerConfig;
    // Config Settings
    private boolean coords;
    private boolean direction;
    private String labelsColor;
    private String valuesColor;

    // CONSTRUCTOR
    public MDirPlayerHandler(MikaDirectional plugin, UUID playerID) {
        this.plugin = plugin;
        this.playerID = playerID;
        playerFile = new File(plugin.getPlayerFolderPath() + playerID + ".yml");
        playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        createPlayer();
    }

    // SETTERS
    public void setCoords(boolean coords) { this.coords = coords; }
    public void toggleCoords() { coords = !coords; }
    public void setDirection(boolean direction) { this.direction = direction; }
    public void toggleDirection() { direction = !direction; }
    public void setLabelsColor(String labelsColor) { this.labelsColor = labelsColor; }
    public void setValuesColor(String valuesColor) { this.valuesColor = valuesColor; }

    // GETTERS
    public YamlConfiguration getPlayerFile() {
        return playerConfig;
    }
    public boolean getCoords() {
        return coords;
    }
    public boolean getDirection() {
        return direction;
    }
    public String getLabelsColor() {
        return labelsColor;
    }
    public String getValuesColor() {
        return valuesColor;
    }

    // METHODS
    /**
     * Creates a config file for new players and sets the values of the object's variables.
     */
    private void createPlayer() {
        if (!playerFile.exists()) {
            // if new, set values as default
            coords = plugin.getMDConfig().getDefaultCoords();
            direction = plugin.getMDConfig().getDefaultDirection();
            labelsColor = plugin.getMDConfig().getDefaultLabelsColor();
            valuesColor = plugin.getMDConfig().getDefaultValuesColor();

            playerConfig.set("Coords", coords);
            playerConfig.set("Direction", direction);
            playerConfig.set("LabelsColor", labelsColor);
            playerConfig.set("ValuesColor", valuesColor);
            savePlayerFile();
        }
        else {
            // if not new, set values from their file
            coords = playerConfig.getBoolean("Coords");
            direction = playerConfig.getBoolean("Direction");
            labelsColor = playerConfig.getString("LabelsColor");
            valuesColor = playerConfig.getString("ValuesColor");
        }
    }

    /**
     * Saves the object's variables to its config file.
     */
    public void savePlayerFile() {
        try {
            playerConfig.set("Coords", coords);
            playerConfig.set("Direction", direction);
            playerConfig.set("LabelsColor", labelsColor);
            playerConfig.set("ValuesColor", valuesColor);
            getPlayerFile().save(playerFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
