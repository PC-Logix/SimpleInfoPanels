package com.pcl.simpleinfopanels.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.pcl.simpleinfopanels.SimpleInfoPanels;
import com.pcl.simpleinfopanels.tileentities.TileEntityInfoExtender;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class BlockInfoExtender extends Block implements ITileEntityProvider {
	private Random random;
	
	public BlockInfoExtender() {
		super(Material.IRON);
		setHardness(2.0F);
		setResistance(10.0F);
		setUnlocalizedName("simpleinfopanels.BlockInfoExtender");
		random = new Random();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityInfoExtender tileEntity = (TileEntityInfoExtender) world.getTileEntity(pos);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		//player.openGui(SimpleInfoPanels.instance, 0, world, tileEntity.getMasterX(), tileEntity.getMasterY(), tileEntity.getMasterZ());
		return true;
	}
	
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		EnumFacing facing = state.getValue(PROPERTYFACING);
		int facingbits = facing.getHorizontalIndex();
		return facingbits;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {PROPERTYFACING});
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return null;
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		super.onBlockPlaced(world, pos, blockFaceClickedOn, hitX, hitY, hitZ, meta, placer);
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
	}
	
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    	TileEntity te = worldIn.getTileEntity(pos);
		//((TileEntityRadio) te).setOwner(placer.getUniqueID().toString());
    }
	
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}
	
	public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		return true;
	}
    
	@SuppressWarnings("deprecation")
	public void onNeighborBlockChange(World world, BlockPos pos, Block block) {
		boolean flag = world.isBlockPowered(pos);
		try {
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (block.canProvidePower((IBlockState) block.getBlockState())) {
				TileEntity tileEntity;
				if (side == Side.SERVER) {
					tileEntity = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getTileEntity(pos);
				} else {
					tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(pos);
				}
				//((TileEntityRadio)tileEntity).setRedstoneInput(flag);
			}
		}
		catch (Exception localException) { }
	}

	public void dropContent(IInventory chest, World world, int xCoord, int yCoord, int zCoord) {
		if (chest == null) {
			return;
		}

		for (int i1 = 0; i1 < chest.getSizeInventory(); ++i1) {
			ItemStack itemstack = chest.getStackInSlot(i1);

			if (itemstack != null) {
				float offsetX = random.nextFloat() * 0.8F + 0.1F;
				float offsetY = random.nextFloat() * 0.8F + 0.1F;
				float offsetZ = random.nextFloat() * 0.8F + 0.1F;
				EntityItem entityitem;

				for (; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
					int stackSize = random.nextInt(21) + 10;
					if (stackSize > itemstack.stackSize) {
						stackSize = itemstack.stackSize;
					}

					itemstack.stackSize -= stackSize;
					entityitem = new EntityItem(world, xCoord + offsetX, yCoord + offsetY, zCoord + offsetZ, new ItemStack(itemstack.getItem(), stackSize, itemstack.getItemDamage()));

					float velocity = 0.05F;
					entityitem.motionX = (float)random.nextGaussian() * velocity;
					entityitem.motionY = (float)random.nextGaussian() * velocity + 0.2F;
					entityitem.motionZ = (float)random.nextGaussian() * velocity;

					if (itemstack.hasTagCompound()) {
						entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
					}
				}
			}
		}
	}
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityInfoExtender();
	}
}
