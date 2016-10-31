package com.pcl.simpleinfopanels;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=BuildInfo.modID, name=BuildInfo.modName, version=BuildInfo.versionNumber + "." + BuildInfo.buildNumber, dependencies = "", guiFactory = "com.pcl.simpleinfopanels.gui.SIPGuiFactory")

public class SimpleInfoPanels
{
	@Mod.Instance(BuildInfo.modID)
	public static SimpleInfoPanels instance;
	@SidedProxy(clientSide="com.pcl.simpleinfopanels.ClientProxy", serverSide="com.pcl.simpleinfopanels.CommonProxy")
	public static CommonProxy proxy;
	public Configuration config;
	public static final org.apache.logging.log4j.Logger logger = LogManager.getFormatterLogger(BuildInfo.modID);
	public static File configFile; 
	
	@EventHandler
	public void preInt(FMLPreInitializationEvent event) {
		ContentRegistry.init();
		proxy.initTileEntities();
		proxy.registerItemRenderers();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerRenderers();
    }
}
