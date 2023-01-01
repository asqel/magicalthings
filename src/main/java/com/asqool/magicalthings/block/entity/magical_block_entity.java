package com.asqool.magicalthings.block.entity;

import com.asqool.magicalthings.magicalthings;
import com.asqool.magicalthings.block.magical_blocks;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class magical_block_entity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITES=
        DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, magicalthings.modid);

    public static final RegistryObject<BlockEntityType<shield_blockE>>shield_blockE=
        BLOCK_ENTITES.register("shield_block", ()->BlockEntityType.Builder.of(shield_blockE::new,
                                                magical_blocks.shield_block.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITES.register(eventBus);
    }
   
    
}
