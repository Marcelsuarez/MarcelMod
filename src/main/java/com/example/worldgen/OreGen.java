package com.example.worldgen;

import com.example.list.ModBlocks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.gen.GenerationStage;

public class OreGen
{

	 private static final CountRangeConfig copper_cfg = new CountRangeConfig(5, 20, 0, 75);
	 private static final int copper_veinsize = 5;

		public static void setupOreGen()
	    {
	    	for (Biome biome: ForgeRegistries.BIOMES.getValues())
	        {
	           
	            if (biome.getCategory() == Biome.Category.THEEND || biome.getCategory() == Biome.Category.NETHER)
	            {
	                continue;
	            }
	            
	          
	            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature
	            		(Feature.ORE,
	                     new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
	                    ModBlocks.COPPERORE.getDefaultState(),
	                    copper_veinsize),
	                    Placement.COUNT_RANGE,
	                    copper_cfg));
			} 
		} 
	
	
	
	
	
	
	
}
