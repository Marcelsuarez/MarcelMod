package com.example.items;


import com.example.items.tooltypes.Knife;
import com.example.list.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ToolItem;

public class CopperKnife extends Knife
{
    public CopperKnife()
    {
		super(ToolMaterialList.copper, 30, 2.0f);
		setRegistryName("copperknife");
	}

}











