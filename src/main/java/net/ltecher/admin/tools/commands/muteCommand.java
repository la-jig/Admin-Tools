package net.ltecher.admin.tools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;

public class muteCommand implements CommandExecutor {
	public ArrayList<String> Muted;
	private Main plugin;
	
    public muteCommand(Main plugin) {
    	this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	Muted = this.plugin.muteEvent.Muted;
    	
        if (sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage("/mute [Player]");
            } else {
                Player M = Bukkit.getPlayer(args[0]);
                if (M == null) {
                    sender.sendMessage(ChatColor.RED + "Player not online!");
                    return false;
                } else if (Muted.contains(M.getName())) {
                    Muted.remove(M.getName());
                    sender.sendMessage(ChatColor.GREEN + "Unmuted player successfuly!");
                } else {
                    Muted.add(M.getName());
                    M.sendMessage(ChatColor.RED + "You have been Muted by " + ChatColor.RED + sender.getName());
                    sender.sendMessage(ChatColor.GREEN + "Player Successfully Muted!");
                }
            }
        } else {
        	sender.sendMessage(ChatColor.RED + "I'm sorry, you do not have permission to use this command.");
        }
        return true;
    }
}
