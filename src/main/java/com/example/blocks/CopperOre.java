package com.example.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ToolItem;
import net.minecraftforge.common.ToolType;

public class CopperOre extends Block{

	public CopperOre() {
		super(Properties.create(Material.ROCK)
				.sound(SoundType.STONE)
				.hardnessAndResistance(5.0f, 4.0f)
				.harvestLevel(2).harvestTool(ToolType.PICKAXE)
				); 
		
		setRegistryName("copperore");
		
	}

}
