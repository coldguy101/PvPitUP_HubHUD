package me.coldguy101.HubHUD;

import me.coldguy101.HubHUD.commands.GiveHudCommand;
import me.coldguy101.HubHUD.listeners.PlayerJoinListener;
import me.coldguy101.HubHUD.listeners.PlayerOpenMinigameSelector;
import me.coldguy101.HubHUD.util.BungeeUtil;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Sean on 7/31/2014.
 */
public class HubHUD extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		registerListeners();
		registerCommands();
		BungeeUtil bu = new BungeeUtil(this, this);
	}

	@Override
	public void onDisable()
	{

	}

	private void registerListeners()
	{
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerOpenMinigameSelector(this.getConfig()), this);
	}

	private void registerCommands()
	{
		getCommand("giveHUD").setExecutor(new GiveHudCommand());
	}
}
