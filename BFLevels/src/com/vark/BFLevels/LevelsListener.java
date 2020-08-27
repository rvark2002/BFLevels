package com.vark.BFLevels;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;



public class LevelsListener implements Listener
{

	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	public LevelsListener(Main plugin) {

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		
		dataCreate(event.getPlayer());
		
		initializeScoreboard(event.getPlayer());

	}
	
	
	
	
	
	
	
	
	
	
	public void initializeScoreboard(Player p)
	{
		
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		
		Scoreboard scoreboard = manager.getNewScoreboard();
		
		
		Objective objective = scoreboard.registerNewObjective("Stats", "BF",ChatColor.GOLD+""+ChatColor.BOLD+"Blockfront II");
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score score = objective.getScore(ChatColor.YELLOW+""+ChatColor.BOLD+"Level: "+ChatColor.WHITE+""+ChatColor.BOLD+Levels.getLevels(p));
		
		score.setScore(6);
		
		
		Score score2 = objective.getScore(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Skill Points: "+ChatColor.WHITE+""+ChatColor.BOLD+Levels.getSkillpoints(p));
		
		score2.setScore(4);
		
		
		
		Score score3 = objective.getScore(ChatColor.AQUA+"Blockfront.us.to");
		
		score3.setScore(1);
		
		
		
		
		Score score4 = objective.getScore(ChatColor.BLUE+"");
		
		score4.setScore(5);
		
		
		Score score5 = objective.getScore(ChatColor.GREEN+"");
		
		score5.setScore(3);
		
		Score score6 = objective.getScore(ChatColor.RED+"");
		
		score6.setScore(2);	
		
		
		p.setScoreboard(scoreboard);
		
		
		
		
		
		
		
		
		
	}
	
	
	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent event)
	{
		if(!(event.isCancelled()))
		{
			if(!(canBreakHere(event.getBlock().getLocation())))
				return;
		Player p = event.getPlayer();
		File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int x = config.getInt("blocksplaced");
		x++;
		
		config.set("blocksplaced",x);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(x%100 == 0)
		{
			Levels.addexp(p, 20);
			ChatUtilities.broadcast(p, "Completed: 100 Blocks Placed, 20XP Gained!");
		}
		
		
		
		}
		
		
		
	}
	
	
	
