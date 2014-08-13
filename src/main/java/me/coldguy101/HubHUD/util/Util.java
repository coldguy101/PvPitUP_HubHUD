package me.coldguy101.HubHUD.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 8/4/2014.
 */
public class Util
{
	public static Permission premium = new Permission("pvpitup.premium");
	public static Permission platinum = new Permission("pvpitup.platinum");

	public static String subColorCodes(String str)
	{
		return str.replaceAll("&", "\u00A7");
	}

	public static List<String> subColorCodes(List<String> l)
	{
		List<String> list = new ArrayList<String>();
		for(String s : l)
			list.add(subColorCodes(s));
		return list;
	}

	public static ItemStack nameAndLore(ItemStack is, String name, List<String> lore)
	{
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(subColorCodes(name));
		im.setLore(subColorCodes(lore));
		is.setItemMeta(im);
		return is;
	}
}
