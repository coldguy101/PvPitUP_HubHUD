package me.coldguy101.HubHUD.listeners;

import me.coldguy101.HubHUD.HubHUD;
import me.coldguy101.HubHUD.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

/**
 * Created by Sean on 7/31/2014.
 */
public class PlayerJoinListener implements Listener
{
	private final HubHUD main;

	public PlayerJoinListener(HubHUD m)
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
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 5));
				p.setWalkSpeed((long) .5);
			}
			else
				for (PotionEffect effect : p.getActivePotionEffects())
					p.removePotionEffect(effect.getType());
			if (p.hasPermission(Util.platinum))
			{
				p.setAllowFlight(true);
				p.setFlySpeed((long) .5);
			}
		}
		p.getInventory().clear();
		setItems(p);
	}

	public void setItems(Player p)
	{
		PlayerInventory pi = p.getInventory();

		ItemStack fishSlap = new ItemStack(Material.RAW_FISH); //fish slapper
		fishSlap.setDurability((short) 2); //Make it a clownfish
		ItemMeta fishSlapMeta = fishSlap.getItemMeta();
		fishSlapMeta.setDisplayName(Util.subColorCodes("&0|&aSlap Your Friends With A Fish!&0|"));
		fishSlapMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Slap Your Friends!"));
		fishSlap.setItemMeta(fishSlapMeta);
		//fishSlap.addUnsafeEnchantment(Enchantment.LUCK, 1);
		pi.setItem(0, fishSlap);

		ItemStack friendBlaster = new ItemStack(Material.BLAZE_ROD); //player blaster
		ItemMeta friendBlasterMeta = friendBlaster.getItemMeta();
		friendBlasterMeta.setDisplayName(Util.subColorCodes("&0|&aShoot Your Friends In The Air!&0|"));
		friendBlasterMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Blast Your Friends!"));
		friendBlaster.setItemMeta(friendBlasterMeta);
		//friendBlaster.addUnsafeEnchantment(Enchantment.LUCK, 1);
		pi.setItem(1, friendBlaster);

		ItemStack jumpBoostToggle = new ItemStack(Material.TRIPWIRE_HOOK); //donor boost toggle
		ItemMeta jumpBoostToggleMeta = jumpBoostToggle.getItemMeta();
		if(p.hasPermission(Util.premium) || p.hasPermission(Util.platinum))
		{
			jumpBoostToggleMeta.setDisplayName(Util.subColorCodes("&0|&aJump Toggle [ON]&0|"));
			jumpBoostToggleMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Toggle In-Hub Jump and Run Boosts!"));
			jumpBoostToggle.addUnsafeEnchantment(Enchantment.LUCK, 2);
		}
		else
		{
			jumpBoostToggleMeta.setDisplayName(Util.subColorCodes("&0|&a[Donor-Only] Jump Toggle [OFF]&0|"));
			jumpBoostToggleMeta.setLore(Arrays.asList(ChatColor.DARK_RED + "You Must Be A Donator", ChatColor.DARK_RED + "To Use This Feature!", ChatColor.GREEN + "Donate At: PvPitUP.com/Store!"));
			jumpBoostToggle.addUnsafeEnchantment(Enchantment.LUCK, 1);
		}
		jumpBoostToggle.setItemMeta(jumpBoostToggleMeta);
		pi.setItem(2, jumpBoostToggle);

		ItemStack petSelector = new ItemStack(Material.BONE); //pet selector
		ItemMeta petSelectorMeta = petSelector.getItemMeta();
		if(p.hasPermission(Util.premium) || p.hasPermission(Util.platinum))
		{
			petSelectorMeta.setDisplayName(Util.subColorCodes("&0|&aPet Selector!&0|"));
			petSelectorMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Select a Pet!"));
			petSelector.addUnsafeEnchantment(Enchantment.LUCK, 2);
		}
		else
		{
			petSelectorMeta.setDisplayName(Util.subColorCodes("&0|&a[Donor-Only] Pet Selector!&0|"));
			petSelectorMeta.setLore(Arrays.asList(ChatColor.DARK_RED + "You Must Be A Donator", ChatColor.DARK_RED + "To Use This Feature!", ChatColor.GREEN + "Donate At: PvPitUP.com/Store!"));
			petSelector.addUnsafeEnchantment(Enchantment.LUCK, 1);
		}
		petSelector.setItemMeta(petSelectorMeta);
		pi.setItem(3, petSelector);

		ItemStack hatSelector = new ItemStack(Material.JACK_O_LANTERN); //hat selector
		ItemMeta hatSelectorMeta = hatSelector.getItemMeta();
		if(p.hasPermission(Util.premium) || p.hasPermission(Util.platinum))
		{
			hatSelectorMeta.setDisplayName(Util.subColorCodes("&0|&aHat Selector!&0|"));
			hatSelectorMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Select a Hat!"));
			hatSelector.addUnsafeEnchantment(Enchantment.LUCK, 2);
		}
		else
		{
			hatSelectorMeta.setDisplayName(Util.subColorCodes("&0|&a[Donor-Only] Hat Selector&0|"));
			hatSelectorMeta.setLore(Arrays.asList(ChatColor.DARK_RED + "You Must Be A Donator", ChatColor.DARK_RED + "To Use This Feature!", ChatColor.GREEN + "Donate At: PvPitUP.com/Store!"));
			hatSelector.addUnsafeEnchantment(Enchantment.LUCK, 1);
		}
		hatSelector.setItemMeta(hatSelectorMeta);
		pi.setItem(4, hatSelector);

		//Rule Book
		pi.setItem(5, main.ruleBook.getRuleBook());

		ItemStack minigameSelector = new ItemStack(Material.COMPASS); //minigame selector
		ItemMeta minigameSMeta = minigameSelector.getItemMeta();
		minigameSMeta.setDisplayName(Util.subColorCodes("%0|&aMiniGame &bSelector!&0|"));
		minigameSMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Select a MiniGame!"));
		minigameSelector.setItemMeta(minigameSMeta);
		minigameSelector.addUnsafeEnchantment(Enchantment.LUCK, 1);
		pi.setItem(6, minigameSelector);

		ItemStack hubSelector = new ItemStack(Material.WATCH); //hub selector
		ItemMeta hubSMeta = hubSelector.getItemMeta();
		hubSMeta.setDisplayName(Util.subColorCodes("&0|&aHub Selector!&0|"));
		hubSMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Select a Hub!"));
		hubSelector.setItemMeta(hubSMeta);
		hubSelector.addUnsafeEnchantment(Enchantment.LUCK, 1);
		pi.setItem(7, hubSelector);

		ItemStack settings = new ItemStack(Material.ENDER_CHEST);
		ItemMeta settingsMeta = settings.getItemMeta();
		settingsMeta.setDisplayName(Util.subColorCodes("&l&0|&aSettings!&0|"));
		settingsMeta.setLore(Arrays.asList(ChatColor.AQUA + "Right Click While Holding", ChatColor.AQUA + "Me In Your Hand To", ChatColor.AQUA + "Open Your Settings!"));
		settings.setItemMeta(settingsMeta);
		pi.setItem(8, settings);
	}
}
