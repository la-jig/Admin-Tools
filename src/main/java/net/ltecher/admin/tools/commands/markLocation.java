package net.ltecher.admin.tools.commands;

import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class markLocation implements CommandExecutor {
    public markLocation() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Logger.getLogger("Admin Tools").info("Command must be executed as player");
            return false;
        } else {
            Player p = (Player)sender;
            Location origin = p.getLocation().add(2.0, 0.0, 0.0);

            for(int i = 0; i < 100; ++i) {
                origin.getBlock().setType(Material.COBBLESTONE);
                origin.add(0.0, 1.0, 0.0);
            }

            p.sendMessage("Marker Created at your location!");
            return true;
        }
    }
}

