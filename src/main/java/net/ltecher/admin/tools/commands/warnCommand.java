package net.ltecher.admin.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        boolean success = true;
        if (sender.isOp()) {
            if (args.length == 2) {
                Player M = Bukkit.getPlayer(args[0]);

                if (M == null) {
                    sender.sendMessage("Player is not online!");
                    success = false;
                } else {
                    String message = "You have been warned by " + sender.getName() + ": \n" + args[1];
                    M.sendMessage(message);
                }
            } else {
                success = false;
            }
        } else {
            sender.sendMessage("You must be op to run this command!");
            success = false;
        }
        return success;
    }
}
