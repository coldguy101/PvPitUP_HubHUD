package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.settings.SettingsManager;
import me.coldguy101.HubHUD.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
	final SettingsManager settingsManager;

	public EntityDamageByEntity(SettingsManager sm)
	{
		settingsManager = sm;
	}

	@EventHandler
	public void entityDamageEntity(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Snowball)
		{
			event.setCancelled(true);
			Entity damaged = event.getEntity();
			Player shooter = (Player) ((Snowball) event.getDamager()).getShooter();
			if(damaged instanceof Player)
			{
				if (settingsManager.getSettings((Player) damaged).isBlasterEnabled())
				{
					shooter.sendMessage(ChatUtil.pvpitup + ChatColor.AQUA + "You Blasted " + ((Player) damaged).getDisplayName() + "!");

					damaged.setVelocity(new Vector(0, .5, 0));
					damaged.setFallDistance(0);
				}
				else
					shooter.sendMessage(ChatUtil.pvpitup + ((Player) damaged).getDisplayName() + ChatColor.RED + " Has Blasting Disabled! Try Blasting Someone Else!");
			}
			else
				shooter.sendMessage(ChatUtil.pvpitup + ChatColor.RED + "Your Target Must Be a Player!");
		}
	}
}
