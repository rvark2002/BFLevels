package com.vark.BFLevels;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;


public class LevelsListCommand implements CommandExecutor{

	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1,
			 String arg2, String[] arg3) {
		
		
		if(cs instanceof Player)
		{
			Player p = (Player)cs;

			for(Player a: Bukkit.getOnlinePlayers())
			{
				
				p.sendMessage(ChatColor.BOLD+a.getDisplayName()+":"+Levels.getLevels(a));
				
				
			}
			
			return true;
			
		}
		
		
		
		
		return false;
	}

}
