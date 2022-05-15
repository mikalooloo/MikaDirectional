package io.github.mikalooloo.mikadirectional.commands;

import io.github.mikalooloo.mikadirectional.MikaDirectional;
import io.github.mikalooloo.mikadirectional.config.MDirPlayerHandler;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import net.kyori.adventure.text.format.NamedTextColor;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Handles various command responses like <code>/md</code>, <code>/md toggle</code>, <code>/md setColor</code>
 *
 * @author  Mikalooloo
 * @version 1.0
 * @since   1.0
 */
public class MDirCommands implements CommandExecutor {

    // VARIABLES
    final private MikaDirectional plugin;
    final private Pattern compiledRegex = Pattern.compile("^#([A-Fa-f\\d]{6}|[A-Fa-f\\d]{3})$"); // used to check hex

    // CONSTRUCTOR
    public MDirCommands(@NotNull MikaDirectional plugin) { this.plugin = plugin; }

    // OVERRIDE METHOD
    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("mikadirectional") || command.getName().equalsIgnoreCase("md")) {
            // true if (you do not need user perms) OR (you do and you have them)
            boolean canUse = (!plugin.getMDConfig().getNeedUserPerms() || sender.hasPermission("MikaDirectional.User"));
            boolean canReload = (!plugin.getMDConfig().getNeedReloadPerms() || sender.hasPermission("MikaDirectional.Reload"));

            // /md
            if (args.length <= 0) {
                displayCommands(sender, canUse, canReload);
            } else {
                Player p = (Player) sender;
                MDirPlayerHandler player = plugin.getPlayer(p.getUniqueId());

                // /md toggle
                if (canUse && args[0].equalsIgnoreCase("toggle")) {
                    // /md toggle, /md toggle both
                    if (args.length == 1 || args[1].equalsIgnoreCase("both")) {
                        player.toggleCoords();
                        player.toggleDirection();
                        sender.sendMessage("Coords are now " + ChatColor.BOLD + (player.getCoords() ? "enabled" : "disabled") +
                                ChatColor.RESET + " and direction is now " + ChatColor.BOLD + (player.getDirection() ? "enabled!" : "disabled!"));
                    }
                    // /md toggle coords
                    else if (args[1].equalsIgnoreCase("coords")) {
                        player.toggleCoords();
                        sender.sendMessage("Coords are now " + ChatColor.BOLD + (player.getCoords() ? "enabled!" : "disabled!"));
                    }
                    // /md toggle direction
                    else if (args[1].equalsIgnoreCase("direction")) {
                        player.toggleDirection();
                        sender.sendMessage("Direction is now " + ChatColor.BOLD + (player.getDirection() ? "enabled!" : "disabled!"));
                    }
                    else { displayError(sender, canUse, canReload); } // if not enough inputs
                }
                // /md setColor
                else if (canUse && args[0].equalsIgnoreCase("setColor")) {
                    if (args.length < 3) { displayError(sender, canUse, canReload); } // if not enough inputs
                    // /md setColor labels [color]
                    else if (args[1].equalsIgnoreCase("labels")) {
                        String newColor = convertToHex(args[2]);
                        if (isValidHexCode(newColor)) {
                            player.setLabelsColor(newColor);
                            sender.sendMessage("Labels color is now set to " + ChatColor.BOLD + args[2]);
                        }
                        else { displayError(sender, canUse, canReload, "The hex value entered is not valid!"); }
                    }
                    // /md setColor values [color]
                    else if (args[1].equalsIgnoreCase("values")) {
                        String newColor = convertToHex(args[2]);
                        if (isValidHexCode(newColor)) {
                            player.setValuesColor(newColor);
                            sender.sendMessage("Values color is now set to " + ChatColor.BOLD + args[2]);
                        }
                        else { displayError(sender, canUse, canReload, "The hex value entered is not valid!"); }
                    }
                }
                else if (args[0].equalsIgnoreCase("reload")) {

                    if (canReload) {
                        plugin.getMDConfig().load();
                        sender.sendMessage("The config has been " + ChatColor.BOLD + "reloaded!");
                    }
                    else { // not enough permissions
                        displayError(sender, canUse, canReload, "You do not have the permission to execute this command.");
                        return true;
                    }

                }
                // not valid command
                else { displayError(sender, canUse, canReload); }
            }
        }
        return true;
    }

    // OTHER METHODS
    private void displayError(CommandSender sender, boolean canUse, boolean canReload) {
        sender.sendMessage(ChatColor.RED + "Something about that command was not right.\n");
        displayCommands(sender, canUse, canReload);
        sender.sendMessage(ChatColor.RED + "That didn't go right. Refer to the above!\n");
    }

    private void displayError(CommandSender sender, boolean canUse, boolean canReload, String customError) {
        sender.sendMessage(ChatColor.RED + customError + "\n");
        displayCommands(sender, canUse, canReload);
        sender.sendMessage(ChatColor.RED + "That didn't go right. Refer to the above!\n");
    }

    private void displayCommands(CommandSender sender, boolean canUse, boolean canReload) {
        sender.sendMessage(ChatColor.ITALIC + "Mika's Directional Commands:");
        if (!canUse) {
            sender.sendMessage(ChatColor.RED + "You do not have the permission to execute this command.");
            return;
        }
        sender.sendMessage(ChatColor.WHITE + "/md");
        sender.sendMessage(ChatColor.GRAY + "Displays commands");
        sender.sendMessage(ChatColor.WHITE + "/md toggle [coords | direction]");
        sender.sendMessage(ChatColor.GRAY + "Toggles the visibility of the coordinates or the facing direction");
        sender.sendMessage(ChatColor.WHITE + "/md setColor [labels | values] [color name/hex code]");
        sender.sendMessage(ChatColor.GRAY + "Sets the color of the labels (X/Y/Z/Facing) or the values (numbers/directions)");
        if (!canReload) { return; }
        sender.sendMessage(ChatColor.WHITE + "/md reload");
        sender.sendMessage(ChatColor.GRAY + "Refreshes the config file to reflect outside changes made since plugin started");
    }

    private boolean isValidHexCode(String code) {
        if (code == null) return false;
        Matcher codeMatcher = compiledRegex.matcher(code);
        return codeMatcher.matches();
    }

    private String convertToHex(String color) {
        if (color.startsWith("#")) {
            return color;
        } else {
            NamedTextColor test = NamedTextColor.NAMES.value(color);
            if (test != null) {
                return test.asHexString();
            } else {
                return "";
            }
        }
    }
}
