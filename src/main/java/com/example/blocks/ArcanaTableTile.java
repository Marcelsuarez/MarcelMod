package com.example.blocks;

import javax.annotation.Nonnull;

import com.example.list.ModBlocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ArcanaTableTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	
	private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
	
	
	
	public ArcanaTableTile() {
		super(ModBlocks.ARCANATABLE_TILE);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void read(CompoundNBT tag)
	{
		CompoundNBT invTag = tag.getCompound("inv");
		handler.ifPresent(h ->
		(  (INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
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
		return super.write(tag);
	}

	
	private ItemStackHandler createHandler()
	{
		return new ItemStackHandler(3);
	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return handler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void tick() {
		
		
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
