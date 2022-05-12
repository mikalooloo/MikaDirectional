package io.github.mikalooloo.mikadirectional.config;

import io.github.mikalooloo.mikadirectional.MikaDirectional;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class UserHandler {

    final private MikaDirectional plugin;
    final private UUID user;
    final private File userFile;
    final private YamlConfiguration userConfig;
    // Config Settings
    private boolean coords;
    private boolean direction;
    private String labelsColor;
    private String valuesColor;

    // Constructor
    public UserHandler(MikaDirectional plugin, UUID user) {
        this.plugin = plugin;
        this.user = user;
        userFile = new File(plugin.userFolderPath + user + ".yml");
        userConfig = YamlConfiguration.loadConfiguration(userFile);
        createUser();
    }

    // SETTERS
    public void setCoords(boolean coords) {
        this.coords = coords;
        userConfig.set("Coords", this.coords);
    }

    public void toggleCoords() {
        coords = !coords;
        userConfig.set("Coords", coords);
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
        userConfig.set("Direction", this.direction);
    }

    public void toggleDirection() {
        direction = !direction;
        userConfig.set("Direction", direction);
    }

    public void setLabelsColor(String labelsColor) {
        this.labelsColor = labelsColor;
        userConfig.set("LabelsColor", this.labelsColor);
    }

    public void setValuesColor(String valuesColor) {
        this.valuesColor = valuesColor;
        userConfig.set("ValuesColor", this.valuesColor);
    }

    // GETTERS
    public YamlConfiguration getUserFile() {
        return userConfig;
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

    // FUNCTIONS
    public void saveUserFile() {
        try {
            getUserFile().save(userFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void createUser() {
        if (!userFile.exists()) {
            coords = plugin.dConfig.coords;
            direction = plugin.dConfig.direction;
            labelsColor = plugin.dConfig.labelsColor;
            valuesColor = plugin.dConfig.valuesColor;

            userConfig.set("Coords", coords);
            userConfig.set("Direction", direction);
            userConfig.set("LabelsColor", labelsColor);
            userConfig.set("ValuesColor", valuesColor);
            saveUserFile();
        }
        else {
            coords = userConfig.getBoolean("Coords");
            direction = userConfig.getBoolean("Direction");
            labelsColor = userConfig.getString("LabelsColor");
            valuesColor = userConfig.getString("ValuesColor");
        }
    }
}
