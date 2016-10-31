package com.pcl.simpleinfopanels;

import com.pcl.simpleinfopanels.blocks.BlockInfoExtender;
import com.pcl.simpleinfopanels.blocks.BlockInfoPanel;
import com.pcl.simpleinfopanels.items.ItemMemoryCard;
import com.pcl.simpleinfopanels.tileentities.TileEntityInfoExtender;
import com.pcl.simpleinfopanels.tileentities.TileEntityInfoPanel;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ContentRegistry {
    // Tabs
    public static CreativeTabs creativeTab;
    public static Block blockInfoPanel;
    public static Block blockInfoExtender;
    public static Item itemMemoryCard;
    // Called on mod init()
	public static void init() {
        registerTabs();
        registerBlocks();
        registerItems();
        //registerEvents();
	}
	
	public static void registerBlocks() {
		blockInfoPanel = new BlockInfoPanel();
		GameRegistry.registerBlock(blockInfoPanel, "InfoPanel");
		GameRegistry.registerTileEntity(TileEntityInfoPanel.class, "InfoPanel");
		blockInfoPanel.setCreativeTab(creativeTab);
		
		blockInfoExtender = new BlockInfoExtender();
		GameRegistry.registerBlock(blockInfoExtender, "InfoExtender");
		GameRegistry.registerTileEntity(TileEntityInfoExtender.class, "InfoExtender");
		blockInfoExtender.setCreativeTab(creativeTab);
	}
	
	public static void registerItems() {
        itemMemoryCard = new ItemMemoryCard();
        GameRegistry.registerItem(itemMemoryCard, "MemoryCard");
        itemMemoryCard.setCreativeTab(creativeTab);
        GameRegistry.addRecipe(new ItemStack(itemMemoryCard), "x x", "y y", "  z",
                'x', new ItemStack(Items.REDSTONE),
                'y', new ItemStack(Items.REDSTONE),
                'z', new ItemStack(Items.PAPER));
	}
	
	public static void registerTabs() {

		creativeTab = new CreativeTabs("tabSimpleInfoPanels") {
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
