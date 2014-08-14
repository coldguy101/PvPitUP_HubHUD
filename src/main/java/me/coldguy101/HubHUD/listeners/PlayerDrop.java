package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Sean on 8/4/2014.
 */
public class PlayerDrop implements Listener
{
	@EventHandler
	public void onPlayerDrop(PlayerDropItemEvent evt)
	{
		if(HubHUD.ignorePlayers.contains(evt.getPlayer()))
			return;
		evt.setCancelled(true);
	}
}
