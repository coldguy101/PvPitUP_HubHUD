package me.coldguy101.HubHUD.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;
import java.nio.charset.Charset;

public final class BungeeUtil
{
	private static final Charset UTF8 = Charset.forName("UTF-8");
	private static Plugin instance;

	public static void registerBungee(Plugin plugin, PluginMessageListener listener)
	{
		instance = plugin;
		Bukkit.getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", listener);
	}

	public static void movePlayerToServer(Player p, String serverName)
	{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try
		{
			out.writeUTF("Connect");
			out.writeUTF(serverName.trim());
		}
		catch (IOException ex)
		{
			// Impossibru
		}
		p.sendPluginMessage(instance, "BungeeCord", b.toByteArray());
	}

	public static boolean sendBungeeMessage(Plugin plugin, String server, BungeeMessage msg)
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(outBytes);

		try
		{
			out.writeUTF("Forward");
			out.writeUTF(server);
			out.writeUTF(msg.getChannel());
			out.writeShort(msg.getData().length);
			out.write(msg.getData());

			final Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
			if (onlinePlayers.length != 0)
			{
				onlinePlayers[0].sendPluginMessage(plugin, "BungeeCord", outBytes.toByteArray());
				return true;
			}
		}
		catch (IOException ignored)
		{
		}
		return false;
	}

	public static BungeeMessage getBungeeMessage(byte[] rawData)
	{
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(rawData));
		try
		{
			String channel = dis.readUTF();
			byte[] msgData = new byte[dis.readUnsignedShort()];
			dis.read(msgData);
			return new BungeeMessage(channel, msgData);
		}
		catch (IOException ignored)
		{
		}
		return null;
	}

	public static class BungeeMessage
	{
		private String channel;
		private byte[] data;

		public BungeeMessage(String channel, byte[] data)
		{
			this.channel = channel;
			this.data = data;
		}

		public BungeeMessage(String channel, String data)
		{
			this(channel, data.getBytes(UTF8));
		}

		public String getChannel()
		{
			return channel;
		}

		public byte[] getData()
		{
			return data;
		}

		public String getDataAsString()
		{
			return new String(data, UTF8);
		}
	}
}
