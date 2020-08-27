package com.vark.BFLevels;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin
{

	
	public void onEnable()
	{
		
		
		
		getCommand("levels").setExecutor(new LevelsCommand());

		getCommand("givelevels").setExecutor(new GiveLevels());
		getCommand("giveexp").setExecutor(new GiveExp());
		
		new LevelsListener(this);
		
		
		File userFolder = new File(this.getDataFolder(), "data");
		userFolder.mkdirs();
		
		
		
		
		
		
		
	}
	
	
	
	public void onDisable() {}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
