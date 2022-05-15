package io.github.mikalooloo.mikadirectional.util;

import io.github.mikalooloo.mikadirectional.MikaDirectional;
import io.github.mikalooloo.mikadirectional.config.MDirPlayerHandler;

import org.bukkit.entity.Player;
import org.bukkit.Location;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import org.jetbrains.annotations.NotNull;

/**
 * Handles updating a player's action bar
 *
 * @author  Mikalooloo
 * @version 1.0
 * @since   1.0
 */
public class MDirUtil {

    // VARIABLES
    final private MikaDirectional plugin;

    // CONSTRUCTOR
    public MDirUtil(@NotNull MikaDirectional plugin) {
        this.plugin = plugin;
    }

    // METHODS
    /**
     * Determines if a player's action bar should be updated based on configs.
     * If so, sends the action bar message to the player.
     *
     * @param receiver   the player who has moved a block
     * @param location   the new location of the player
     */
    public void sendUpdate(Player receiver, Location location) {
        // check what configs player has set
        MDirPlayerHandler player = plugin.getPlayer(receiver.getUniqueId());
        TextComponent message = Component.text("");

        if (player.getCoords()) {
            message = message.append(Component.text("X: ").color(TextColor.fromHexString(player.getLabelsColor())))
                    .append(Component.text(Integer.toString(location.getBlockX())).color(TextColor.fromHexString(player.getValuesColor())))
                    .append(Component.text("   Y: ").color(TextColor.fromHexString(player.getLabelsColor())))
                    .append(Component.text(Integer.toString(location.getBlockY())).color(TextColor.fromHexString(player.getValuesColor())))
                    .append(Component.text("   Z: ").color(TextColor.fromHexString(player.getLabelsColor())))
                    .append(Component.text(Integer.toString(location.getBlockZ())).color(TextColor.fromHexString(player.getValuesColor())));
        }
        if (player.getDirection()) {
            message = message.append(Component.text("   Facing: ").color(TextColor.fromHexString(player.getLabelsColor())))
                    .append(Component.text(getDirection(location.getYaw())).color(TextColor.fromHexString(player.getValuesColor())));
        }

        // this is checked so action bar is not accidentally wiped clean if player has both disabled
        if (!message.equals(Component.text(""))) {
            updateActionBar(receiver, message);
        }
    }

    private void updateActionBar(@NotNull Player player, @NotNull TextComponent message) {
        try {
            player.sendActionBar(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDirection(float yaw) {
        float rot = (yaw - 90) % 360;
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
}
