package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import me.coldguy101.HubHUD.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Sean on 7/31/2014.
 */
public class PlayerJoin implements Listener
{
	private final HubHUD main;

	public PlayerJoin(HubHUD m)
	{
		main = m;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		if(p.hasPermission(Util.premium) || p.hasPermission(Util.platinum))
		{
			if (p.hasPermission(Util.premium))
			{
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
				//p.setWalkSpeed((long) .45);
			}
			else
				for (PotionEffect effect : p.getActivePotionEffects())
					p.removePotionEffect(effect.getType());
			if (p.hasPermission(Util.platinum))
			{
				p.setAllowFlight(true);
				//p.setFlySpeed((long) .5);
			}
		}
		p.getInventory().clear();
		main.inventoryManager.giveFullInventory(p);

		for(Player pl : Bukkit.getOnlinePlayers())
		{
			if(main.settingsManager.getSettings(pl).isPlayersHidden())
				pl.hidePlayer(p);
			else
				pl.showPlayer(p);
		}
	}
}
