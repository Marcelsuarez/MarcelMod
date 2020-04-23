package com.example.notworking;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.inventory.IInventory;

import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ArcanaRecipes implements IRecipe<IInventory> {

	 public static final Serializer SERIALIZER = new Serializer();
	 public static final ResourceLocation RL = new ResourceLocation("examplemod", "arcana_crafting");
    public static final IRecipeType<ArcanaRecipes> RECIPE_TYPE = new IRecipeType<ArcanaRecipes>() { };
	
    
    protected Ingredient wandBase, essence, gem;
    protected final ResourceLocation id;
    protected ItemStack result;
    
    
    public ArcanaRecipes(ResourceLocation idIn) {
        this.id = idIn;
    }
    
	@Override
	public boolean matches(IInventory inv, World worldIn) { //wand 0, essence 1, gem 2
		
		return this.wandBase.test(inv.getStackInSlot(0)) && this.essence.test(inv.getStackInSlot(1)) && this.gem.test(inv.getStackInSlot(2));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return this.result.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {

		return this.result;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	
	@Override
	public IRecipeSerializer getSerializer() {
		return SERIALIZER;
	}

	
	@Override
	public IRecipeType getType() {

		return RECIPE_TYPE;
	}
	
	
	
    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ArcanaRecipes> {

        @Override
        public ArcanaRecipes read(ResourceLocation recipeId, JsonObject json) {
        	ArcanaRecipes recipe = new ArcanaRecipes(recipeId);
        	System.out.println("Wand String:" + JSONUtils.getJsonObject(json, "wandbase").getAsString());
        	System.out.println("Gem String:" + JSONUtils.getJsonObject(json, "gem").getAsString());
        	System.out.println("Essence String:" + JSONUtils.getJsonObject(json, "essence").getAsString());
        	
        	
        	
        	
            recipe.result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            recipe.wandBase = Ingredient.deserialize(JSONUtils.getJsonObject(json, "wandbase"));
            recipe.essence = Ingredient.deserialize(JSONUtils.getJsonObject(json, "essence"));
            recipe.gem = Ingredient.deserialize(JSONUtils.getJsonObject(json, "gem"));
            
            return recipe;
        }

        @Nullable
        @Override
        public ArcanaRecipes read(ResourceLocation recipeId, PacketBuffer buffer) {
            ArcanaRecipes recipe = new ArcanaRecipes(recipeId);
            recipe.result = buffer.readItemStack();
            recipe.wandBase = Ingredient.read(buffer);
            recipe.essence = Ingredient.read(buffer);
            recipe.gem = Ingredient.read(buffer);
            return recipe;
        }

        @Override
        public void write(PacketBuffer buffer, ArcanaRecipes recipe) {
            buffer.writeItemStack(recipe.result);
            recipe.wandBase.write(buffer);
            recipe.essence.write(buffer);
            recipe.gem.write(buffer);
        }


    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
