package com.pcl.simpleinfopanels.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityInfoExtender extends TileEntity implements /*SimpleComponent, ManagedPeripheral,*/ ITickable {

	public int masterX = 0;
	public int masterY = 0;
	public int masterZ = 0;
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("masterX", this.masterX);
		nbt.setInteger("masterY", this.masterY);
		nbt.setInteger("masterZ", this.masterZ);
		
		return nbt;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.masterX = nbt.getInteger("masterX");
		this.masterY = nbt.getInteger("masterY");
		this.masterZ = nbt.getInteger("masterZ");
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	public void setMasterX(int x) {
		this.masterX = x;
	}
	public void setMasterY(int y) {
		this.masterY = y;
	}
	public void setMasterZ(int z) {
		this.masterZ = z;
	}
	
	public int getMasterX() {
		return this.masterX;
	}
	public int getMasterY() {
		return this.masterY;
	}
	public int getMasterZ() {
		return this.masterZ;
	}
}
