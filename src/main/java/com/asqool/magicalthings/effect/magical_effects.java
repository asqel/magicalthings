package com.asqool.magicalthings.effect;


import com.asqool.magicalthings.magicalthings;
import com.asqool.magicalthings.effect.effects.long_arm;
import com.asqool.magicalthings.effect.effects.name_hide;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class magical_effects {
    public static final DeferredRegister<MobEffect>mob_effects=
    DeferredRegister.create(ForgeRegistries.MOB_EFFECTS,magicalthings.modid);

    public static final RegistryObject<MobEffect> long_arm=mob_effects.register("long_arm",
     ()->new long_arm());
    public static final RegistryObject<MobEffect>name_hide=mob_effects.register("name_hide",
    ()->new name_hide()); 




    public static void register(IEventBus eventbus){
        mob_effects.register(eventbus);
    }
}
