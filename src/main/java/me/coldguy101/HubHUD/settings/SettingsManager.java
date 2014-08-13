package me.coldguy101.HubHUD.settings;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Sean on 8/4/2014.
 */
public class SettingsManager
{
	HashMap<Player, Settings> settingsHashMap = new HashMap<Player, Settings>();

	public void giveDefaultSettings(Player p)
	{
		settingsHashMap.put(p, new Settings());
	}

	public Settings getSettings(Player p)
	{
		return settingsHashMap.get(p);
	}

	public void setSettings(Player p, Settings s)
	{
		settingsHashMap.put(p, s);
	}
}
