package net.ltecher.admin.tools.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class tempBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean success = true;
        if (sender.isOp()) {
            Player M = Bukkit.getPlayer(args[0]);

            if (M == null) {
                sender.sendMessage("Player not online");
                success = false;
            } else {
                if (args.length == 3) {
                    String reason = "Banned from server: \n" + args[1];
                    try {
                        Bukkit.getBanList(BanList.Type.NAME).addBan(M.getName(), reason, new Date(System.currentTimeMillis() + Integer.parseInt(args[2]) * Integer.parseInt(args[3]) * 1000), null);
                        M.kickPlayer(reason);
                        sender.sendMessage("Successfully kicked " + args[0]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("Invalid ban time " + args[2] + ", " + args[3]);
                        success = false;
                    }
                } else {
                    success = false;
                }
            }
        } else {
            sender.sendMessage("You must be op to run this command!");
        }
        return success;
    }
}
