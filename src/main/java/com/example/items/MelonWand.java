package com.example.items;

import com.example.entities.FWFireball;
import com.example.items.tooltypes.Wand;
import com.example.list.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MelonWand extends Wand
{

	public MelonWand() {
		super();
		setRegistryName("melonwand");
		
	}
	
	   public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
		      if (target instanceof MonsterEntity) {
		         MonsterEntity monster = (MonsterEntity)target;
		         if (monster.isAlive()) {
		            
		          BlockPos position =  monster.getPosition();
		          position = position.up().up();
		          World world = playerIn.getEntityWorld();
		          Block checkAir = world.getBlockState(position).getBlock();
		          if (checkAir == Blocks.AIR || checkAir == Blocks.CAVE_AIR || checkAir == Blocks.VOID_AIR)
		          {
					ItemStack wandItem =playerIn.getHeldItemMainhand();
					Item wand = (Item) playerIn.getHeldItemMainhand().getItem(); 
					wand.setDamage(wandItem, wand.getDamage(wandItem) + 2);
		        	  monster.remove();
		        	  IParticleData particle = ParticleTypes.SMOKE;
		        	  world.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_WITHER_BREAK_BLOCK
				        		,SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
		        	  world.addParticle(particle, position.getX(), position.getY(), position.getZ(),0.0, 0.0, 0.0);
		        	  world.setBlockState(position, ModBlocks.CONJUREDMELON.getDefaultState());
		          }
		          
		          
		         }

		         return true;
		      } else {
		         return false;
		      }
		   }
	
	
	
	
	
	
	
}
