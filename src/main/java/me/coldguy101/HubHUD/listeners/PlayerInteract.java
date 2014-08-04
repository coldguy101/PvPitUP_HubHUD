package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import me.coldguy101.HubHUD.util.ChatUtil;
import me.coldguy101.HubHUD.util.Util;
import me.confuser.banmanager.BmAPI;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Sean on 8/3/2014.
 */
public class PlayerInteract implements Listener
{
	private final HubHUD main;

	public PlayerInteract(HubHUD m)
	{
		main = m;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		ItemStack heald = event.getPlayer().getItemInHand();
		String dName = heald.getItemMeta().getDisplayName().toLowerCase();

		if(dName.contains("|"))
		{
			Player player = event.getPlayer();
			if (dName.contains("shoot"))
			{
				player.launchProjectile(Fireball.class);
			} else if (dName.contains("pet"))
			{
				if(BmAPI.isMuted(player.getName()))
					player.sendMessage(ChatColor.DARK_RED + "Sorry, you cant do this action while muted!");
				else
					main.echoPetAPI.openPetSelector(player);
			} else if (dName.contains("jump"))
			{
				if(player.hasPermission(Util.premium) || player.hasPermission(Util.platinum))
				{
					if (player.getActivePotionEffects().size() > 0)
					{
						for (PotionEffect effect : player.getActivePotionEffects())
							player.removePotionEffect(effect.getType());
						player.setWalkSpeed((float) .2);
						player.getItemInHand().getItemMeta().setDisplayName(Util.subColorCodes("&0|&4Jump Toggle [OFF]&0|"));
					}
					else
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 5));
						player.setWalkSpeed((long) .5);
						player.getItemInHand().getItemMeta().setDisplayName(Util.subColorCodes("&0|&aJump Toggle [ON]&0|"));
					}
				}
			//} else if (dName.contains("minigame"))
			//{

			//}
			else if (dName.contains("hub"))
			{
				player.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_BLUE + "This feature is coming soon!");
			}
			} else if (dName.contains("settings"))
			{
				player.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_BLUE + "This feature is coming soon!");
			}
		}
	}

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event)
	{
		if(event.getRightClicked() instanceof Player)
		{
			Player clicker = event.getPlayer();
			Player clicked = (Player) event.getRightClicked();
			ItemStack heald = clicker.getItemInHand();
			String dName = heald.getItemMeta().getDisplayName().toLowerCase();

			if(dName.contains("slap"))
			{
				clicked.sendMessage(ChatUtil.pvpitup + clicker.getDisplayName() + ChatColor.GOLD + " Slapped you!" + ChatColor.GRAY + " (If you wish, use settings to turn off slap)");
				clicker.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_GREEN + "You just slapped " + clicked.getDisplayName() + "!");
				Location location = clicked.getLocation();
				location.setPitch(location.getPitch() + 180);
				location.setYaw(location.getYaw() + 180);
				clicked.teleport(location);
			}
		}
	}
}
