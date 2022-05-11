package io.github.mikalooloo.mikadirectional.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class DirectionalCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("md")) {
            if (args.length <= 0) {
                sender.sendMessage(ChatColor.RED + "Mika's Directional Commands:");
                sender.sendMessage(ChatColor.WHITE + "/md");
                sender.sendMessage(ChatColor.GRAY + "Displays commands");
            } else {
                if (args[0].equalsIgnoreCase("toggle")) {
                    if (args[1].equalsIgnoreCase("coords")) {
                        // toggle coords
                    }
                    else if (args[1].equalsIgnoreCase("direction")) {
                        // toggle direction
                    }
                    else {
                        // toggle both
                    }
                }
            }
        }
        return true;
    }
}
