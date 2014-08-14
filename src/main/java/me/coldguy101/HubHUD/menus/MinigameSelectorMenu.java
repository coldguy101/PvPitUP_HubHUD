package me.coldguy101.HubHUD.menus;

import org.bukkit.event.Listener;

/**
 * Created by Sean on 7/31/2014.
 */
public class MinigameSelectorMenu implements Listener
{
	//private final HubHUD main;
	//Inventory gameSelector = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Player " + ChatColor.GRAY + "Spectate");

	/*public MinigameSelectorMenu(HubHUD hubHUD)
	{
		main = hubHUD;
	}*/
//
//	@EventHandler
//	public void onPlayerRightClick(PlayerInteractEvent evt)
//	{
//		if (evt.getPlayer().getItemInHand().getType() == Material.getMaterial() && evt.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("game selector"))
//		{
//			Player p = evt.getPlayer();
//			gameSelector.clear();
//
//
//
//			p.openInventory(gameSelector);
//			evt.setCancelled(true);
//		}
//	}
//
//	@EventHandler
//	public void onInventoryClick(InventoryClickEvent evt)
//	{
//		if (evt.getInventory().getName().equals(gameSelector.getName())) // The inventory is our custom Inventory
//		{
//			SkullMeta sm = (SkullMeta) evt.getCurrentItem().getItemMeta(); //Get the skull meta from the player's click
//			Player player = (Player) evt.getWhoClicked(); // The player that clicked the item
//			Player toPlayer = Bukkit.getPlayer(sm.getOwner());
//
//			player.teleport(toPlayer);
//			player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Teleporting you to " + toPlayer.getDisplayName());
//
//			evt.setCancelled(true); // Make it so the dirt is back in its original spot
//			player.closeInventory(); // Closes their inventory
//		}
//	}
}
