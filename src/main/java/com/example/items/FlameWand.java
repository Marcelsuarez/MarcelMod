package com.example.items;

import com.example.entities.FWFireball;
import com.example.items.tooltypes.Wand;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FlameWand extends Wand {

	public FlameWand() {
		super(100);
		setRegistryName("flamewand");
		
	}

	

	
		@Override
	   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		    
				ItemStack stack =playerIn.getHeldItemMainhand();
				Item wand = (Item) playerIn.getHeldItemMainhand().getItem(); 
				//wand.setDamage(stack, wand.getDamage(stack) + 1);	
				((Wand)wand).damageWand(playerIn);
	        	Vec3d playerpos = playerIn.getLook(1.0F);
	        	Vec3d targetpos = playerpos.scale(5.0);		//vector math woooo
	          
		        FWFireball fireballentity = new FWFireball(worldIn, playerIn, targetpos.x, targetpos.y, targetpos.z);
		        fireballentity.explosionPower = 1;
		        fireballentity.posX = playerIn.posX + playerpos.x * 4.0D;
		        fireballentity.posY = playerIn.posY + (double)(playerIn.getHeight() / 2.0F) + 0.8D;
		        fireballentity.posZ = playerIn.posZ + playerpos.z * 4.0D;
		        worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_FIREWORK_ROCKET_LAUNCH
		        		,SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
		        worldIn.addEntity(fireballentity);
	          
		      
	          return new ActionResult<>(ActionResultType.SUCCESS, stack);
		   }
	
	
}
