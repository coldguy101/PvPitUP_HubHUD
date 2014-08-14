package me.coldguy101.HubHUD.menus;

import me.coldguy101.HubHUD.util.BungeeUtil;
import me.coldguy101.HubHUD.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Sean on 8/3/2014.
 */
public class HubSelectorMenu implements Listener
{
	static Inventory gameSelector = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Hub " + ChatColor.GRAY + "Selector");

	public static void playerOpenMenu(Player p)
	{
		gameSelector.clear();

		//gameSelector.setItem(10, );

		p.openInventory(gameSelector);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt)
	{
		if (evt.getInventory().getName().equals(gameSelector.getName())) // The inventory is our custom Inventory
		{
			ItemMeta itemMeta = evt.getCurrentItem().getItemMeta(); //Get the skull meta from the player's click
			Player player = (Player) evt.getWhoClicked(); // The player that clicked the item

			player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Teleporting you to " + itemMeta.getDisplayName());

			evt.setCancelled(true); // Make it so the dirt is back in its original spot
			player.closeInventory(); // Closes their inventory
			BungeeUtil.movePlayerToServer(player, itemMeta.getDisplayName());
		}
	}
}
