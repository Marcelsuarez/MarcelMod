package com.example.blocks.arcanatable;


import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ArcanaOutputSlot extends SlotItemHandler {


	
	//prevent player from putting in items
	
	public ArcanaOutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
	}

		@Override
	   public boolean isItemValid(ItemStack stack) {
		      return false;
		   }
	
	
	
	

}
