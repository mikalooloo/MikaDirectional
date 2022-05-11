package io.github.mikalooloo.mikadirectional;
// My classes
import io.github.mikalooloo.mikadirectional.util.DirectionalUtil;
import io.github.mikalooloo.mikadirectional.commands.DirectionalCommands;
import io.github.mikalooloo.mikadirectional.config.DirectionalConfig;
import io.github.mikalooloo.mikadirectional.events.DirectionalEvents;
// Bukkit classes
import org.bukkit.plugin.java.JavaPlugin;
// Java classes
import java.io.File;

public final class MikaDirectional extends JavaPlugin {

    public DirectionalUtil dUtil;
    public DirectionalConfig dConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Registering
        this.dUtil = new DirectionalUtil(this);
        this.dConfig = new DirectionalConfig(this);
        getCommand("mikadirectional").setExecutor(new DirectionalCommands());
        getServer().getPluginManager().registerEvents(new DirectionalEvents(this), this);

        // Create player folder
        File file = new File("plugins/MikaDirectional/players/");
        file.mkdirs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
