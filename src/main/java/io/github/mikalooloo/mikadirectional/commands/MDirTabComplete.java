package io.github.mikalooloo.mikadirectional.commands;

import io.github.mikalooloo.mikadirectional.MikaDirectional;

import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import net.kyori.adventure.text.format.NamedTextColor;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

/**
 * Enables tab completion for the custom commands provided in <code>MDirCommands</code>,
 * providing players with the next possible choices for whatever command they're typing!
 *
 * @author  Mikalooloo
 * @version 1.0
 * @see     MDirCommands
 * @since   1.0
 */
public class MDirTabComplete implements TabCompleter {

    // VARIABLES
    final private MikaDirectional plugin;

    // CONSTRUCTOR
    public MDirTabComplete(@NotNull MikaDirectional plugin) { this.plugin = plugin; }

    // OVERRIDE METHOD
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            List<String> autoCompletes = new ArrayList<>();
            // true if (you do not need user perms) OR (you do and you have them)
            boolean canUse = (!plugin.getMDConfig().getNeedUserPerms() || sender.hasPermission("MikaDirectional.User"));
            boolean canReload = (!plugin.getMDConfig().getNeedReloadPerms() || sender.hasPermission("MikaDirectional.Reload"));

            if (args.length == 1) {
                if (canUse)
                {
                    autoCompletes.add("toggle");
                    autoCompletes.add("setColor");
                }
                if (canReload) {
                    autoCompletes.add("reload");
                }
            } else {
                if (canUse) {
                    if (args[0].equalsIgnoreCase("toggle")) { // /md toggle
                        autoCompletes.add("coords");
                        autoCompletes.add("direction");
                        autoCompletes.add("both");
                    }
                    else if (args[0].equalsIgnoreCase("setColor")) { // /md setColor
                        if (args.length == 2) {
                            autoCompletes.add("labels");
                            autoCompletes.add("values");
                        }
                        else { // /md setColor [labels | values]
                            autoCompletes.addAll(NamedTextColor.NAMES.keys());
                        }
                    }
                }
            }
            return autoCompletes;
        }
        return null;
    }
}
