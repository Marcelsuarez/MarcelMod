package com.example.items;

import com.example.list.ModItems;

import net.minecraft.item.ShearsItem;

public class DiamondShears extends ShearsItem{

	public DiamondShears() {
		super(new Properties().maxStackSize(1).maxDamage(600).group(ModItems.itemGroup));
		setRegistryName("diamondshears");
	}

}
