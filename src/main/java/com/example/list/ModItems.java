package com.example.list;

import com.example.items.*;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {
	
	
	public static ItemGroup itemGroup = new ItemGroup("examplemod") {
		@Override
		public ItemStack createIcon() 
		{
			return new ItemStack(ModBlocks.COPPERORE);
		}
	};
	
	@ObjectHolder("examplemod:copperingot")
	public static CopperIngot COPPERINGOT;

	@ObjectHolder("examplemod:copperknife")
	public static CopperKnife COPPERKNIFE;
	
	@ObjectHolder("examplemod:fiber")
	public static Fiber FIBER;
	
	@ObjectHolder("examplemod:diamondshears")
	public static DiamondShears DIAMONDSHEARS;
	
}
