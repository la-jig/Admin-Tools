package net.ltecher.admin.tools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class discordCommand implements CommandExecutor {
    private final Plugin plugin = Bukkit.getPluginManager().getPlugin("AdminTools");
    public discordCommand() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(plugin.getConfig().getString("discord-link"));
        return true;
    }
}

