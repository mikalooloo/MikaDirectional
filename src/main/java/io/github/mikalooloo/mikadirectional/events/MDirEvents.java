package io.github.mikalooloo.mikadirectional.events;

import io.github.mikalooloo.mikadirectional.MikaDirectional;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.jetbrains.annotations.NotNull;

/**
 * Listens for and activates upon specified player events (movement, join, quit)
 * so config files can be created and action bars are updated
 *
 * @author  Mikalooloo
 * @version 1.0
 * @since   1.0
 */
public class MDirEvents implements Listener {

    // VARIABLES
    final private MikaDirectional plugin;

    // CONSTRUCTOR
    public MDirEvents(@NotNull MikaDirectional plugin) {
        this.plugin = plugin;
    }

    // EVENT HANDLER METHODS
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.hasChangedOrientation()) { // event.hasChangedPosition()
            plugin.getMDUtil().sendUpdate(event.getPlayer(), event.getTo());
        }
    }

    @EventHandler
    public void joiningPlayer(PlayerJoinEvent event) {
        plugin.addPlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void leavingPlayer(PlayerQuitEvent event) {
        plugin.removePlayer(event.getPlayer().getUniqueId());
    }
}
