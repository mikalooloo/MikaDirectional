package io.github.mikalooloo.mikadirectional.events;

import io.github.mikalooloo.mikadirectional.MikaDirectional;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

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
}
