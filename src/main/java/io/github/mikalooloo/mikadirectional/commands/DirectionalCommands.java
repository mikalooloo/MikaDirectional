package io.github.mikalooloo.mikadirectional.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

/**
 * DirectionalCommands handles various command responses.
 *
 * @author  Mikalooloo
 * @since   1.0-SNAPSHOT
 */
public class DirectionalCommands implements CommandExecutor {

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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            if (args.length <= 0) {
                displayCommands(sender);
            } else {
                if (args[0].equalsIgnoreCase("toggle")) {
                    if (args.length == 1) {
                        // toggle both
                        sender.sendMessage("Toggle both");
                    }
                    else if (args[1].equalsIgnoreCase("coords")) {
                        // toggle coords
                        sender.sendMessage("Toggle coords");
                    }
                    else if (args[1].equalsIgnoreCase("direction")) {
                        // toggle direction
                        sender.sendMessage("Toggle direction");
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
                        sender.sendMessage("Set color of labels");
                    }
                    else if (args[1].equalsIgnoreCase("values")) {
                        // set color of the values
                        sender.sendMessage("Set color of values");
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
