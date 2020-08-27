package com.vark.BFLevels;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;


public class LevelsGUI implements Listener
{

	
	
private final Inventory inv;
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	public LevelsGUI()
	{

		inv = Bukkit.createInventory(null,9,"Stats");

	}
	
	
	
	public LevelsGUI(Plugin plugin, Player p)
	{
		

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		
		inv = Bukkit.createInventory(null,54,"Stats");
		
		initializeItems(p);
		
		
		
	}
	
	
	
    public void initializeItems(Player p) {
    	String s = ""+getSkillPoints(p);
    	

    	
    	
        inv.setItem(19,getHead(p));
        
       
        	
                
        inv.setItem(25,createGuiItem(141,Material.ENCHANTED_BOOK, "§bTasks",false,"Checkout Tasks!"));

        
     

        
        
        
        

        

    }
	
    
    public static ItemStack getHead(Player player) {

    	ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
    	ItemMeta im = skull.getItemMeta();
    	
    	SkullMeta sm = (SkullMeta)im;
    	
    	sm.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
    	
    	
    	sm.setDisplayName(Levels.printStats(player));
    	skull.setItemMeta(sm);
    	
    	
    	
    	
    	return skull;
    	
    	
    	
    	
    	
    }
	
	
	
	
	public Inventory getInv()
	{
		return inv;
	}
	
	
	public int getSkillPoints(Player p)
	{
		
		File file = new File(plugin.getDataFolder().getParentFile()+"/BFLevels/data/"+p.getUniqueId() + ".yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int x = config.getInt("skillpoints");
		
		
		return x;
		
	}
	
	

	
	
	
    private ItemStack createGuiItem(int n,final Material material, final String name, boolean enchanted, String lore) {
        ItemStack item = new ItemStack(material, 1);
         ItemMeta meta = item.getItemMeta();

        if(n != 0)
        {
        	
        	meta.setCustomModelData(n);
        }
        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        ArrayList<String> lores = new ArrayList<String>();
        lores.add(lore);
        meta.setLore(lores);
        
        item.setItemMeta(meta);
       if(enchanted)
       {
    	   
    	   item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
       }

        
       

        return item;
    }
    
    
    
	
	
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
       // p.sendMessage("You clicked at slot " + e.getRawSlot());
        
        if(e.getRawSlot() == 25 && (e.getInventory().getItem(e.getRawSlot()).getType() != Material.AIR && e.getInventory().getItem(e.getRawSlot()).getType() != null ))
        {
		
			Inventory i = new TasksGUI(plugin,p).getInv();
			p.closeInventory();
			p.openInventory(i);
			
			
        }


        
    }
    
  

	@EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
          e.setCancelled(true);
        }
    }
	
    
    
    
    
    public int getPowerLevel(Player p, String force)
    {
    	
    
    	
    	
    	File file = new File(plugin.getDataFolder().getParentFile()+"/BFLevels/data/"+p.getUniqueId() + ".yml");
    	
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		int x = config.getInt(force);
    	
    	return x;
    	
    	
    	
    }
    
    
    
    
    

    
    
    
    
    
    

    
    
    
    
    
	
    

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
