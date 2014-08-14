package me.coldguy101.HubHUD.menus;

import me.coldguy101.HubHUD.util.BungeeUtil;
import me.coldguy101.HubHUD.util.ChatUtil;
import me.coldguy101.HubHUD.util.Util;
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
 * Created by Sean on 8/3/2014.
 */
public class HubSelectorMenu implements Listener
{
	static Inventory hubSelector = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Hub " + ChatColor.GRAY + "Selector");

	public static void playerOpenMenu(Player p)
	{
		hubSelector.clear();

		hubSelector.setItem(1, Util.nameItem(new ItemStack(Material.EMERALD_BLOCK, 1),"&bPrimary Hub &c1"));
		hubSelector.setItem(3, Util.nameItem(new ItemStack(Material.EMERALD_BLOCK, 1),"&bPrimary Hub &c2"));
		hubSelector.setItem(5, Util.nameItem(new ItemStack(Material.EMERALD_BLOCK, 1),"&bPrimary Hub &c3"));
		hubSelector.setItem(7, Util.nameItem(new ItemStack(Material.EMERALD_BLOCK, 1),"&bPrimary Hub &c4"));

		hubSelector.setItem(10, Util.nameItem(new ItemStack(Material.WORKBENCH, 1),"&aHungerGames Hub &c1"));
		hubSelector.setItem(12, Util.nameItem(new ItemStack(Material.WORKBENCH, 1),"&aHungerGames Hub &c2"));
		hubSelector.setItem(14, Util.nameItem(new ItemStack(Material.WORKBENCH, 1),"&aHungerGames Hub &c3"));
		hubSelector.setItem(16, Util.nameItem(new ItemStack(Material.WORKBENCH, 1),"&aHungerGames Hub &c4"));

		hubSelector.setItem(22, Util.nameItem(new ItemStack(Material.WORKBENCH, 1),"&cKingOfTheHill Hub &41"));

		p.openInventory(hubSelector);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt)
	{
		if (evt.getInventory().getName().equals(hubSelector.getName())) // The inventory is our custom Inventory
		{
			String itemName = evt.getCurrentItem().getItemMeta().getDisplayName().toLowerCase(); //Get the name of the item clicked
			Player player = (Player) evt.getWhoClicked(); // The player that clicked the item

			player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Sending you to " + itemName);

			evt.setCancelled(true); // Make it so the dirt is back in its original spot
			player.closeInventory(); // Closes their inventory

			BungeeUtil.movePlayerToServer(player, (itemName.contains("primary") ? "hub" + itemName.substring(itemName.length() - 1, itemName.length()) :
					(itemName.contains("hungergames") ? "hghub" + itemName.substring(itemName.length() - 1, itemName.length()) :
							(itemName.contains("kingofthehill") ? "kothhub1" : "hub1"))));
		}
	}
}
