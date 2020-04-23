package com.example.items;

import com.example.items.tooltypes.Knife;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;

public class DiamondKnife extends Knife {

	public DiamondKnife() {
		super(ItemTier.DIAMOND, 200, 0.4f);
		setRegistryName("diamondknife");
	}

}
