package com.pcl.simpleinfopanels.gui;

import com.pcl.simpleinfopanels.tileentities.ContainerInfoPanel;
import com.pcl.simpleinfopanels.tileentities.TileEntityInfoPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SIPGuiHandler  implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityInfoPanel) {
				return new ContainerInfoPanel(player.inventory, (TileEntityInfoPanel) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
			if (tileEntity instanceof TileEntityInfoPanel) {
				return new GuiInfoPanel(player.inventory, (TileEntityInfoPanel) tileEntity);
			}
		}
		return null;
	}

}
