package com.example.items;

import java.util.ArrayList;
import java.util.Set;

import com.example.list.ModItems;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArcanumAxe extends AxeItem {
	
	
	private static final Set<Block> ALL_LOGS = ImmutableSet.of(Blocks.SPRUCE_LOG, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.OAK_LOG);

	public ArcanumAxe() {
		super(ItemTier.DIAMOND, 1, 2, new Properties().defaultMaxDamage(2000).group(ModItems.itemGroup));
		setRegistryName("arcanumaxe");
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos posl, LivingEntity user)
	{
		
		PlayerEntity player = (PlayerEntity) user;
		BlockPos start = posl;
		Block block = state.getBlock();
			
		
		if (ALL_LOGS.contains(block))
		{
			ArrayList<BlockPos> loglist = new ArrayList<BlockPos>();
		
			while (ALL_LOGS.contains(block))
			{
				
				loglist.add(start);
				start = start.up();
				block = world.getBlockState(start).getBlock();
			}
	
			block = state.getBlock();
			for (BlockPos pos: loglist)
			{
			world.playSound(player, pos, SoundEvents.BLOCK_WOOD_BREAK
					,SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
			block.removedByPlayer(state, world, pos, player, true, null);
			block.onBlockHarvested(world, pos, state, player);
			block.harvestBlock(world, player, pos, state, null, stack);		
			}
			
		
		}
		
		
		
		
		return super.onBlockDestroyed(stack, world, state, posl, user);
	}

}
