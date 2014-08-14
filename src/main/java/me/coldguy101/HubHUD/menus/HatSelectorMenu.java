package me.coldguy101.HubHUD.menus;

import me.coldguy101.HubHUD.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Sean on 8/4/2014.
 */
public class HatSelectorMenu implements Listener
{
	static Inventory hatSelector = Bukkit.createInventory(null, 18, ChatColor.AQUA + "Hat " + ChatColor.GRAY + "Selector");

	public static void playerOpenMenu(Player p)
	{
		hatSelector.clear();

		//First Row
		hatSelector.setItem(0, new ItemStack(Material.STONE));
		hatSelector.setItem(1, new ItemStack(Material.WOOD));
		hatSelector.setItem(2, new ItemStack(Material.SAPLING));
		hatSelector.setItem(3, new ItemStack(Material.BEDROCK));
		hatSelector.setItem(4, new ItemStack(Material.WATER));
		hatSelector.setItem(5, new ItemStack(Material.LAVA));
		hatSelector.setItem(6, new ItemStack(Material.GRAVEL));
		hatSelector.setItem(7, new ItemStack(Material.SPONGE));
		hatSelector.setItem(8, new ItemStack(Material.LEAVES));

		//Second Row
		hatSelector.setItem(9, new ItemStack(Material.GLASS));
		hatSelector.setItem(10, new ItemStack(Material.EMERALD_BLOCK));
		hatSelector.setItem(11, new ItemStack(Material.BRICK));
		hatSelector.setItem(12, new ItemStack(Material.TNT));
		hatSelector.setItem(13, new ItemStack(Material.FIRE));
		hatSelector.setItem(14, new ItemStack(Material.OBSIDIAN));
		hatSelector.setItem(15, new ItemStack(Material.WORKBENCH));
		hatSelector.setItem(16, new ItemStack(Material.GOLD_BLOCK));
		hatSelector.setItem(17, new ItemStack(Material.DIAMOND_BLOCK));

		p.openInventory(hatSelector);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt)
	{
		if (evt.getInventory().getName().equals(hatSelector.getName())) // The inventory is our custom Inventory
		{
			ItemStack clicked = evt.getCurrentItem();
			Player player = (Player) evt.getWhoClicked();

			player.getInventory().setHelmet(clicked);

			player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "You now are wearing a hat of " + ChatColor.GOLD + clicked.getType().toString() + ChatColor.BLUE + "!");

			evt.setCancelled(true); // Cancel the event
			player.closeInventory(); // Closes the inventory
		}
	}
}
