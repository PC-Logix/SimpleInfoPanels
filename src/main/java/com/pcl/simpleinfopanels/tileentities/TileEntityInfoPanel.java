package com.pcl.simpleinfopanels.tileentities;

import com.pcl.simpleinfopanels.blocks.BlockInfoPanel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class TileEntityInfoPanel extends TileEntity implements /*SimpleComponent, ManagedPeripheral,*/ IInventory, ITickable {

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		return nbt;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		int dir = this.getBlockMetadata();
		if (dir == 0) {
			int i = 0;
			// Scan area, looking for extenders
			for (int x = this.getPos().getX() - 8; x < this.getPos().getX() + 9; x++) {
				for (int y = this.getPos().getY() - 8; y < this.getPos().getY() + 9; y++) {
					TileEntity tile = worldObj.getTileEntity(new BlockPos(x, y, this.getPos().getZ()));
					// Make sure tile isn't null, is an instance of the same Tile, and isn't already a part of a multiblock (if ours is already part of one)
					if (tile != null && (tile instanceof TileEntityInfoExtender) && tile.getBlockMetadata() == dir) {
						if (((TileEntityInfoExtender) tile).getMasterX() == 0) {
							((TileEntityInfoExtender) tile).setMasterX(x);
							((TileEntityInfoExtender) tile).setMasterY(y);
							((TileEntityInfoExtender) tile).setMasterZ(this.getPos().getZ());
							System.out.println("Extender At: " + x + " " + y);
						}
						//((TileEntityInfoExtender) tile).findOthers();
					}
				}
				//System.out.println("MEEP " + x);
			}
		} else if (dir == 1) {

		} else if (dir == 2) {

		} else if (dir == 3) {

		}
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
}
