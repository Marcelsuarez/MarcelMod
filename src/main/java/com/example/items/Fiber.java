package com.example.items;

import com.example.list.ModItems;

import net.minecraft.item.Item;

public class Fiber extends Item {

	public Fiber() {
		super(new Properties().maxStackSize(64).group(ModItems.itemGroup));
		setRegistryName("fiber");
	}

}
