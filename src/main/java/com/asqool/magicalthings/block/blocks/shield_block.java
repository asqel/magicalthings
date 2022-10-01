package com.asqool.magicalthings.block.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class shield_block extends Block{

    public shield_block() {
        super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS).explosionResistance(150).strength(1).noLootTable().noOcclusion());
    }
    
    
}
