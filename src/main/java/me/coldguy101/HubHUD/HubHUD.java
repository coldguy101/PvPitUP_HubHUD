package me.coldguy101.HubHUD;

import com.dsh105.echopet.api.EchoPetAPI;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;
import me.coldguy101.HubHUD.administrative.AdminCommands;
import me.coldguy101.HubHUD.listeners.*;
import me.coldguy101.HubHUD.menus.HatSelectorMenu;
import me.coldguy101.HubHUD.menus.HubSelectorMenu;
import me.coldguy101.HubHUD.menus.PlayerSettingsMenu;
import me.coldguy101.HubHUD.settings.SettingsManager;
import me.coldguy101.HubHUD.util.BungeeUtil;
import me.coldguy101.HubHUD.util.InventoryManager;
import me.coldguy101.HubHUD.util.RuleBook;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashSet;

/**
 * Created by Sean on 7/31/2014.
 */
public class HubHUD extends JavaPlugin implements PluginMessageListener
{
	public RuleBook ruleBook;
	public EchoPetAPI echoPetAPI;
	public SettingsManager settingsManager;
	public InventoryManager inventoryManager;
	public EffectManager effectManager;

	public static HashSet<Player> ignorePlayers = new HashSet<Player>();

	@Override
	public void onEnable()
	{
		BungeeUtil.registerBungee(this, this);
		settingsManager = new SettingsManager();
		ruleBook = new RuleBook(getConfig());
		inventoryManager = new InventoryManager(this);
		effectManager = new EffectManager(EffectLib.instance());
		echoPetAPI = EchoPetAPI.getAPI();

		//events
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(settingsManager), this);
		getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
		getServer().getPluginManager().registerEvents(new PlayerBuild(), this);
		getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
		getServer().getPluginManager().registerEvents(new PlayerDrop(), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
		getServer().getPluginManager().registerEvents(new PlayerRespawn(this), this);

		//menus
		//getServer().getPluginManager().registerEvents(new MinigameSelectorMenu(), this);
		getServer().getPluginManager().registerEvents(new HubSelectorMenu(), this);
		getServer().getPluginManager().registerEvents(new HatSelectorMenu(), this);
		getServer().getPluginManager().registerEvents(new PlayerSettingsMenu(settingsManager), this);

		getCommand("setRuleBook").setExecutor(new AdminCommands(this));
		getCommand("setparticletext").setExecutor(new AdminCommands(this));
		getCommand("ignoreme").setExecutor(new AdminCommands(this));
		getCommand("testBungeeNumPlayers").setExecutor(new AdminCommands(this));
	}

	@Override
	public void onDisable()
	{

	}

	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message)
	{
		getLogger().info("Got Plugin Message on " + channel + " from " + player.getName() + " messge was: " + message.toString());
	}
}
