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
			return new ItemStack(ModBlocks.ARCANATABLE);
		}
	};
	
	@ObjectHolder("examplemod:copperingot")
	public static CopperIngot COPPERINGOT;

	@ObjectHolder("examplemod:copperknife")
	public static CopperKnife COPPERKNIFE;
	
	@ObjectHolder("examplemod:fiber")
	public static Fiber FIBER;
	
	@ObjectHolder("examplemod:primalessence")
	public static PrimalEssence PRIMALESSENCE;
	
	@ObjectHolder("examplemod:fructalessence")
	public static FructalEssence FRUCTALESSENCE;
	
	@ObjectHolder("examplemod:arcanagem")
	public static ArcanaGem ARCANAGEM;
	
	@ObjectHolder("examplemod:diamondshears")
	public static DiamondShears DIAMONDSHEARS;
	
	@ObjectHolder("examplemod:flamewand")
	public static FlameWand FLAMEWAND;
	
	@ObjectHolder("examplemod:melonwand")
	public static MelonWand MELONWAND;
	
	@ObjectHolder("examplemod:jumpwand")
	public static JumpWand JUMPWAND;
	
	@ObjectHolder("examplemod:radarwand")
	public static RadarWand RADARWAND;
	
}
