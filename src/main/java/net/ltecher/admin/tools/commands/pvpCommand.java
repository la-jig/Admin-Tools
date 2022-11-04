package net.ltecher.admin.tools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;


public class pvpCommand implements CommandExecutor {
    private Main plugin;

	public pvpCommand(Main plugin) {
    	this.plugin = plugin;
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			 String[] args) {
		if (sender.isOp()) {
			if (plugin.playerDamage.pvpAllowed == true) {
				plugin.playerDamage.pvpAllowed = false;
				sender.sendMessage(ChatColor.GREEN + "PVP has been disabled!");
				return true;
			} else {
				plugin.playerDamage.pvpAllowed = true;
				sender.sendMessage(ChatColor.GREEN + "PVP has been enabled!");
				return true;
			}
		}
		return false;
	}
}
