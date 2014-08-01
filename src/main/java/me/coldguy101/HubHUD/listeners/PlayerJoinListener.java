package me.coldguy101.HubHUD.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * Created by Sean on 7/31/2014.
 */
public class PlayerJoinListener implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		p.getInventory().clear();
		setItems(p);
	}

	public void setItems(Player p)
	{
		PlayerInventory pi = p.getInventory();

		ItemStack minigameSelector = new ItemStack(Material.COMPASS);
		ItemMeta minigameSMeta = minigameSelector.getItemMeta();
		minigameSMeta.setDisplayName(ChatColor.GREEN + "MiniGame " + ChatColor.AQUA + "Selector!");
		minigameSMeta.setLore(Arrays.asList(ChatColor.GREEN + "Right Click While Holding", ChatColor.GREEN + "This In Your Hand To", ChatColor.AQUA + "Select a Minigame!"));
		minigameSelector.setItemMeta(minigameSMeta);
		pi.setItem(0, minigameSelector);

		ItemStack 
	}
}
