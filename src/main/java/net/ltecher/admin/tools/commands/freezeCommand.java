package net.ltecher.admin.tools.commands;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class freezeCommand implements CommandExecutor {
    private final Main plugin;

    public freezeCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (!(args.length == 0)) {
                Player M = Bukkit.getPlayer(args[0]);

                if (!(M == null)) {
                    if (!plugin.frozenPlayers.contains(M.getName())) {
                        plugin.frozenPlayers.add(M.getName());
                        M.sendMessage(ChatColor.RED + "You have been frozen!");
                    } else {
                        plugin.frozenPlayers.remove(M.getName());
                    }
                    sender.sendMessage(ChatColor.GREEN + "Success!");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "Player is not online!");
                }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "I'm sorry, you do not have permission to use this command.");
        }
        return false;
    }
}
