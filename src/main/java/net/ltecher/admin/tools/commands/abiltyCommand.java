package net.ltecher.admin.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.ltecher.admin.tools.Main;

public class abiltyCommand implements CommandExecutor {
    private Main plugin;

    public abiltyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            if (args.length >= 3) {
                Player M = Bukkit.getPlayer(args[0]);

                if (!(M == null)) {
                    boolean value = Boolean.parseBoolean(args[2]);

                    if (args[1] == "frozen") {
                        if (value == true) {
                            plugin.frozenPlayers.add(M.getName());
                        } else {
                            plugin.frozenPlayers.remove(M.getName());
                        }
                    } else if (args[1] == "mute") {
                        if (value == true) {
                           plugin.muteEvent.Muted.add(M.getName());
                        } else {
                            plugin.muteEvent.Muted.remove(M.getName());
                        }
                    }
                    return true;
                } else {
                    
                }
            }
        }
        return false;
    }
    
}