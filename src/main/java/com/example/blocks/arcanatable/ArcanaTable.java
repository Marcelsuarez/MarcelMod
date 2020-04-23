package com.example.blocks.arcanatable;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

public class ArcanaTable extends Block {

	public ArcanaTable() {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.METAL)
				.harvestLevel(2)
				.hardnessAndResistance(3.0f)
				.lightValue(5));
		setRegistryName("arcanatable");
	}

	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;	
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new ArcanaTableTile();
	}
	
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if (entity != null) {
            world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
        }
    }
    
    @Override
    public boolean onBlockActivated(BlockState block, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
    	if (!world.isRemote)
    	{
    		TileEntity tile = world.getTileEntity(pos);
    		if (tile instanceof INamedContainerProvider)
    		{
    			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider)tile, tile.getPos());
    		}
    	}
    	return super.onBlockActivated(block, world,pos,player, hand, hit);
    }
    
    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
        Vec3d vec = entity.getPositionVec();
        return Direction.getFacingFromVector((float) (vec.x - clickedBlock.getX()), (float) (vec.y - clickedBlock.getY()), (float) (vec.z - clickedBlock.getZ()));
    }
    
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }
    
    
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            // drops everything in the inventory when destroyed
            worldIn.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                for (int i = 0; i < h.getSlots(); i++) {
                    spawnAsEntity(worldIn, pos, h.getStackInSlot(i));
                }
            });
            worldIn.removeTileEntity(pos);
        }
    }
    
    
}
