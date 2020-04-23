package com.example.items;

import com.example.list.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;

public class PrimalEssence extends Item {

	public PrimalEssence() {
		super(new Properties().maxStackSize(64).group(ModItems.itemGroup));
		setRegistryName("primalessence");
		
	}

}
