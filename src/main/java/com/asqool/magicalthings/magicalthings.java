package com.asqool.magicalthings;

import com.asqool.magicalthings.effect.magical_effects;
import com.asqool.magicalthings.item.magical_items;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(magicalthings.modid)
public class magicalthings
{
    public static final String modid = "magicalthings";
    public static final String long_arm_uuid="d71e0cde-84eb-4dc3-b941-47be1935a1c0";
    public static final String name_hide_uuid="";
    public magicalthings()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        magical_items.register(modEventBus);
        magical_effects.register(modEventBus);
        
        modEventBus.addListener(this::commonSetup);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = modid, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
