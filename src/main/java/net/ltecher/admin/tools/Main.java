package net.ltecher.admin.tools;

import net.ltecher.admin.tools.commands.*;
import net.ltecher.admin.tools.event.*;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Main extends JavaPlugin {
	private Logger logger = this.getLogger();
	public ArrayList<String> frozenPlayers = new ArrayList<String>();
	public vanishCommand VanishCommand = new vanishCommand();
	public PlayerChatEvent muteEvent = new PlayerChatEvent();
	public PlayerDamageEvent playerDamage = new PlayerDamageEvent(this);
	public muteCommand mute = new muteCommand(this);
	public final File data = new File(getDataFolder() + File.separator + "data.yml");
	public final FileConfiguration config = YamlConfiguration.loadConfiguration(data);

	public static final String PLUGIN_ID = "AdminTools";

    public void onEnable() {
    	
    	if (!data.exists()) {
    		try {
    			data.createNewFile();
    		} catch (Exception e) {
    			logger.warning("A error occurred while creating data file. You will not be able to save plugin data!");
				e.printStackTrace();
    		}
    	}
    	
    	List<String> MutedList = config.getStringList("Muted.players");
    	for (String M : MutedList) {
    		muteEvent.Muted.add(M);
    	}
    	
    	List<String> vanishedList = config.getStringList("World.vanished");
    	
    	for (String V : vanishedList) {
    		VanishCommand.vanished.add(V);
    	}

		List<String> frozenList = config.getStringList("World.frozen");

		for (String F : frozenList) {
			frozenPlayers.add(F);
		}

		
        
        // Register the commands
        this.getServer().getPluginCommand("mark-location").setExecutor(new markLocation());
		if (!getConfig().getString("discord-link").equalsIgnoreCase("None")) {
        	this.getServer().getPluginCommand("discord").setExecutor(new discordCommand());
		}
        this.getServer().getPluginCommand("vanish").setExecutor(VanishCommand);
        this.getServer().getPluginCommand("mute").setExecutor(mute);
        this.getServer().getPluginCommand("adminAuthor").setExecutor(new adminAuthor());
        this.getServer().getPluginCommand("pvp").setExecutor(new pvpCommand(this));
        this.getServer().getPluginCommand("tempban").setExecutor(new tempBan());
        this.getServer().getPluginCommand("warn").setExecutor(new warnCommand());
        this.getServer().getPluginCommand("report").setExecutor(new reportCommand());
		this.getServer().getPluginCommand("freeze").setExecutor(new freezeCommand(this));
        this.getServer().getPluginCommand("server_stop").setExecutor(new shutdownCommand(this));
        
        
        // Register events handlers
        Bukkit.getPluginManager().registerEvents(muteEvent, this);
        Bukkit.getPluginManager().registerEvents(playerDamage, this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakEvent(this), this);
        
        logger.info("AdminTools has been enabled.");
        
        
        this.saveDefaultConfig();
    }
    

    public void onDisable() {	
    	config.set("Muted.players", mute.Muted);
    	config.set("World.pvp", playerDamage.pvpAllowed);
    	config.set("World.vanished", VanishCommand.vanished);
		config.set("World.frozen", frozenPlayers);
    	
    	try {
    		config.save(data);
    	} catch (Exception e) {
    		logger.severe("ERROR: Failed to save data: " + e.getMessage());
    	}
    	
    	
        logger.info("AdminTools has been disabled.");
    }
}
