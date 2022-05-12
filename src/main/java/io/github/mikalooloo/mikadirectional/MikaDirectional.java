package io.github.mikalooloo.mikadirectional;
// My classes
import io.github.mikalooloo.mikadirectional.config.UserHandler;
import io.github.mikalooloo.mikadirectional.util.DirectionalUtil;
import io.github.mikalooloo.mikadirectional.commands.DirectionalCommands;
import io.github.mikalooloo.mikadirectional.config.DirectionalConfig;
import io.github.mikalooloo.mikadirectional.events.DirectionalEvents;
// Bukkit classes
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
// Java classes
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MikaDirectional extends JavaPlugin {

    public String userFolderPath = "/players/";
    public DirectionalUtil dUtil;
    public DirectionalConfig dConfig;
    public Map<UUID, UserHandler> users = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        // Registering
        this.dUtil = new DirectionalUtil(this);
        this.dConfig = new DirectionalConfig(this);
        getCommand("mikadirectional").setExecutor(new DirectionalCommands(this));
        getServer().getPluginManager().registerEvents(new DirectionalEvents(this), this);

        // Create player folder
        userFolderPath  = getDataFolder() + userFolderPath;
        File file = new File(userFolderPath);
        try {
            file.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    public void addUser(UUID user) {
        users.put(user, new UserHandler(this, user));
    }
}
