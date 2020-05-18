package com.example.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import com.example.list.ModItems;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemStack;

public class ArcanumPick extends PickaxeItem {

	
	public ArcanumPick() {
		super(ItemTier.DIAMOND, 1, 2, new Properties().defaultMaxDamage(2000).group(ModItems.itemGroup));
		setRegistryName("arcanumpick");
	}
	
	

	
	
	//for the 3x3
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, BlockState state, BlockPos posl, LivingEntity user)
	{
		PlayerEntity player = (PlayerEntity) user;
		BlockPos center = posl;
		Block block = state.getBlock();
		ArrayList<BlockPos> breakList = new ArrayList<BlockPos>();
		Vec3d vec = player.getPositionVec();
		Direction playerDir = Direction.getFacingFromVector(	(float) (vec.x - center.getX()),
																						(float) (vec.y - center.getY()),
																						(float) (vec.z - center.getZ()));
				
		switch (playerDir)
		{
		case NORTH:
		case SOUTH:
			System.out.println("north looking"); //dont touch z
			BlockPos[] northlist = {	center.add(1, 1, 0), center.add(0, 1, 0), center.add(-1, 1, 0),
												center.add(1, 0, 0), center, center.add(-1, 0, 0),
												center.add(1, -1, 0), center.add(0, -1, 0), center.add(-1, -1, 0)};
			breakList.clear();
			breakList.addAll(Arrays.asList(northlist));
			break;
		case EAST:
		case WEST:
			System.out.println("west looking"); //dont touch x
			BlockPos[] westlist = {	center.add(0, 1, 1), center.add(0, 1, 0), center.add(0, 1, -1),
												center.add(0, 0, 1), center, center.add(0, 0, -1),
												center.add(0, -1, 1), center.add(0, -1, 0), center.add(0, -1, -1)};
			breakList.clear();
			breakList.addAll(Arrays.asList(westlist));
			break;
		case UP:
		case DOWN:
			System.out.println("up looking"); //dont touch y
			BlockPos[] uplist = {	center.add(1, 0, 1), center.add(0, 0, 1), center.add(-1, 0, 1),
											center.add(1, 0, 0), center, center.add(-1, 0, 0),
											center.add(1, 0, -1), center.add(0, 0, -1), center.add(-1, 0, -1)};
			breakList.clear();
			breakList.addAll(Arrays.asList(uplist));
			break;
			
		default:
			break;
						
		}
		
		for (BlockPos pos : breakList)
		{
			
			if (this.canHarvestBlock(state))
			{
				block.removedByPlayer(state, world, pos, player, true, null);
				block.onBlockHarvested(world, pos, state, player);
				block.harvestBlock(world, player, pos, state, null, stack);
			}
		}
		
		return super.onBlockDestroyed(stack, world, state, posl, user);
		
	}

}
