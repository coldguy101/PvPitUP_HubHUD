package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Created by Sean on 8/13/2014.
 */
public class PlayerRespawn implements Listener
{
	private final HubHUD main;

	public PlayerRespawn(HubHUD hhud)
	{
		main = hhud;
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent evt)
	{
		main.inventoryManager.giveFullInventory(evt.getPlayer());
	}
}
