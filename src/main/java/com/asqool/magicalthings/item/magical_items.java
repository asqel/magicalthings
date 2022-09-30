package com.asqool.magicalthings.item;

import com.asqool.magicalthings.magicalthings;
import com.asqool.magicalthings.item.items.long_arm_magic_paper;
import com.asqool.magicalthings.item.items.blaze_totem;
import com.asqool.magicalthings.item.items.shield_spell;


import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class magical_items {
    public static final DeferredRegister<Item>items=DeferredRegister.create(ForgeRegistries.ITEMS,magicalthings.modid);

    public static final RegistryObject<Item> long_arm_magic_paper=items.register("long_arm_magic_paper",()->new long_arm_magic_paper());
    public static final RegistryObject<Item> blaze_totem=items.register("blaze_totem", ()->new blaze_totem());
    public static final RegistryObject<Item> shield_spell=items.register("shield_spell",()->new shield_spell());
    public static void register(IEventBus eventBus){
        items.register(eventBus);
    }
}

