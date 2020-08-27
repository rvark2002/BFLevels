package com.vark.BFLevels;

import static org.bukkit.ChatColor.*;


import org.bukkit.entity.Player;
public class ChatUtilities {
	
	
	public static void broadcast(Player p,String arg)
	{

			p.sendMessage(starter() + arg);
	
		
	}
	
	
	private static String starter()
	{
		
		return BLUE +""+BOLD+" ["+GREEN+"BFLevels"+BLUE+"]" + GREEN; 
	}
	

}




