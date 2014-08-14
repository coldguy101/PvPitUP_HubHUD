package me.coldguy101.HubHUD.settings;

/**
 * Created by Sean on 8/4/2014.
 */
public class Settings
{
	boolean enableSlap = true;
	boolean enableBlaster = true;
	boolean playersHidden = false;

	public boolean isSlapEnabled()
	{
		return enableSlap;
	}

	public void toggleSlap()
	{
		enableSlap = !enableSlap;
	}

	public boolean isBlasterEnabled()
	{
		return enableBlaster;
	}

	public void toggleBlaster()
	{
		enableBlaster = !enableBlaster;
	}

	public boolean isPlayersHidden()
	{
		return playersHidden;
	}

	public void toggleHidePlayers()
	{
		playersHidden = !playersHidden;
	}
}
