package me.coldguy101.HubHUD.util;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/**
 * Created by Sean on 8/3/2014.
 */
public class RuleBook
{
	private ItemStack ruleBook;

	public RuleBook(Configuration cfg)
	{
		String serializedRuleBook = null;
		if(cfg.getString("ruleBook") != null)
		{
			serializedRuleBook = cfg.getString("ruleBook");
			ruleBook = BookSerializer.deserializeBook(serializedRuleBook);
		}
		else
			ruleBook = null;
	}

	public ItemStack getRuleBook()
	{
		if(ruleBook == null)
		{
			ruleBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta rbm = (BookMeta) ruleBook.getItemMeta();
			rbm.setAuthor("me");
			rbm.setTitle("its broken");
			rbm.addPage("it appears that you have either not made a default rule book yet, or you have broken everything... Hopefully it is the first one :D");
		}
		return ruleBook;
	}
}
