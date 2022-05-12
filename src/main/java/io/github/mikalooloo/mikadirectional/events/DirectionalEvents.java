package io.github.mikalooloo.mikadirectional.events;

import io.github.mikalooloo.mikadirectional.MikaDirectional;
import io.github.mikalooloo.mikadirectional.config.UserHandler;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * DirectionalEvents listens for and actiavtes upon player movement events.
 *
 * @author  Mikalooloo
 * @since   1.0-SNAPSHOT
 */
public class DirectionalEvents implements Listener {

    final private MikaDirectional plugin;

    public DirectionalEvents(MikaDirectional plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.hasChangedPosition()) {
            plugin.dUtil.sendUpdate(event.getPlayer(), event.getTo());
        }
    }

    @EventHandler
    public void joiningUser(PlayerJoinEvent event) {
        plugin.addUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void leavingUser(PlayerQuitEvent event) {
        plugin.users.get(event.getPlayer().getUniqueId()).saveUserFile();
        plugin.users.remove(event.getPlayer().getUniqueId());
    }
}
