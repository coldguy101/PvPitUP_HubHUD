package me.coldguy101.HubHUD.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

/**
 * Created by Sean on 8/3/2014.
 */
public class BookSerializer
{
	/**
	 * Takes an ItemStack of a WRITTEN_BOOK and changes it to a String
	 * @param book
	 * @return String with book info in it
	 */
	public static String serializeBook(ItemStack book)
	{
		String str = "";
		if(book.getType() == Material.WRITTEN_BOOK)
		{
			BookMeta bMeta = (BookMeta) book.getItemMeta();
			String title = bMeta.getTitle();
			String author = bMeta.getAuthor();
			List<String> pages = bMeta.getPages();
			str += title + "##" + author + "##";
			for(String s : pages)
				str += s + "@@";
		}
		return str;
	}

	/**
	 * Takes a String made from the above method and turns it into an ItemStack
	 * @param str
	 * @return ItemStack of WRITTEN_BOOK with all the text written inside
	 */
	public static ItemStack deserializeBook(String str)
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bMeta = (BookMeta) book.getItemMeta();
		String[] sections = str.split("##");
		bMeta.setTitle(sections[0]);
		bMeta.setAuthor(sections[1]);
		String[] pages = sections[2].split("@@");
		for(String s : pages)
			bMeta.addPage(s);
		book.setItemMeta(bMeta);
		return book;
	}
}
