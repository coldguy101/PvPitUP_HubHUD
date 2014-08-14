package me.coldguy101.HubHUD.settings;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Sean on 8/4/2014.
 */
public class SettingsManager
{
	HashMap<Player, Settings> settingsHashMap = new HashMap<Player, Settings>();

	public Settings getSettings(Player p)
	{
		if(!settingsHashMap.containsKey(p))
			settingsHashMap.put(p, new Settings());
		return settingsHashMap.get(p);
	}
}
