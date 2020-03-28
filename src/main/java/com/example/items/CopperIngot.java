package com.example.items;

import com.example.list.ModItems;

import net.minecraft.item.Item;

public class CopperIngot extends Item {

	public CopperIngot() {
		super(new Item.Properties().maxStackSize(64).group(ModItems.itemGroup));
		setRegistryName("copperingot");
		
	}

}
