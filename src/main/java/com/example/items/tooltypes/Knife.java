package com.example.items.tooltypes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.list.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;

public class Knife extends ToolItem {
	
	
	
	
	static Block arr[] = {Blocks.DIRT, Blocks.PUMPKIN, Blocks.SUGAR_CANE, Blocks.MELON,
								Blocks.OAK_LOG, Blocks.DARK_OAK_LOG, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG,
								Blocks.SPRUCE_LOG, Blocks.GRASS_BLOCK};
	
	static Set<Block> kblocks = new HashSet<Block>(Arrays.asList(arr));
	
	
	
	protected Knife(IItemTier tier, int extraDurability, float speed)
	{
		super(0.2f, speed, tier, kblocks, new Properties()
				.maxDamage(extraDurability + 100)
				.group(ModItems.itemGroup));
	}
	

	


}
