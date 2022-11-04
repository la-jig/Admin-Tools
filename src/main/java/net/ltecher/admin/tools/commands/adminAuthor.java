package net.ltecher.admin.tools.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class adminAuthor implements CommandExecutor {
    public adminAuthor() {
    }

    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        sender.sendMessage("Plugin by Lawrence\nMinecraft Name: lawrencewil1030");
        return true;
    }
}

