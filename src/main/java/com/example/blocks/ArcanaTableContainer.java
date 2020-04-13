package com.example.blocks;

import com.example.list.ModBlocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ArcanaTableContainer extends Container {
	
	private TileEntity tileEntity;
	private PlayerEntity player;
	private IItemHandler playerInventory;
	private IInventory arcanaInventory;
	

	public ArcanaTableContainer(int id, BlockPos pos, World world, PlayerInventory inv, PlayerEntity player)
	{
		super(ModBlocks.ARCANATABLE_CONTAINER, id);
		tileEntity = world.getTileEntity(pos);
		tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
		this.player = player;
		this.playerInventory = new InvWrapper(inv);
		this.arcanaInventory = new Inventory(3);		

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 56, 17));
            addSlot(new SlotItemHandler(h, 1, 32, 17));
            addSlot(new SlotItemHandler(h, 2, 44, 37));
        });
        
		/*
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
               this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
         }

         for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
         }
         */
         
         layoutPlayerInventorySlots(8, 84);
		
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), player, ModBlocks.ARCANATABLE );
	}


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx)
    {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
    {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
           ItemStack itemstack1 = slot.getStack();
           itemstack = itemstack1.copy();
           if (index == 2) {
              if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                 return ItemStack.EMPTY;
              }

              slot.onSlotChange(itemstack1, itemstack);
           } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
              return ItemStack.EMPTY;
           }

           if (itemstack1.isEmpty()) {
              slot.putStack(ItemStack.EMPTY);
           } else {
              slot.onSlotChanged();
           }

           if (itemstack1.getCount() == itemstack.getCount()) {
              return ItemStack.EMPTY;
           }

           slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
     }
    
    
    
    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);
        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 3, leftCol, topRow, 9, 18);
    }
    
    
    
    
    
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


