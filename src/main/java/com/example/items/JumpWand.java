package com.example.items;

import com.example.items.tooltypes.Wand;
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
import net.minecraft.world.World;

public class JumpWand extends Wand {

	public JumpWand() {
		super(50);
		setRegistryName("jumpwand");
	}

	
	@Override
	   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		    
				ItemStack stack =playerIn.getHeldItemMainhand();
				Item wand = (Item)playerIn.getHeldItemMainhand().getItem(); 
				((Wand)wand).damageWand(playerIn);
					
				worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_RABBIT_JUMP
		        		,SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
				EffectInstance effect = new EffectInstance(Effects.JUMP_BOOST, 100, 4);
				
				playerIn.addPotionEffect(effect);
	          
		      
	          return new ActionResult<>(ActionResultType.SUCCESS, stack);
		   }
	
	
	
	
	
	
	
}
