package me.coldguy101.HubHUD;

import com.dsh105.echopet.api.EchoPetAPI;
import me.coldguy101.HubHUD.administrative.AdminCommands;
import me.coldguy101.HubHUD.listeners.EntityDamageByEntity;
import me.coldguy101.HubHUD.listeners.PlayerInteract;
import me.coldguy101.HubHUD.listeners.PlayerJoinListener;
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

	@Override
	public void onEnable()
	{
		BungeeUtil.registerBungee(this, this);
		ruleBook = new RuleBook(getConfig());
		echoPetAPI = EchoPetAPI.getAPI();

		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);

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
