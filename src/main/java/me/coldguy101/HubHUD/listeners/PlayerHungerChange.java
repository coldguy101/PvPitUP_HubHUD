package me.coldguy101.HubHUD.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Created by Sean on 8/14/2014.
 */
public class PlayerHungerChange implements Listener
{
	@EventHandler
	public void onHungerChange(FoodLevelChangeEvent evt)
	{
		evt.setCancelled(true);
	}
}
