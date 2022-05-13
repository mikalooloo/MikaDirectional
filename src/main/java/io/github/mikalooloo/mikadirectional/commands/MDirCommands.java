package io.github.mikalooloo.mikadirectional.commands;
// Mika classes
import io.github.mikalooloo.mikadirectional.MikaDirectional;
import io.github.mikalooloo.mikadirectional.config.MDirPlayerHandler;
// Bukkit classes
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
// Kyori classes
import net.kyori.adventure.text.format.NamedTextColor;
// Jetbrains classes
import org.jetbrains.annotations.NotNull;
// Java classes
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * MDirCommands handles various command responses like /md, /md toggle, /md setColor.
 *
 * @author  Mikalooloo
 * @since   1.0
 */
public class MDirCommands implements CommandExecutor {

    // VARIABLES
    final private MikaDirectional plugin;
    final private Pattern compiledRegex = Pattern.compile("^#([A-Fa-f\\d]{6}|[A-Fa-f\\d]{3})$");

    // CONSTRUCTOR
    public MDirCommands(MikaDirectional plugin) { this.plugin = plugin; }

    // OVERRIDE METHOD
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            // /md
            if (args.length <= 0) {
                displayCommands(sender);
            } else {
                Player p = (Player) sender;
                MDirPlayerHandler player = plugin.getPlayer(p.getUniqueId());

                // /md toggle
                if (args[0].equalsIgnoreCase("toggle")) {
                    // /md toggle, /md toggle both
                    if (args.length == 1 || args[1].equalsIgnoreCase("both")) {
                        player.toggleCoords();
                        player.toggleDirection();
                        sender.sendMessage("Coords are now " + (player.getCoords() ? "enabled" : "disabled" +
                                " and direction is now " + (player.getDirection() ? "enabled!" : "disabled!")));
                    }
                    // /md toggle coords
                    else if (args[1].equalsIgnoreCase("coords")) {
                        player.toggleCoords();
                        sender.sendMessage("Coords are now " + (player.getCoords() ? "enabled!" : "disabled!"));
                    }
                    // /md toggle direction
                    else if (args[1].equalsIgnoreCase("direction")) {
                        player.toggleDirection();
                        sender.sendMessage("Direction is now " + (player.getDirection() ? "enabled!" : "disabled!"));
                    }
                    // not valid inputs
                    else { displayError(sender); }
                }
                // /md setColor
                else if (args[0].equalsIgnoreCase("setColor")) {
                    // if not enough inputs
                    if (args.length < 3) { displayError(sender); }
                    // /md setColor labels [color]
                    else if (args[1].equalsIgnoreCase("labels")) {
                        String newColor = args[2].startsWith("#") ? args[2] : NamedTextColor.NAMES.value(args[2]).asHexString();
                        if (isValidHexCode(newColor)) {
                            player.setLabelsColor(newColor);
                            sender.sendMessage("Labels color is now set to " + newColor);
                        }
                        else { displayError(sender, "The hex value entered is not valid!"); }
                    }
                    // /md setColor values [color]
                    else if (args[1].equalsIgnoreCase("values")) {
                        String newColor = args[2].startsWith("#") ? args[2] : NamedTextColor.NAMES.value(args[2]).asHexString();
                        if (isValidHexCode(newColor)) {
                            player.setValuesColor(newColor);
                            sender.sendMessage("Values color is now set to " + newColor);
                        }
                        else { displayError(sender, "The hex value entered is not valid!"); }
                    }
                }
                // not valid command
                else { displayError(sender); }
            }
        }
        return true;
    }

    // OTHER METHODS
    /**
     * Sends player a generic error message and all of the valid plugin commands.
     *
     * @param sender   the player who sent the invalid command
     */
    private void displayError(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Something about that command was not right.\n");
        displayCommands(sender);
    }

    /**
     * Sends player a custom error message and all of the valid plugin commands.
     *
     * @param sender        the player who sent the invalid command
     * @param customError   the custom error message to send to player
     */
    private void displayError(CommandSender sender, String customError) {
        sender.sendMessage(ChatColor.RED + customError + "\n");
        displayCommands(sender);
    }

    /**
     * Sends a player all of the valid plugin commands.
     *
     * @param sender   the player to send the commands to
     */
    private void displayCommands(CommandSender sender) {
        sender.sendMessage(ChatColor.ITALIC + "Mika's Directional Commands:");
        sender.sendMessage(ChatColor.WHITE + "/md");
        sender.sendMessage(ChatColor.GRAY + "Displays commands");
        sender.sendMessage(ChatColor.WHITE + "/md toggle [coords | direction]");
        sender.sendMessage(ChatColor.GRAY + "Toggles the visibility of the coordinates or the facing direction");
        sender.sendMessage(ChatColor.WHITE + "/md setColor [labels | values] [color]");
        sender.sendMessage(ChatColor.GRAY + "Sets the color of the labels (X/Y/Z/Facing) or the values (numbers/directions)");
    }

    /**
     * Given a String, returns whether it is a valid hexadecimal value
     *
     * @param code   the String to test whether it's a hexadecimal
     * @return       Boolean set to true if code is valid, false if code is not valid
     */
    private boolean isValidHexCode(String code) {
        if (code == null) return false;
        Matcher codeMatcher = compiledRegex.matcher(code);
        return codeMatcher.matches();
    }
}
