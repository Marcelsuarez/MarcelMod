package com.example.items;

import java.util.List;

import com.example.entities.FWFireball;
import com.example.items.tooltypes.Wand;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RadarWand extends Wand {

	public RadarWand() {
		super(50);
		setRegistryName("radarwand");
		
	}
	
	
	@Override
	   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		    
				ItemStack stack =playerIn.getHeldItemMainhand();
				Item wand = (Item) playerIn.getHeldItemMainhand().getItem(); 
				((Wand)wand).damageWand(playerIn);
				BlockPos center = playerIn.getPosition();
				BlockPos corner1 = center.add(-10, -10, -10);
				BlockPos corner2 = center.add(10, 10, 10);

				List<MonsterEntity> mobs = worldIn.getEntitiesWithinAABB(MonsterEntity.class,new AxisAlignedBB(corner1, corner2));
				
				
				
				for (MonsterEntity mob : mobs)
				{
					mob.addPotionEffect(new EffectInstance(Effects.GLOWING, 120, 1));
				}
				
				
		        worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON
		        		,SoundCategory.BLOCKS, 0.8F, random.nextFloat() * 0.7F + 0.5F);
		        
	          
		      
	          return new ActionResult<>(ActionResultType.SUCCESS, stack);
		   }
	

}
