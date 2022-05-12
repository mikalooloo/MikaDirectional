package io.github.mikalooloo.mikadirectional.commands;

import io.github.mikalooloo.mikadirectional.MikaDirectional;
import io.github.mikalooloo.mikadirectional.config.UserHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * DirectionalCommands handles various command responses.
 *
 * @author  Mikalooloo
 * @since   1.0-SNAPSHOT
 */
public class DirectionalCommands implements CommandExecutor {

    final private MikaDirectional plugin;

    public DirectionalCommands(MikaDirectional plugin) {
        this.plugin = plugin;
    }

    public void displayCommands(CommandSender sender) {
        sender.sendMessage(ChatColor.ITALIC + "Mika's Directional Commands:");
        sender.sendMessage(ChatColor.WHITE + "/md");
        sender.sendMessage(ChatColor.GRAY + "Displays commands");
        sender.sendMessage(ChatColor.WHITE + "/md toggle [coords | direction]");
        sender.sendMessage(ChatColor.GRAY + "Toggles the visibility of the coordinates or the facing direction");
        sender.sendMessage(ChatColor.WHITE + "/md setColor [labels | values] [color]");
        sender.sendMessage(ChatColor.GRAY + "Sets the color of the labels (X/Y/Z/Facing) or the values (numbers/directions)");
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            if (args.length <= 0) {
                displayCommands(sender);
            } else {
                Player player = (Player) sender;
                UserHandler user = plugin.users.get(player.getUniqueId());

                if (args[0].equalsIgnoreCase("toggle")) {
                    if (args.length == 1) {
                        // toggle both
                        user.toggleCoords();
                        user.toggleDirection();
                        sender.sendMessage("Coords are now " + (user.getCoords() ? "enabled" : "disabled" +
                                "and direction is now " + (user.getDirection() ? "enabled!" : "disabled!")));
                    }
                    else if (args[1].equalsIgnoreCase("coords")) {
                        // toggle coords
                        user.toggleCoords();
                        sender.sendMessage("Coords are now " + (user.getCoords() ? "enabled!" : "disabled!"));
                    }
                    else if (args[1].equalsIgnoreCase("direction")) {
                        // toggle direction
                        user.toggleDirection();
                        sender.sendMessage("Direction is now " + (user.getDirection() ? "enabled!" : "disabled!"));
                    }
                    else {
                        // not valid inputs
                        sender.sendMessage(ChatColor.RED + "Something about that command was not right.\n");
                        displayCommands(sender);
                    }
                }
                else if (args[0].equalsIgnoreCase("setColor")) {
                    if (args[1].equalsIgnoreCase("labels")) {
                        // set color of the labels
                        String newColor = args[2].startsWith("#") ? args[2] : "#" + args[2];
                        if (newColor.length() == 7) {
                            user.setLabelsColor(newColor);
                            sender.sendMessage("Labels color is now set to " + newColor);
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "The hex value entered is not valid!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("values")) {
                        // set color of the values
                        String newColor = args[2].startsWith("#") ? args[2] : "#" + args[2];
                        if (newColor.length() == 7) {
                            user.setValuesColor(newColor);
                            sender.sendMessage("Values color is now set to " + newColor);
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "The hex value entered is not valid!");
                        }
                    }
                    else {
                        // not valid inputs
                        sender.sendMessage(ChatColor.RED + "Something about that command was not right.\n");
                        displayCommands(sender);
                    }
                }
                else {
                    // not valid command
                    sender.sendMessage(ChatColor.RED + "Something about that command was not right.\n");
                    displayCommands(sender);
                }
            }
        }
        return true;
    }
}
