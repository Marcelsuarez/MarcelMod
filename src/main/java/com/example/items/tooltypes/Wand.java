package com.example.items.tooltypes;

import com.example.list.ModItems;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Wand extends Item{

	public Wand(int durability)
	{
		super(new Properties().defaultMaxDamage(durability).group(ModItems.itemGroup));
		
	}


	public void damageWand(PlayerEntity player)
	{
		ItemStack stack = player.getHeldItemMainhand();
		this.setDamage(stack, this.getDamage(stack) + 1);
		
		if (this.getDamage(stack) >= this.getMaxDamage(stack))
		{
			World world = player.getEntityWorld();
			world.playSound(player, player.getPosition(), SoundEvents.ENTITY_ITEM_BREAK
	        		,SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
			//player.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			player.sendBreakAnimation(player.getActiveHand());
			stack.shrink(1);
		}
		

		
		
	}
	
	
	
}
