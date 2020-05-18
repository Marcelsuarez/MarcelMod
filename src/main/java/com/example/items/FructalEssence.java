package com.example.items;

import com.example.list.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;

public class FructalEssence extends Item {

	
	public FructalEssence() {
		super(new Properties().maxStackSize(64).group(ModItems.itemGroup));
		setRegistryName("fructalessence");
		
	}
}
