package com.pcl.simpleinfopanels;

import java.io.File;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class SIPConfig {

	// All config values
	public static String defaultURL = "StreamURL";
	public static boolean enableMUD = true;
	public static boolean enableStreams = true;
	public static Configuration config;

	// Called by OpenFM preInit()
	public static void init(File configFile) {

		config = new Configuration(configFile);

		try {
			config.load();
			defaultURL = config.get("general", "defaultURL", defaultURL, "The default stream of the player.").getString();
			enableMUD = config.get("general", "enableMUD", enableMUD, "Automatically check for mod updates.").getBoolean();
			enableStreams = config.get("general", "enableStreams", enableStreams, "Should we try to play streams at all? If false streams will not work in game. (Client side only)").getBoolean();
		} catch(Exception e) {
			SimpleInfoPanels.logger.error("SIP encountered a problem with loading the config file.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	public static void sync() {
		if (config.hasChanged()) {
			config.save();
		}
	}
	
	public static ConfigCategory getCategory(String name) {
		// TODO Auto-generated method stub
		return config.getCategory(name.toLowerCase()).setLanguageKey(name.toLowerCase().replace(" ", ""));
	}
}