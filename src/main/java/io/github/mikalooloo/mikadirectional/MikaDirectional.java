package io.github.mikalooloo.mikadirectional;
// Mika packages
import io.github.mikalooloo.mikadirectional.util.MDirUtil;
import io.github.mikalooloo.mikadirectional.config.MDirConfig;
import io.github.mikalooloo.mikadirectional.bstats.Metrics;
import io.github.mikalooloo.mikadirectional.config.MDirPlayerHandler;
import io.github.mikalooloo.mikadirectional.commands.MDirCommands;
import io.github.mikalooloo.mikadirectional.commands.MDirTabComplete;
import io.github.mikalooloo.mikadirectional.events.MDirEvents;
// Bukkit packages
import org.bukkit.plugin.java.JavaPlugin;
// Jetbrains packages
import org.jetbrains.annotations.NotNull;
// Java packages
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.io.File;

/**
 * MikaDirectional is the main class of the Minecraft plugin Mika's Directional.
 *
 * @author  Mikalooloo
 * @since   1.0
 */
public final class MikaDirectional extends JavaPlugin {

    // VARIABLES
    private String playerFolderPath = "/players/";
    private MDirUtil mdUtil;
    private MDirConfig mdConfig;
    private Metrics mdMetrics;
    private Map<UUID, MDirPlayerHandler> playersMap = new HashMap<>();


    // SETTERS
    public void addPlayer(@NotNull UUID playerID) {
        playersMap.put(playerID, new MDirPlayerHandler(this, playerID));
    }
    public void removePlayer(@NotNull UUID playerID) {
        playersMap.get(playerID).savePlayerFile();
        playersMap.remove(playerID);
    }

    // GETTERS
    public String getPlayerFolderPath() { return playerFolderPath; }
    public MDirUtil getMDUtil() { return mdUtil; }
    public MDirConfig getMDConfig() { return mdConfig; }
    public MDirPlayerHandler getPlayer(UUID playerID) {
        return playersMap.get(playerID);
    }

    // OVERRIDE METHODS
    @Override
    public void onEnable() {
        // Plugin startup logic
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

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
