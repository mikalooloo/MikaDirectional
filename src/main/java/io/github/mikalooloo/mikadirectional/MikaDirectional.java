package io.github.mikalooloo.mikadirectional;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class MikaDirectional extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Create player folder
        File file = new File("plugins/MikaDirectional/players/");
        file.mkdirs();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
