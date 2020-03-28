package com.example.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CompressedCobble extends Block {

	public CompressedCobble() {
		super(Properties.create(Material.ROCK)
				.sound(SoundType.STONE)
				.hardnessAndResistance(3.0f, 4.0f)
				.harvestLevel(2)
				);
			setRegistryName("compressedcobble");
	}

}
