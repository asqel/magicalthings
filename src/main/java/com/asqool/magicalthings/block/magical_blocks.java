package com.asqool.magicalthings.block;

import com.asqool.magicalthings.magicalthings;
import com.asqool.magicalthings.block.blocks.shield_block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class magical_blocks {
    public static final DeferredRegister<Block>blocks=DeferredRegister.create(ForgeRegistries.BLOCKS,magicalthings.modid);
    public static final RegistryObject<Block> shield_block=blocks.register("shield_block",()->new shield_block());
    public static void register(IEventBus eventBus){
        blocks.register(eventBus);
    }
}
