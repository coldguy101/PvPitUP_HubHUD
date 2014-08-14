package me.coldguy101.HubHUD.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Sean on 8/13/2014.
 */
public class PlayerDeath implements Listener
{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent evt)
	{
		evt.setDeathMessage(evt.getEntity().getDisplayName() + " Somehow managed to die...");
		evt.setDroppedExp(0);
		evt.getDrops().clear();
	}
}
