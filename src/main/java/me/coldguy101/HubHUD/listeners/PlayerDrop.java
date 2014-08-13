package me.coldguy101.HubHUD.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Sean on 8/4/2014.
 */
public class PlayerDrop implements Listener
{
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent event)
	{
		if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().contains("|"))
		{
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteractItem
}
