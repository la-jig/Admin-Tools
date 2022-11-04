package net.ltecher.admin.tools.event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;


public class PlayerChatEvent implements Listener { 
	public ArrayList<String> Muted = new ArrayList<String>();
	public ArrayList<String> sendedMessages = new ArrayList<String>();
	
    public PlayerChatEvent() {
    }

    @SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
    	event.setCancelled(true);
    	
    	
    	if (Muted.contains(event.getPlayer().getName())) {
    		event.getPlayer().sendMessage(ChatColor.RED + "You cannot talk whilst muted!");
    	} else {
    		
    		boolean cancel = false;
    		String message = event.getMessage();
    		
    		for (String sendMessage : sendedMessages) {
    			if (sendMessage.equalsIgnoreCase("<" + event.getPlayer().getName() + "> " + message)) {
    				cancel = true;
    				event.getPlayer().sendMessage(ChatColor.RED + "You cannot send the same message twice!");
    			}
    		}
    		
    		if (cancel == false) {
	    		for (Player player : Bukkit.getOnlinePlayers()) {
	    			player.sendMessage("<" + event.getPlayer().getName() + "> " + message);
	    		}
	    		
	    		sendedMessages.add("<" + event.getPlayer().getName() + "> " + message);
    		}
    	}
    }
}