	@EventHandler
	public void onPlayerBreakBlock(BlockBreakEvent event)
	{
		if(!(event.isCancelled()))
		{
			
			if(!(canBreakHere(event.getBlock().getLocation())))
				return;
			
			
			
		Player p = event.getPlayer();
		File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int x = config.getInt("blocksbroken");
		x++;
		
		config.set("blocksbroken",x);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(x%100 == 0)
		{
			Levels.addexp(p, 5);
			ChatUtilities.broadcast(p, "Completed: 100 Blocks Broken, 5XP Gained!");
		}
		
		
		
		}
		
		
		
	}
	
	
	@EventHandler
	public void onPlayerKill(PlayerDeathEvent event)
	{
		if(event.getEntity().getKiller() instanceof Player)
		{
			if(event.getEntity() instanceof Player)
			{
				
				
				Player p = event.getEntity().getKiller();
				
				File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				
				int x = config.getInt("playerskilled");
				x++;
				
				config.set("playerskilled",x);
				try {
					config.save(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(x == 10)
				{
					Levels.addexp(p, 20);
					ChatUtilities.broadcast(p, "Completed: 10 Players Killed, 20XP Gained!");
				}
				if(x == 25)
				{
					Levels.addexp(p, 50);
					ChatUtilities.broadcast(p, "Completed: 25 Players Killed, 50XP Gained!");
				}
				if(x == 50)
				{
					Levels.addexp(p, 100);
					ChatUtilities.broadcast(p, "Completed: 50 Players Killed, 100XP Gained!");
				}
				if(x == 100)
				{
					Levels.addexp(p, 500);
					ChatUtilities.broadcast(p, "Completed: 100 Players Killed, 500XP Gained!");
				}
	
				
				
				
				
				
				
				
				
			}
			
			
		}
		
		
		
		
	}
	
	
	
	public static boolean canBreakHere(Location location) {
	    RegionContainer container = com.sk89q.worldguard.WorldGuard.getInstance().getPlatform().getRegionContainer();
	    RegionQuery query = container.createQuery();
	    ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));
	    return set.size() == 0;
	}
	
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if(!(event.isCancelled()))
		{
			
			
		Location from = event.getFrom();
		Location to = event.getTo();
		
		
		if( (( (int)(to.getX()) != (int)(from.getX())) || (int)(to.getZ()) != (int)(from.getZ() ) ) && event.getPlayer().getGameMode() == GameMode.SURVIVAL)
		{
		Player p = event.getPlayer();
		File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int x = config.getInt("blockstraveled");
		x++;
		
		config.set("blockstraveled",x);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(x%1000 == 0)
		{
			Levels.addexp(p, 3);
			ChatUtilities.broadcast(p, "Completed: 1000 Blocks Traveled, 3XP Gained!");
		}
		
		
		
		}



		}
		
		
		
	}
	
	
	
	
	
	public void dataCreate(Player p)
	{
		
		File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
		
		// creates a new file with the given path example: File file = new File("plugins/Test/" + player.getUniqueId() + ".yml");
    	if (!file.exists()) 
    	{ //Checks if the file doesn't exist
    		try {
    			file.createNewFile(); //Tries to create the file
    			FileConfiguration config = YamlConfiguration.loadConfiguration(file); //Creates a FileConfiguration for the file
    			config.set("levels", 0);
    			config.set("exp", 0);
    			config.set("blocksplaced", 0);
    			config.set("blocksbroken", 0);
    			config.set("blockstraveled", 0);
    			config.set("playerskilled", 0);
    			config.set("skillpoints",0);
    			config.set("forcepush", 0);
    			config.set("forcejump", 0);
    			config.set("forcerepulse", 0);
    			config.set("forcedrain", 0);
    			config.set("forceheal", 0);
    			config.set("forcemindtrick", 0);
    			config.set("forcefreeze", 0);
    			config.set("forcesense", 0);
    			config.set("forcelightning", 0);
    			config.set("forcedash", 0);
    			config.set("saberthrow", 0);
    			config.set("crystalsbled",0);
    			config.set("saberscrafted", 0);
    			config.set("shipscrafted", 0);
    			
    			
    			//Path, where the value is stored and Value, what gets stored example config.set("health",100); would put health: 100 in the plugins/Test/+player.getUniqueId().yml file
    			config.save(file); //So the changes are actually saved

    			loadOldPowers(p);
    			System.out.println(ChatColor.RED+"Created New Data File For Player: "+p.getName());
    		} catch (IOException ex) {
    			p.sendMessage("Error 001 Contact An Admin");
    		} 
    	}else {
			FileConfiguration config = YamlConfiguration.loadConfiguration(file); 
			
    		if(!(config.getInt("helmetshards") >=1))
    		{
    			config.set("helmetshards",0);
    		}
    		
    		try {
				config.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}

	}

	
	
	public void loadOldPowers(Player p)
	{
		
		File file = new File(plugin.getDataFolder()+"/data/" + p.getUniqueId() + ".yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		
		int x = 0;
		
		
		if(p.hasPermission("force.jump"))
			x++;
		
		if(p.hasPermission("force.use"))
			x++;
		
		if(p.hasPermission("force.sense"))
			x++;
		if(p.hasPermission("force.storm"))
			x++;
		
		if(p.hasPermission("force.jump"))
			x++;
		
		if(p.hasPermission("saber.throw.cut"))
			x++;
		
		if(p.hasPermission("saber.throw.use"))
			x++;
		
		if(p.hasPermission("force.repulse"))
			x++;
		
		if(p.hasPermission("force.heal"))
			x++;
		
		if(p.hasPermission("force.mindtrick"))
			x++;
		
		if(p.hasPermission("force.mindtrick.II"))
			x++;
		
		if(p.hasPermission("force.freeze"))
			x++;
		
		if(p.hasPermission("force.freeze.II"))
			x++;
		if(p.hasPermission("force.dash"))
			x++;
		
		if(p.hasPermission("force.dash.II"))
			x++;
		if(p.hasPermission("force.drain"))
			x++;
		if(p.hasPermission("force.lightning"))
			x++;
		if(p.hasPermission("force.lightning.II"))
			x++;
		
		if(x > 0)
		{
			p.sendMessage(ChatColor.BLUE+""+ChatColor.BOLD+"Found Old Force Powers, Converting to Skillpoints...");
		}
		int g = 0;
		
		if(x>=3)
			g = 1;

		if(x>=8)
			g = 2;
		
		if(x>=11)
			g = 3;
		
		if(x>=15)
			g = 4;
		
		if(x>=18)
			g = 5;
		
		
		
		
		config.set("skillpoints",g);		
		
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
/*	
	
	@EventHandler
	public void playerChat(AsyncPlayerChatEvent event)
	{

		event.setFormat(ChatColor.GREEN+"["+ChatColor.GOLD+""+Levels.getLevels(event.getPlayer())+ChatColor.GREEN+"]"+" %s : %s");
		
		
	}
	
	
*/	
	
	
	
	
	
}
