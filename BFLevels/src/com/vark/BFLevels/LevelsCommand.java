package com.vark.BFLevels;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;


public class LevelsCommand implements CommandExecutor{

	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	
	@Override
	public boolean onCommand(CommandSender cs, Command arg1,
			 String arg2, String[] arg3) {
		
		
		if(cs instanceof Player)
		{
			Player p = (Player)cs;
			//ChatUtilities.broadcast(p, Levels.printStats(p));
			
			Inventory inv = new LevelsGUI(plugin,p).getInv();
			
			p.openInventory(inv);
			return true;
			
		}
		
		
		
		
		return false;
	}

}
