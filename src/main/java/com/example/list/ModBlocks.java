package com.example.list;


import com.example.blocks.*;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks
{

	@ObjectHolder("examplemod:copperore")
	public static CopperOre COPPERORE;
	
	@ObjectHolder("examplemod:compressedcobble")
	public static CompressedCobble COMPRESSEDCOBBLE;
	
	@ObjectHolder("examplemod:arcanatable")
	public static ArcanaTable ARCANATABLE;
	
	@ObjectHolder("examplemod:arcanatable")
	public static TileEntityType<ArcanaTableTile> ARCANATABLE_TILE;
	
	@ObjectHolder("examplemod:arcanatable")
	public static ContainerType<ArcanaTableContainer> ARCANATABLE_CONTAINER;
	
	
	
	
}
