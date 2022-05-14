package io.github.mikalooloo.mikadirectional.events;
// Mika packages
import io.github.mikalooloo.mikadirectional.MikaDirectional;
// Bukkit packages
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
// Jetbrains packages
import org.jetbrains.annotations.NotNull;

/**
 * MDirEvents listens for and activates upon specified player events (movement, join, quit).
 *
 * @author  Mikalooloo
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
        if (event.hasChangedPosition()) {
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
