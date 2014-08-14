package me.coldguy101.HubHUD.menus;

import me.coldguy101.HubHUD.settings.Settings;
import me.coldguy101.HubHUD.settings.SettingsManager;
import me.coldguy101.HubHUD.util.ChatUtil;
import me.coldguy101.HubHUD.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Created by Sean on 8/4/2014.
 */
public class PlayerSettingsMenu implements Listener
{
	private static SettingsManager settingsManager;
	static Inventory settingsMenu = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Settings");
	static ItemStack enable_fish = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aFishSlap Enabled", Arrays.asList("&aYou Can Now Slap","&aYour Friends With the","&aFish in Your Inventory!", "&7PS: They Can Slap You Back!"));
	static ItemStack disable_fish = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&cFishSlap Disabled", Arrays.asList("&cNo Fish-Slapping","&cFor You \u2639\u2639","&7PS: Others Can't Slap You! (Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley
	static ItemStack enable_blaster = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aBlaster Enabled", Arrays.asList("&aYou Can Now Blast","&aYour Friends With the","&aBlazeRod in Your Inventory!", "&7PS: They Can Blast You Back!"));
	static ItemStack disable_blaster = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&cBlaster Disabled", Arrays.asList("&cNo Blasting For","&cYou \u2639\u2639", "&7PS: Others Can't Blast You! (Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley
	static ItemStack players_hidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&cPlayers Hidden", Arrays.asList("&cPlayers Are Currently Hidden.","&bClick This To Show", "&bAll Players!"));
	static ItemStack players_unHidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&aPlayers Seen", Arrays.asList("&aPlayers Are Currently Seen.","&bClick This To Hide", "&bAll Players!"));

	public PlayerSettingsMenu(SettingsManager sm)
	{
		settingsManager = sm;
	}

	public static void playerOpenSettingsMenu(Player p)
	{
		settingsMenu.clear();
		Settings settings = settingsManager.getSettings(p);

		if(settings.isSlapEnabled())
			settingsMenu.setItem(11, enable_fish);
		else
			settingsMenu.setItem(11, disable_fish);

		if(settings.isBlasterEnabled())
			settingsMenu.setItem(13, enable_blaster);
		else
			settingsMenu.setItem(13, disable_blaster);

		if(settings.isPlayersHidden())
			settingsMenu.setItem(15, players_unHidden);
		else
			settingsMenu.setItem(15, players_hidden);

		p.openInventory(settingsMenu);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt)
	{
		if (evt.getInventory().getName().equals(settingsMenu.getName())) // The inventory is our custom Inventory
		{
			String itemName = evt.getCurrentItem().getItemMeta().getDisplayName().toLowerCase(); //Get the name of the item the player clicks
			Player player = (Player) evt.getWhoClicked(); // The player that clicked the item
			Settings settings = settingsManager.getSettings(player);

			if(itemName.contains("fishslap"))
			{
				settings.toggleSlap(); //switch whatever the value was to the opposite
				if(settings.isSlapEnabled()) //display the correct color block immediately after clicking
					settingsMenu.setItem(10, enable_fish);
				else
					settingsMenu.setItem(10, disable_fish);
				player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Fish Slap is Now: " + (settings.isSlapEnabled() ? ChatColor.GREEN + "Enabled!" : ChatColor.RED + "Disabled!"));
			}
			else if(itemName.contains("blaster"))
			{
				settings.toggleBlaster();
				if(settings.isBlasterEnabled())
					settingsMenu.setItem(12, enable_blaster);
				else
					settingsMenu.setItem(12, disable_blaster);
				player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "The Blaster is Now: " + (settings.isBlasterEnabled() ? ChatColor.GREEN + "Enabled!" : ChatColor.RED + "Disabled!"));
			}
			else if(itemName.contains("player"))
			{
				settings.toggleHidePlayers();
				if(settings.isPlayersHidden())
				{
					settingsMenu.setItem(14, players_unHidden);
					for(Player p : Bukkit.getOnlinePlayers())
					{
						player.hidePlayer(p);
					}
				}
				else
				{
					settingsMenu.setItem(14, players_hidden);
					for(Player p : Bukkit.getOnlinePlayers())
					{
						player.showPlayer(p);
					}

				}
				player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Players Are Now: " + (settings.isPlayersHidden() ? ChatColor.GREEN + "Invisible!" : ChatColor.RED + "Visible!"));
			}

			evt.setCancelled(true); // Cancel the event
			player.closeInventory(); // Closes their inventory
		}
	}
}
