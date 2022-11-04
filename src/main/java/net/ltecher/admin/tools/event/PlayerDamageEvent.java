package net.ltecher.admin.tools.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.ltecher.admin.tools.Main;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;

public class PlayerDamageEvent implements Listener {
	private final Main plugin;
	private ArrayList<String> frozen;
	public boolean pvpAllowed = true;

	public PlayerDamageEvent(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event) {
		frozen = plugin.frozenPlayers;
		if (frozen.contains(event.getEntity().getName())) {
			event.setCancelled(true);
			event.getDamager().sendMessage(ChatColor.RED + "You cannot damage a frozen player!");
		} else if (frozen.contains(event.getDamager().getName())) {
			event.setCancelled(true);
			event.getDamager().sendMessage(ChatColor.RED + "You cannot damage players while frozen!");
		} else if (pvpAllowed == false) {
			event.setCancelled(true);
			event.getDamager().sendMessage(ChatColor.RED + "Hey! You are not allowed to pvp here!");
		} else {
			event.setCancelled(false);
		}
	}
}
