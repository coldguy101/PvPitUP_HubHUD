package me.coldguy101.HubHUD.menus;

import me.coldguy101.HubHUD.HubHUD;
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
	private static HubHUD main;
	private static SettingsManager settingsManager;
	static Inventory settingsMenu = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Settings");
	static ItemStack enable_fish = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aFishSlap Enabled", Arrays.asList("&bYou Can Now Slap","&bYour Friends With the","&bFish in Your Inventory!", "&7PS: They Can Slap You Back!"));
	static ItemStack disable_fish = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&4FishSlap Disabled", Arrays.asList("&cNo Fish-Slapping","&cFor You \u2639\u2639","&7PS: Others Can't Slap You!", "&7(Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley
	static ItemStack enable_blaster = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aBlaster Enabled", Arrays.asList("&bYou Can Now Blast","&bYour Friends With the","&bBlazeRod in Your Inventory!", "&7PS: They Can Blast You Back!"));
	static ItemStack disable_blaster = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&4Blaster Disabled", Arrays.asList("&cNo Blasting For","&cYou \u2639\u2639", "&7PS: Others Can't Blast You!", "&7(Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley
	static ItemStack players_unHidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aPlayers Seen", Arrays.asList("&aPlayers Are Currently Seen.","&bClick This To Hide", "&bAll Players!"));
	static ItemStack players_hidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&4Players Hidden", Arrays.asList("&cPlayers Are Currently Hidden.","&bClick This To Show", "&bAll Players!"));
	static ItemStack inventory_unhidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 5), "&aShow Inventory Items", Arrays.asList("&bShow All The Features", "&bAnd Hub Games In Your Hot-bar!", "&7Click This To Hide Items"));
	static ItemStack inventory_hidden = Util.nameAndLore(new ItemStack(Material.WOOL, 1, (short) 14), "&4Hide Inventory Items", Arrays.asList("&bHide All The Features", "&bAnd Hub Games In Your Hot-bar!", "&7Click This To Show Items"));

	public PlayerSettingsMenu(HubHUD hhud)
	{
		main = hhud;
		settingsManager = main.settingsManager;
	}

	public static void playerOpenSettingsMenu(Player p)
	{
		settingsMenu.clear();
		Settings settings = settingsManager.getSettings(p);

		if(settings.isSlapEnabled())
			settingsMenu.setItem(10, enable_fish);
		else
			settingsMenu.setItem(10, disable_fish);

		if(settings.isBlasterEnabled())
			settingsMenu.setItem(12, enable_blaster);
		else
			settingsMenu.setItem(12, disable_blaster);

		if(!settings.isPlayersHidden())
			settingsMenu.setItem(14, players_unHidden);
		else
			settingsMenu.setItem(14, players_hidden);

		if(!settings.isInventoryHidden())
			settingsMenu.setItem(16, inventory_unhidden);
		else
			settingsMenu.setItem(16, inventory_hidden);

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
				player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Players Are Now: " + (settings.isPlayersHidden() ? ChatColor.GREEN + "Hidden!" : ChatColor.RED + "Visible!"));
			}
			else if(itemName.contains("inventory"))
			{
				settings.toggleInventoryHidden();
				if(settings.isInventoryHidden())
				{
					settingsMenu.setItem(16, inventory_unhidden);
					player.getInventory().clear();
					player.getInventory().setItem(8, Util.nameItem(new ItemStack(Material.NAME_TAG), "&0|&aShow Inventory Items&0|"));
				}
				else
				{
					settingsMenu.setItem(16, inventory_hidden);
					main.inventoryManager.giveFullInventory(player);
				}
				player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Inventory Now: " + (settings.isInventoryHidden() ? ChatColor.GREEN + "Hidden!" : ChatColor.RED + "Visible!"));
			}

			evt.setCancelled(true); // Cancel the event
			player.closeInventory(); // Closes their inventory
		}
	}
}
