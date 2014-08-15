package me.coldguy101.HubHUD.administrative;

import de.slikey.effectlib.effect.TextLocationEffect;
import me.coldguy101.HubHUD.HubHUD;
import me.coldguy101.HubHUD.util.BookSerializer;
import me.coldguy101.HubHUD.util.BungeeUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Sean on 8/3/2014.
 */
public class AdminCommands implements CommandExecutor
{
	private final HubHUD main;
	public AdminCommands(HubHUD p)
	{
		main = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(!(sender instanceof Player))
		{
			sender.sendMessage("These commands can only be done by players.");
			return false;
		}
		Player player = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("setRuleBook"))
		{
			ItemStack book = player.getItemInHand();
			if(book.getType() == Material.WRITTEN_BOOK)
			{
				String str = BookSerializer.serializeBook(book);
				main.getConfig().set("ruleBook", str);
				main.saveConfig();
				player.sendMessage(ChatColor.GREEN + "Successful.");
			}
			else
			{
				player.sendMessage(ChatColor.RED + "That item must be a book.");
			}
			return true;
		}
		else if (cmd.getName().equalsIgnoreCase("setparticletext"))
		{
			if(args.length != 2)
				return false;
			TextLocationEffect effect = new TextLocationEffect(main.effectManager, player.getLocation());
			//effect.text = args[0];
			/*String text = "Welcome To PvPitUP!";
			long size = 0.2;
			if(args[0].contains("\""))
				for(int x = 0; x < args.length; x++)
				{
					if(x == 0)
						text += args[x];
					else if(!args[x].contains("\""))
						text += args[x];
					else
						break;
				}
			effect.text = text;
			effect.size = Float.parseFloat(args[1]); */
			effect.text = "Welcome To PvPitUP!";
			effect.infinite();
			effect.start();
			player.sendMessage("created");
			return true;
		}
		else if (cmd.getName().equalsIgnoreCase("ignoreme"))
		{
			if(!HubHUD.ignorePlayers.contains(player))
			{
				HubHUD.ignorePlayers.add(player);
				player.sendMessage("The plugin is now ignoring you. You can place blocks and break blocks as usual.");
			}
			else
			{
				HubHUD.ignorePlayers.remove(player);
				player.sendMessage("The plugin is no longer ignoring you. It will block you from placing and breaking blocks and stuff.");
			}
		}
		else if(cmd.getName().equalsIgnoreCase("testBungeeNumPlayers"))
		{
			if(args.length != 1)
				return false;
			BungeeUtil.getNumPlayers(player, args[0]);
			player.sendMessage("message sent. should be getting feedback in console.");
		}
		return true;
	}
}
