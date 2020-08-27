package com.vark.BFLevels;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveExp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] arg3) 
	{
		
		
		if(cs instanceof Player)
		{
			
			Player p = (Player)cs;
			if(p.hasPermission("bflevels.admin") || p.isOp())
			{
				

				if(arg3.length > 1)
				{
					
					int a = Integer.parseInt(arg3[1]);
					
					Player b = Bukkit.getPlayer(arg3[0]);
					
				
					Levels.addexp(b, a);
					
					return true;
	
				}
				else {
					p.sendMessage("Incorrect Command Usage");
					return true;
				}

			}
			else {
				p.sendMessage("You Do Not Have Permissions To Do This");
				return true;
			}
				
				
			
			
			
			}else {
				
				if(arg3.length > 1)
				{
					
					int a = Integer.parseInt(arg3[1]);
					
					Player b = Bukkit.getPlayer(arg3[0]);
					
				
					Levels.addexp(b, a);
					
					return true;
	
				}
				else {
					cs.sendMessage("Incorrect Command Usage");
					return true;
				}
				
			}
			
			
	
	}
	
	
	
	
	
	
	
	
	
	
	

}
