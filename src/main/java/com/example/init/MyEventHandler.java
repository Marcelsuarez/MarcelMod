package com.example.init;


import java.util.ArrayList;
import java.util.Arrays;

import com.example.items.ArcanumPick;
import com.example.items.tooltypes.Knife;
import com.example.list.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class MyEventHandler
{

	
	
	
	// when the player breaks a sugar cane for plant fiber, lose durability on your knife
	@SubscribeEvent
	public void breakCane(BreakEvent event)
	{
		Block block = event.getState().getBlock();
		if (block == Blocks.SUGAR_CANE)
		{
			if (event.getPlayer().getHeldItemMainhand().getItem() instanceof Knife)
			{
				ItemStack stack = event.getPlayer().getHeldItemMainhand();
				ToolItem knife = (ToolItem) event.getPlayer().getHeldItemMainhand().getItem();
				knife.setDamage(stack, knife.getDamage(stack) + 1);	
			}
		}
	}
	    
	
	        
	    
	    
	    
	    
}
