package net.ltecher.admin.tools.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.ltecher.admin.tools.Main;

import java.util.ArrayList;

public class PlayerMoveEvent implements Listener {
    public ArrayList<String> frozen;
    private final Main plugin;

    public PlayerMoveEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(org.bukkit.event.player.PlayerMoveEvent event) {
        frozen = plugin.frozenPlayers;

        if (frozen.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }
}
