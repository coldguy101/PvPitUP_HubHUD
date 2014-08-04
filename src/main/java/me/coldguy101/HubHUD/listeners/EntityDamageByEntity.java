package me.coldguy101.HubHUD.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
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
		if (event.getDamager() instanceof Fireball)
		{
			Entity damaged = event.getEntity();
			event.setDamage(0);
			damaged.setVelocity(new Vector(0, 2, 0));
		}
	}
}
