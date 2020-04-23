package com.example.blocks.arcanatable;

import net.minecraftforge.energy.EnergyStorage;

public class ArcanaManaStorage extends EnergyStorage {

	public ArcanaManaStorage()
	{
		super(1000, 0);
	}

	public void setMana(int amount)
	{
		this.energy = amount;
	}
	
	public void addMana(int amount)
	{
		this.energy += amount;
		if(this.energy > getMaxEnergyStored())
		{
			this.energy = getMaxEnergyStored();
		}
	}
	
	
	
	
	
}
