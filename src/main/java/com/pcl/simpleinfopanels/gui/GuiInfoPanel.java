package com.pcl.simpleinfopanels.gui;

import com.pcl.simpleinfopanels.tileentities.ContainerInfoPanel;
import com.pcl.simpleinfopanels.tileentities.TileEntityInfoPanel;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GuiInfoPanel extends GuiContainer {

	public GuiInfoPanel(InventoryPlayer inventory, TileEntityInfoPanel tileEntity) {
		super(new ContainerInfoPanel(inventory, tileEntity));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
