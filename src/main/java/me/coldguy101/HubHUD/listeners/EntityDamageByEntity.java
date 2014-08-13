package me.coldguy101.HubHUD.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

/**
 * Created by Sean on 8/4/2014.
 */
public class EntityDamageByEntity implements Listener
{
	@EventHandler
	public void entityDamageEntity(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Snowball)
		{
			event.setCancelled(true);
			Entity damaged = event.getEntity();
			damaged.setVelocity(new Vector(0, .5, 0));
			damaged.setFallDistance(0);
		}
	}
}
