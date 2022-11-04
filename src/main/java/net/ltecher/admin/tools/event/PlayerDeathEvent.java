package net.ltecher.admin.tools.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import net.ltecher.admin.tools.Main;

public class PlayerDeathEvent implements Listener {
    private Main plugin;

    public PlayerDeathEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        Player player = event.getPlayer();

        if (plugin.getConfig().getBoolean("clear-inv-on-death") == true) {
            Inventory inventory = player.getInventory();

            inventory.clear();
        }
    }
}
