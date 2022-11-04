package net.ltecher.admin.tools.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;

public class BlockBreakEvent implements Listener {
	private final Main plugin;

	public BlockBreakEvent(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
		Player p = event.getPlayer();

		if (plugin.frozenPlayers.contains(p.getName())) {
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You cannot break blocks while frozen!");
		} else {
			event.setCancelled(false);
		}
	}
}
