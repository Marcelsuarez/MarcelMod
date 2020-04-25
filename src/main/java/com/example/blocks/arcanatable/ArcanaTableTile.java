package com.example.blocks.arcanatable;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.example.list.ModBlocks;
import com.example.list.ModItems;
import com.example.notworking.ArcanaRecipes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ArcanaTableTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(()->this.inventory);
	private LazyOptional<IEnergyStorage> mana = LazyOptional.of(this::createEnergy);
	
	
	
	public ArcanaTableTile() {
		super(ModBlocks.ARCANATABLE_TILE);
		
	}
	
    public final ItemStackHandler inventory = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(final int slot) {
            super.onContentsChanged(slot);
           ArcanaTableTile.this.markDirty();
        }
    };
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(CompoundNBT tag)
	{
		CompoundNBT invTag = tag.getCompound("inv");
		handler.ifPresent(h ->
		(  (INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
		mana.ifPresent( h ->
		( (ArcanaManaStorage)   h).setMana(invTag.getInt("mana")));
		super.read(tag);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		handler.ifPresent(h -> {
		CompoundNBT comp = (  (INBTSerializable<CompoundNBT>) h).serializeNBT();
		tag.put("inv", comp);
		});
		mana.ifPresent(h -> tag.putInt("mana", h.getEnergyStored()));
		
		return super.write(tag);
	}

	
	private IEnergyStorage createEnergy()
	{
		return new ArcanaManaStorage();
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return handler.cast();
		}
		if (cap == CapabilityEnergy.ENERGY)
		{
			return mana.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void tick() {
		
		handler.ifPresent( h -> {
			ItemStack fuelSlot = h.getStackInSlot(3); 
			if (fuelSlot.getItem() == ModItems.PRIMALESSENCE && this.getMana() < this.getMax())  
			{
				h.extractItem(3, 1, false);
				mana.ifPresent(e -> ((ArcanaManaStorage) e).addMana(100));	
			}
				});
		
		handler.ifPresent( h -> {
        ItemStack wandBase = h.getStackInSlot(0);
        ItemStack gem = h.getStackInSlot(1);
        ItemStack essence = h.getStackInSlot(2);
        ItemStack result = h.getStackInSlot(4);

        
      
        
        if (result.isEmpty() && ArcanaRecipeList.matchesRecipe(wandBase, gem, essence) && this.getMana() >= 100)
        {
        	ArcanaRecipeList recipe = ArcanaRecipeList.getMatchRecipe(wandBase, gem, essence);
        	final boolean insertOutput = h.insertItem(4, recipe.getResult(), true).isEmpty();
        	ItemStack wand = new ItemStack(recipe.getResult().getItem()); //Apparently this needs to be here or it doesnt work for some reason, only god knows why
        	
        	if(insertOutput)
        	{
        	
        		h.insertItem(4, wand, false); //putting in the item
        		h.extractItem(0, 1, false); //instantly pulls out items because im too lazy to figure it out the traditional way
        		h.extractItem(1, 1, false);
        		h.extractItem(2, 1, false);
        		this.substractMana(100);
        	}
        	
        }

        
        
		});
		
	}
	
	 private int getMana() {
	        return this.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
	    }
	
	 private int getMax() {
	        return this.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
	    }
	 
	 
	 public void substractMana(int value)
	 {
		this.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((ArcanaManaStorage)h).setMana(this.getMana() - value));
	 }

	@Override
	public Container createMenu(int menu, PlayerInventory inv, PlayerEntity entity) {
		
		return new ArcanaTableContainer(menu, pos, world, inv, entity);
	}

	@Override
	public ITextComponent getDisplayName() {
		
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	

	
	

}
