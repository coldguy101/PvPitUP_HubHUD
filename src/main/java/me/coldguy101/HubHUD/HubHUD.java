package me.coldguy101.HubHUD;

import com.dsh105.echopet.api.EchoPetAPI;
import me.coldguy101.HubHUD.administrative.AdminCommands;
import me.coldguy101.HubHUD.listeners.EntityDamageByEntity;
import me.coldguy101.HubHUD.listeners.PlayerDrop;
import me.coldguy101.HubHUD.listeners.PlayerInteract;
import me.coldguy101.HubHUD.listeners.PlayerJoin;
import me.coldguy101.HubHUD.menus.HubSelectorMenu;
import me.coldguy101.HubHUD.menus.MinigameSelectorMenu;
import me.coldguy101.HubHUD.menus.PlayerSettingsMenu;
import me.coldguy101.HubHUD.settings.SettingsManager;
import me.coldguy101.HubHUD.util.BungeeUtil;
import me.coldguy101.HubHUD.util.RuleBook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 * Created by Sean on 7/31/2014.
 */
public class HubHUD extends JavaPlugin implements PluginMessageListener
{
	public RuleBook ruleBook;
	public EchoPetAPI echoPetAPI;
	public SettingsManager settingsManager = new SettingsManager();

	@Override
	public void onEnable()
	{
		BungeeUtil.registerBungee(this, this);
		ruleBook = new RuleBook(getConfig());
		echoPetAPI = EchoPetAPI.getAPI();

		//events
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new PlayerDrop(), this);

		//menus
		getServer().getPluginManager().registerEvents(new MinigameSelectorMenu(this), this);
		getServer().getPluginManager().registerEvents(new HubSelectorMenu(this), this);
		getServer().getPluginManager().registerEvents(new PlayerSettingsMenu(this), this);

		getCommand("setRuleBook").setExecutor(new AdminCommands(this));
	}

	@Override
	public void onDisable()
	{

	}

	@Override
	public void onPluginMessageReceived(String s, Player player, byte[] bytes)
	{

	}
}
