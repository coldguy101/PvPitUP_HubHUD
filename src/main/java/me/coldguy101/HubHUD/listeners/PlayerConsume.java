package me.coldguy101.HubHUD.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Created by Sean on 8/14/2014.
 */
public class PlayerConsume implements Listener
{
	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent evt)
	{
		if(evt.getItem().getItemMeta().getDisplayName().contains("|"))
			evt.setCancelled(true);
	}
}
