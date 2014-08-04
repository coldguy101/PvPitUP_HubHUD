package me.coldguy101.HubHUD.util;

import org.bukkit.permissions.Permission;

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
}
