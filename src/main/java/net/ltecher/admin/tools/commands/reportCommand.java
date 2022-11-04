package net.ltecher.admin.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class reportCommand implements CommandExecutor {
    public ArrayList<String> admins = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        boolean success = true;
        if (args.length == 3) {
            Player M = Bukkit.getPlayer(args[0]);

            if (M == null) {
                sender.sendMessage("Player not online!");
                success = false;
            } else {
                String message = "Player " + M.getName() + " Has been reported by " + sender.getName() + ": \n" + args[1];
                Player A = Bukkit.getPlayer(args[2]);

                if (A == null) {
                    sender.sendMessage("Admin not online!");
                    success = false;
                } else {
                    if (A.isOp()) {
                        A.sendMessage(message);
                        sender.sendMessage("Successfully reported " + M.getName());
                    } else {
                        sender.sendMessage("Player is not a admin!");
                        success = false;
                    }
                }
            }
        } else if (args.length == 2) {
            Player M = Bukkit.getPlayer(args[0]);
            String message = "Player " + M.getName() + " Has been reported by " + sender.getName() + ": \n" + args[1];


            for (Player admin : Bukkit.getOnlinePlayers()) {
                if (admin.isOp()) {
                    admin.sendMessage(message);
                }
            }
            sender.sendMessage("Successfully reported " + M.getName());
        } else {
            success = false;
        }
        return success;
    }
}
