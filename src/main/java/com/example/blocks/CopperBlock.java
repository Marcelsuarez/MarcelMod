package com.example.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;

public class CopperBlock extends Block {

	public CopperBlock() {
		super(Properties.create(Material.IRON)
				.sound(SoundType.METAL)
				.hardnessAndResistance(4.0f, 4.0f)
				.harvestLevel(2));
		setRegistryName("copperblock");
		
	}

}
