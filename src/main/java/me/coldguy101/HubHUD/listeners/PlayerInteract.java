package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import me.coldguy101.HubHUD.menus.HatSelectorMenu;
import me.coldguy101.HubHUD.menus.HubSelectorMenu;
import me.coldguy101.HubHUD.menus.PlayerSettingsMenu;
import me.coldguy101.HubHUD.util.ChatUtil;
import me.coldguy101.HubHUD.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
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
		if (heald.getType() == Material.WRITTEN_BOOK)
			return;
		String dName = heald.getItemMeta().getDisplayName().toLowerCase();

		if (dName.contains("|"))
		{
			Player player = event.getPlayer();
			if (dName.contains("shoot"))
			{
				if(main.settingsManager.getSettings(player).isBlasterEnabled())
					player.launchProjectile(Snowball.class);
				else
					player.sendMessage(ChatUtil.pvpitup + ChatColor.RED + "You Have the Blaster Disabled! Change This in Settings!");
			}
			else if (dName.contains("pet"))
			{
				if (player.hasPermission(Util.premium) || player.hasPermission(Util.platinum))
				{
					/*if(BmAPI.isMuted(player.getName()))
						player.sendMessage(ChatColor.DARK_RED + "Sorry, you cant do this action while muted!");
					else*/
					main.echoPetAPI.openPetSelector(player);
				}
				else
				{
					player.sendMessage(ChatUtil.pvpitup + ChatColor.GOLD + "This is a Donor-Only Feature! " +
							ChatColor.BLUE + "Go to PvPitUP.com/Store to Donate!");
				}
			}
			else if (dName.contains("hat"))
			{
				if (player.hasPermission(Util.premium) || player.hasPermission(Util.platinum))
				{
					HatSelectorMenu.playerOpenMenu(player);
				}
				else
				{
					player.sendMessage(ChatUtil.pvpitup + ChatColor.GOLD + "This is a Donor-Only Feature! " +
							ChatColor.BLUE + "Go to PvPitUP.com/Store to Donate!");
				}
			}
			else if (dName.contains("jump"))
			{
				if (player.hasPermission(Util.premium) || player.hasPermission(Util.platinum))
				{
					if (player.getItemInHand().getItemMeta().getDisplayName().contains("ON"))
					{
						for (PotionEffect effect : player.getActivePotionEffects())
							player.removePotionEffect(effect.getType());
						player.getItemInHand().getItemMeta().setDisplayName(Util.subColorCodes("&0|&4Jump Toggle " + "[OFF]&0|"));
						player.sendMessage(ChatUtil.pvpitup + ChatColor.GREEN + "Hub Boosts Disabled!");
					}
					else
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999999, 2));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
						player.getItemInHand().getItemMeta().setDisplayName(Util.subColorCodes("&0|&aJump Toggle " + "[ON]&0|"));
						player.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_BLUE + "Hub Boosts Enabled!");
					}
				}
				else
				{
					player.sendMessage(ChatUtil.pvpitup + ChatColor.GOLD + "This is a Donor-Only Feature! " +
							ChatColor.BLUE + "Go to PvPitUP.com/Store to Donate!");
				}
			}
			//} else if (dName.contains("minigame"))
			//{

			//}
			else if (dName.contains("hub"))
			{
				HubSelectorMenu.playerOpenMenu(player);
				//player.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_BLUE + "This feature is coming soon!");
			}
			else if (dName.contains("settings"))
			{
				PlayerSettingsMenu.playerOpenSettingsMenu(player);
				//player.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_BLUE + "This feature is coming soon!");
			}
		}
	}

	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent event)
	{
		if (event.getRightClicked() instanceof Player)
		{
			Player clicker = event.getPlayer();
			Player clicked = (Player) event.getRightClicked();
			ItemStack heald = clicker.getItemInHand();
			String dName = heald.getItemMeta().getDisplayName().toLowerCase();

			if (dName.contains("slap"))
			{
				if(!main.settingsManager.getSettings(clicker).isSlapEnabled())
				{
					clicker.sendMessage(ChatUtil.pvpitup + ChatColor.RED + "You Have Slapping Disabled! Change This In The Settings!");
					return;
				}
				if(!main.settingsManager.getSettings(clicked).isSlapEnabled())
				{
					clicker.sendMessage(ChatUtil.pvpitup + ChatColor.RED + "The Person You Are Attempting to Slap Has Slapping Disabled!");
					return;
				}

				clicked.sendMessage(ChatUtil.pvpitup + clicker.getDisplayName() + ChatColor.GOLD + " Slapped you!" +
						ChatColor.GRAY + " (If you wish, use settings to turn off slap)");
				clicker.sendMessage(ChatUtil.pvpitup + ChatColor.DARK_GREEN + "You just slapped " + clicked
						.getDisplayName() + "!");
				Location location = clicked.getLocation();
				location.setPitch(location.getPitch() + 180);
				location.setYaw(location.getYaw() + 180);
				clicked.teleport(location);
			}
		}
	}
}
