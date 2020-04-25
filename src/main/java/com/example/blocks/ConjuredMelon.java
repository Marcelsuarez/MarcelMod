package com.example.blocks;

import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;

public class ConjuredMelon extends FallingBlock{

	public ConjuredMelon() {
		super(Properties.create(Material.GOURD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.0f, 1.0f)
				.harvestLevel(2));
		setRegistryName("conjuredmelon");
		
	}

}
