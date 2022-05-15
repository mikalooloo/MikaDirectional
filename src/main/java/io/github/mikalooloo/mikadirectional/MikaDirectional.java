package io.github.mikalooloo.mikadirectional;

import io.github.mikalooloo.mikadirectional.bstats.Metrics;
import io.github.mikalooloo.mikadirectional.commands.MDirCommands;
import io.github.mikalooloo.mikadirectional.commands.MDirTabComplete;
import io.github.mikalooloo.mikadirectional.config.MDirConfig;
import io.github.mikalooloo.mikadirectional.config.MDirPlayerHandler;
import io.github.mikalooloo.mikadirectional.events.MDirEvents;
import io.github.mikalooloo.mikadirectional.util.MDirUtil;

import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Starts up the plugin
 *
 * @author  Mikalooloo
 * @version 1.0
 * @since   1.0
 */
public final class MikaDirectional extends JavaPlugin {

    // VARIABLES
    private String playerFolderPath = "/players/"; // where player config files are placed and searched for
    private MDirUtil mdUtil;
    private MDirConfig mdConfig;
    private Metrics mdMetrics; // sets up bStats
    private Map<UUID, MDirPlayerHandler> playersMap = new HashMap<>(); // holds all online players & their configs

    // "SETTERS"
    public void addPlayer(@NotNull UUID playerId) {
        playersMap.put(playerId, new MDirPlayerHandler(this, playerId));
    }
    public void removePlayer(@NotNull UUID playerId) {
        playersMap.get(playerId).savePlayerFile();
        playersMap.remove(playerId);
    }

    // GETTERS
    public String getPlayerFolderPath() { return playerFolderPath; }
    public MDirUtil getMDUtil() { return mdUtil; }
    public MDirConfig getMDConfig() { return mdConfig; }
    public MDirPlayerHandler getPlayer(UUID playerID) { return playersMap.get(playerID); }

    // OVERRIDE METHODS
    /** Plugin startup logic */
    @Override
    public void onEnable() {
        saveDefaultConfig();

        // Registering
        mdUtil = new MDirUtil(this);
        mdConfig = new MDirConfig(this);
        mdMetrics = new Metrics(this, 15182); // plugin id: 15182
        getCommand("mikadirectional").setExecutor(new MDirCommands(this));
        getCommand("mikadirectional").setTabCompleter(new MDirTabComplete(this));
        getServer().getPluginManager().registerEvents(new MDirEvents(this), this);

        // Create player folder
        playerFolderPath  = getDataFolder() + playerFolderPath;
        File file = new File(playerFolderPath);
        try {
            if (!file.mkdirs()) getLogger().info("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Plugin shutdown logic */
    @Override
    public void onDisable() { }
}
