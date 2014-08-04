package me.coldguy101.HubHUD.administrative;

import me.coldguy101.HubHUD.util.BookSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

/**
 * Created by Sean on 8/3/2014.
 */
public class AdminCommands implements CommandExecutor
{
	private final Plugin main;
	public AdminCommands(Plugin p)
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
		}
		return false;
	}
}
