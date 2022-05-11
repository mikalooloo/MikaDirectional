package io.github.mikalooloo.mikadirectional.util;
// My classes
import io.github.mikalooloo.mikadirectional.MikaDirectional;
// Bukkit classes
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
// Other classes
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

/**
 * DirectionalUtil handles updating a player's action bar.
 *
 * @author  Mikalooloo
 * @since   1.0-SNAPSHOT
 */
public class DirectionalUtil {

    final private MikaDirectional plugin;

    /**
     * Updates a player's action bar with message.
     *
     * @param player    the player whose action bar will be updated
     * @param message   the message that will be displayed
     */
    private void updateActionBar(Player player, TextComponent message) {
        try {
            player.sendActionBar(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Given a yaw, returns a cardinal direction string.
     *
     * @param yaw   how much a player is looking left or right
     * @return      String set to be a cardinal direction if calculated successfully; "" otherwise.
     */
    private String getDirection(double yaw) {
        double rot = (yaw - 90) % 360;
        if (rot < 0) {
            rot += 360;
        }
        if (0 <= rot && rot < 22.5) {
            return "West";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northwest";
        } else if (67.5 <= rot && rot < 112.5) {
            return "North";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Northeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "East";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southeast";
        } else if (247.5 <= rot && rot < 292.5) {
            return "South";
        } else if (292.5 <= rot && rot < 337.5) {
            return "Southwest";
        } else if (337.5 <= rot && rot < 360.0) {
            return "West";
        } else {
            return "";
        }
    }

    public DirectionalUtil(MikaDirectional plugin) {
        this.plugin = plugin;
    }

    /**
     * Determines if a player's action bar should be updated based on configs.
     * If so, sends the action bar message to the player.
     *
     * @see              #updateActionBar(Player, TextComponent) 
     * @param receiver   the player who has moved a block
     * @param location   the new location of the player
     */
    public void sendUpdate(Player receiver, Location location) {
        // check what configs player has set
        TextComponent message = Component.text("X: ", NamedTextColor.GRAY)
                .append(Component.text(Integer.toString(location.getBlockX()), NamedTextColor.WHITE))
                .append(Component.text("   Y: ", NamedTextColor.GRAY))
                .append(Component.text(Integer.toString(location.getBlockY()), NamedTextColor.WHITE))
                .append(Component.text("   Z: ", NamedTextColor.GRAY))
                .append(Component.text(Integer.toString(location.getBlockZ()), NamedTextColor.WHITE))
                .append(Component.text("   Facing: ", NamedTextColor.GRAY))
                .append(Component.text(getDirection(location.getYaw()), NamedTextColor.WHITE));
        updateActionBar(receiver, message);
    }
}
