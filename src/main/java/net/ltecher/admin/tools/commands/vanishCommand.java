package net.ltecher.admin.tools.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class vanishCommand implements CommandExecutor {
    public ArrayList<String> vanished = new ArrayList<String>();

    public vanishCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
        	if (sender instanceof Player) {
        		Player p = (Player) sender;
        		if (!vanished.contains(p.getName())) {
        			p.setInvisible(true);
        			vanished.add(p.getName());
        			for (Player player : Bukkit.getOnlinePlayers()) {
        				player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.YELLOW + " left the game");
        			}
        			return true;
        		} else {
        			p.setInvisible(false);
        			vanished.remove(p.getName());
        			for (Player player : Bukkit.getOnlinePlayers()) {
        				player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.YELLOW + " joined the game");
        			}
        			return true;
        		}
        	} else {
        		sender.sendMessage("You must be a player to run this command!");
        	}
        } else {
        	sender.sendMessage(ChatColor.RED + "You do not have permission!");
        }
        
        return false;
    }
}

