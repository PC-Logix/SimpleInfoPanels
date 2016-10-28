package com.pcl.simpleinfopanels;

import com.pcl.simpleinfopanels.blocks.BlockInfoPanel;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ContentRegistry {
    // Tabs
    public static CreativeTabs creativeTab;
    public static Block blockInfoPanel;
    // Called on mod init()
	public static void init() {
        registerTabs();
        registerBlocks();
        //registerItems();
        //registerEvents();
	}
	
	public static void registerBlocks() {
		blockInfoPanel = new BlockInfoPanel();
		GameRegistry.registerBlock(blockInfoPanel, "simpleinfopanels.InfoPanel");
		blockInfoPanel.setCreativeTab(creativeTab);
	}
	
	public static void registerTabs() {

		creativeTab = new CreativeTabs("tabOpenFM") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(blockInfoPanel);
			}

			@SideOnly(Side.CLIENT)
			public String getTranslatedTabLabel() {
				return I18n.translateToLocal("itemGroup.SIP.tabSIP");
			}
		};
	}
}
