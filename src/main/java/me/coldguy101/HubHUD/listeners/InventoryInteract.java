package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Created by Sean on 8/13/2014.
 */
public class InventoryInteract implements Listener
{
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteractInventory(InventoryClickEvent evt)
	{
		if (HubHUD.ignorePlayers.contains(Bukkit.getPlayer(evt.getWhoClicked().getUniqueId())))
			return;
		evt.setCancelled(true);
	}
}

