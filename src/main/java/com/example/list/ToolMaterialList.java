package com.example.list;

import net.minecraft.item.IItemTier;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public enum ToolMaterialList implements IItemTier
{
	copper(0.8f, 2.2f, 130, 2, 12, "copperingot", false);
	
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantibility;
	private String repairMaterial;
	private boolean vanilla; //is the repair material from vanilla minecraft?
	
	
	private ToolMaterialList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantibility, String repairMaterial, boolean vanilla)
	{
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantibility = enchantibility;
		this.repairMaterial = repairMaterial;
		this.vanilla = vanilla;
	}

	
	
	@Override
	public int getMaxUses() {
		
		return this.durability;
	}

	@Override
	public float getEfficiency() {
		
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		
		return this.enchantibility;
	}

	@Override
	public Ingredient getRepairMaterial()
	{
		String location = this.vanilla ? "minecraft" : "examplemod";
		Item mat = ForgeRegistries.ITEMS.getValue(new ResourceLocation(location, this.repairMaterial));
		return Ingredient.fromItems(mat);
	}


	
}
