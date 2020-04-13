package com.example.init;

import com.example.blocks.ArcanaTableScreen;
import com.example.list.ModBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

	
	@Override
	public void init()
	{
		ScreenManager.registerFactory(ModBlocks.ARCANATABLE_CONTAINER, ArcanaTableScreen::new);
	}
	
	
	
    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
