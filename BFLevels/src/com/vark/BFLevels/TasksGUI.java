package com.vark.BFLevels;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
import org.bukkit.plugin.Plugin;


public class TasksGUI implements Listener
{

	
	
private final Inventory inv;
	
	private Plugin plugin = Main.getPlugin(Main.class);
	
	public TasksGUI()
	{

		inv = Bukkit.createInventory(null,9,"Tasks");

	}
	
	
	
	public TasksGUI(Plugin plugin, Player p)
	{
		

		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		
		inv = Bukkit.createInventory(null,54,"Tasks");
		
		initializeItems(p);
		
		
		
	}
	
	
	
    public void initializeItems(Player p) {
    	String s = ""+getSkillPoints(p);
    	
    	
        inv.setItem(0,createGuiItem(111,Material.DRAGON_HEAD, "§c§lDefeat Bosses",false,"40xp, Unlimited"));
        inv.setItem(1,createGuiItem(111,Material.ZOMBIE_HEAD, "§4§lHunt Bounties",false,"50xp, Unlimited"));
        inv.setItem(2,createGuiItem(31111111,Material.NETHER_STAR, "§c§lBleed Crystals",false,"10xp, Unlimited"));
        inv.setItem(3,createGuiItem(101,Material.STICK, "§a§lWin a HVV Match",false,"10xp, Unlimited"));
        inv.setItem(4,createGuiItem(102,Material.STICK, "§c§lPlay a HVV Match",false,"5xp, Unlimited"));
        inv.setItem(5,createGuiItem(102,Material.GRASS_BLOCK, "§aPlace 100 Blocks",false,"15xp, Unlimited"));
        inv.setItem(6,createGuiItem(102,Material.DIRT, "§cBreak 100 Blocks",false,"5xp, Unlimited"));
        inv.setItem(7,createGuiItem(102,Material.DIAMOND_ORE, "§6Travel 1000 Blocks",false,"3xp, Unlimited"));
        inv.setItem(8,createGuiItem(102,Material.DIAMOND, "§b/vote",false,"20xp per Vote, Unlimited"));  
        inv.setItem(9,createGuiItem(102,Material.CHEST, "§eOpen Crates",false,"1-50xp, Unlimited")); 
        
        inv.setItem(19,createGuiItem(102,Material.STICK, "§bUse a Lightsaber",false,"100xp, One Time")); 
        inv.setItem(25,createGuiItem(101,Material.IRON_HOE, "§aFly A Ship!",false,"200xp, One Time")); 
        inv.setItem(20,createGuiItem(0,Material.WOODEN_SWORD, "§4Kill 10 Players",false,"20xp, One Time")); 
        inv.setItem(21,createGuiItem(0,Material.STONE_SWORD, "§4Kill 25 Players",false,"50xp, One Time")); 
        inv.setItem(22,createGuiItem(0,Material.GOLDEN_SWORD, "§4Kill 50 Players",false,"100xp, One Time")); 
        inv.setItem(23,createGuiItem(0,Material.DIAMOND_SWORD, "§4Kill 100 Players",false,"500xp, One Time")); 
  

        
        
        
        

        

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
        
     /*   if(e.getRawSlot() == 25 && (e.getInventory().getItem(e.getRawSlot()).getType() != Material.AIR && e.getInventory().getItem(e.getRawSlot()).getType() != null ))
        {
		
			Inventory i = new TasksGUI(plugin,p).getInv();
			p.closeInventory();
			p.openInventory(i);
			
		
        }
*/

        
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
