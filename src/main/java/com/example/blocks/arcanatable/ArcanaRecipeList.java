package com.example.blocks.arcanatable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

import com.example.list.*;


public enum ArcanaRecipeList
{
	flameWandRecipe(Items.BLAZE_ROD, Items.DIAMOND, ModItems.PRIMALESSENCE, ModItems.FLAMEWAND);
	
	private final ItemStack wandBase;
	private final ItemStack gem;
	private final ItemStack essence;
	private final ItemStack result;
	
	
	private ArcanaRecipeList(Item wandBase, Item gem, Item essence, Item result)
	{
		this.wandBase = new ItemStack(wandBase);
		this.gem = new ItemStack(gem);
		this.essence = new ItemStack(essence);
		this.result = new ItemStack(result);
	}
	
	public ItemStack getResult()
	{
		return this.result;
	}
	
	public ItemStack[] getIngredients()
	{
		return new ItemStack[] {this.wandBase, this.gem, this.essence};
	}
	
	public ItemStack getBase()
	{
		return this.wandBase;
	}
	
	public ItemStack getGem()
	{
		return this.gem;
	}
	
	
	public ItemStack getEssence()
	{
		return this.essence;
	}
	
	public static boolean matchesRecipe(ItemStack wandBase, ItemStack gem, ItemStack essence)
	{
		List<ArcanaRecipeList> recipes = Arrays.asList(ArcanaRecipeList.values());
		
		for (ArcanaRecipeList recipe : recipes)
		{
			return (wandBase.getItem() == recipe.getBase().getItem()) &&
					(gem.getItem() == recipe.getGem().getItem()) && (essence.getItem() == recipe.getEssence().getItem());
		}
		
		return false;
	}
	
	public static ArcanaRecipeList getMatchRecipe(ItemStack wandBase, ItemStack gem, ItemStack essence)
	{
		List<ArcanaRecipeList> recipes = Arrays.asList(ArcanaRecipeList.values());
		
		
		for (ArcanaRecipeList recipe : recipes)
		{
			if ((wandBase.getItem() == recipe.wandBase.getItem()) && (gem.getItem() == recipe.gem.getItem()) && (essence.getItem() == recipe.essence.getItem()))
			{
				return recipe;
			}
		}
		
		return null;
		
		
	}
	
	
	
	
	
	
}
