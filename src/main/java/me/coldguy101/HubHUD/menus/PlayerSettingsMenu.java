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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

/**
 * Created by Sean on 8/4/2014.
 */
public class PlayerSettingsMenu implements Listener
{
	private static SettingsManager settingsManager;
	static Inventory settingsMenu = Bukkit.createInventory(null, 27, ChatColor.BOLD + "Settings");
	static ItemStack enable_fish = Util.nameAndLore(new ItemStack(Material.EMERALD_BLOCK), "&aFishSlap Enabled", Arrays.asList("&aYou Can Now Slap","&aYour Friends With the","&aFish in Your Inventory!", "&7PS: They Can Slap You Back!"));
	static ItemStack disable_fish = Util.nameAndLore(new ItemStack(Material.REDSTONE_BLOCK), "&cFishSlap Disabled", Arrays.asList("&cNo Fish-Slapping","&cFor You \u2639\u2639","&7PS: Others Can't Slap You! (Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley
	static ItemStack enable_blaster = Util.nameAndLore(new ItemStack(Material.EMERALD_BLOCK), "&aBlaster Enabled", Arrays.asList("&aYou Can Now Blast","&aYour Friends With the","&aBlazeRod in Your Inventory!", "&7PS: They Can Blast You Back!"));
	static ItemStack disable_blaster = Util.nameAndLore(new ItemStack(Material.REDSTONE_BLOCK), "&cBlaster Disabled", Arrays.asList("&cNo Blasting For","&cYou \u2639\u2639", "&7PS: Others Can't Blast You! (Accept Donors \u263A)")); //the two u are frowny faces, last one is smiley

	public PlayerSettingsMenu(SettingsManager sm)
	{
		settingsManager = sm;
	}

	public static void playerOpenSettingsMenu(Player p)
	{
		settingsMenu.clear();
		Settings settings = settingsManager.getSettings(p);

		if(settings.isSlapEnabled())
			settingsMenu.setItem(12, enable_fish);
		else
			settingsMenu.setItem(12, disable_fish);

		if(settings.isBlasterEnabled())
			settingsMenu.setItem(16, enable_blaster);
		else
			settingsMenu.setItem(16, disable_blaster);

		p.openInventory(settingsMenu);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent evt)
	{
		if (evt.getInventory().getName().equals(settingsMenu.getName())) // The inventory is our custom Inventory
		{
			SkullMeta sm = (SkullMeta) evt.getCurrentItem().getItemMeta(); //Get the skull meta from the player's click
			Player player = (Player) evt.getWhoClicked(); // The player that clicked the item
			Player toPlayer = Bukkit.getPlayer(sm.getOwner());

			player.teleport(toPlayer);
			player.sendMessage(ChatUtil.pvpitup + ChatColor.BLUE + "Teleporting you to " + toPlayer.getDisplayName());

			evt.setCancelled(true); // Make it so the dirt is back in its original spot
			player.closeInventory(); // Closes their inventory
		}
	}
}
