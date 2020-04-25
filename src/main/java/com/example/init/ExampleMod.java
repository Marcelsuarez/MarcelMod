package com.example.init;
import com.example.blocks.*;
import com.example.blocks.arcanatable.ArcanaTable;
import com.example.blocks.arcanatable.ArcanaTableContainer;
import com.example.blocks.arcanatable.ArcanaTableTile;
import com.example.entities.FWFireball;
import com.example.items.*;
import com.example.items.tooltypes.Knife;
import com.example.list.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("examplemod")
public class ExampleMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public ExampleMod() {
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MyEventHandler());
        
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init();  //important
    }

    
    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
   
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }


    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)  {
            event.getRegistry().register(new CopperOre());
            event.getRegistry().register(new CompressedCobble());
            event.getRegistry().register(new ArcanaTable());
            event.getRegistry().register(new CopperBlock());
            event.getRegistry().register(new ConjuredMelon());
        }
         @SubscribeEvent
         public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        	 //Item.Properties properties = new Item.Properties().group(ModItems.itemGroup);
              event.getRegistry().register(new BlockItem(ModBlocks.COPPERORE, new Item.Properties().group(ModItems.itemGroup))
              .setRegistryName("copperore"));
              event.getRegistry().register(new BlockItem(ModBlocks.COMPRESSEDCOBBLE, new Item.Properties().group(ModItems.itemGroup))
              .setRegistryName("compressedcobble"));
              event.getRegistry().register(new BlockItem(ModBlocks.ARCANATABLE, new Item.Properties().group(ModItems.itemGroup))
              .setRegistryName("arcanatable"));
              event.getRegistry().register(new BlockItem(ModBlocks.COPPERBLOCK, new Item.Properties().group(ModItems.itemGroup))
              .setRegistryName("copperblock"));
              event.getRegistry().register(new BlockItem(ModBlocks.CONJUREDMELON, new Item.Properties().group(ModItems.itemGroup))
              .setRegistryName("conjuredmelon"));
              event.getRegistry().register(new CopperIngot());
              event.getRegistry().register(new CopperKnife());
              event.getRegistry().register(new Fiber());
              event.getRegistry().register(new DiamondKnife());
              event.getRegistry().register(new DiamondShears());
              event.getRegistry().register(new PrimalEssence());
              event.getRegistry().register(new FlameWand());
              event.getRegistry().register(new MelonWand());
                         
        }
         @SubscribeEvent
         public static void onEnchantmentsRegistry(final RegistryEvent.Register<Enchantment> event)
         {
    		
    		
         }
         
         @SubscribeEvent
         public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event)
         {
        	 event.getRegistry().register(TileEntityType.Builder.create(ArcanaTableTile::new, ModBlocks.ARCANATABLE).build(null).setRegistryName("arcanatable"));
         }
         
         @SubscribeEvent
         public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event)
         {
        	 event.getRegistry().register(IForgeContainerType.create( (windowID, inv, data) ->
        	 {
        		 BlockPos pos = data.readBlockPos();
        		 return new ArcanaTableContainer(windowID, pos, proxy.getClientWorld(), inv, proxy.getClientPlayer());
        	 }).setRegistryName("arcanatable"));    	 
         }

         public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event)
         {
        	
        	            
         }

         
         
    }
}
