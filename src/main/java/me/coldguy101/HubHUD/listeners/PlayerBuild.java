package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Sean on 8/13/2014.
 */
public class PlayerBuild implements Listener
{
	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent evt)
	{
		if(HubHUD.ignorePlayers.contains(evt.getPlayer()))
			return;
		evt.setCancelled(true);
	}

	@EventHandler
	public void onPlayerBreak(BlockBreakEvent evt)
	{
		if(HubHUD.ignorePlayers.contains(evt.getPlayer()))
			return;
		evt.setCancelled(true);
	}
}
