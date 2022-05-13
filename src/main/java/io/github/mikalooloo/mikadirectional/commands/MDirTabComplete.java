package io.github.mikalooloo.mikadirectional.commands;
// Bukkit classes
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
// Kyori classes
import net.kyori.adventure.text.format.NamedTextColor;
// Jetbrains classes
import org.jetbrains.annotations.NotNull;
// Java classes
import java.util.List;
import java.util.ArrayList;

/**
 * MDirTabComplete enables tab completion for the custom commands provided in MDirCommands,
 * providing players with the next possible choices for whatever command they're typing!
 *
 * @see     MDirCommands
 * @author  Mikalooloo
 * @since   1.0
 */
public class MDirTabComplete implements TabCompleter {

    // OVERRIDE METHOD
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            List<String> autoCompletes = new ArrayList<>();
            if (args.length == 1) {
                autoCompletes.add("toggle");
                autoCompletes.add("setColor");
            } else {
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
            return autoCompletes;
        }
        return null;
    }
}
