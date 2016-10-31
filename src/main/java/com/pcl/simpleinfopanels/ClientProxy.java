package com.pcl.simpleinfopanels;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemRenderers() {
		registerBlockItem(ContentRegistry.blockInfoPanel, 0, "InfoPanel");
		registerBlockItem(ContentRegistry.blockInfoExtender, 0, "InfoExtender");
		registerItem(ContentRegistry.itemMemoryCard, "MemoryCard");
	}
	
	public static void registerBlockItem(final Block block, int meta, final String blockName)
    {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(BuildInfo.modID + ":" + blockName, "inventory"));
		SimpleInfoPanels.logger.info("Registering " + blockName + " Item Renderer");
    }
	
	public static void registerItem(final Item item, final String itemName)
    {
		ModelLoader.setCustomModelResourceLocation(item,  0, new ModelResourceLocation(BuildInfo.modID + ":" + itemName, "inventory"));
		SimpleInfoPanels.logger.info("Registering " + itemName + " Item Renderer");
    }
}
