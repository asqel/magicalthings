package com.asqool.magicalthings.block.blocks;

import javax.annotation.Nullable;

import com.asqool.magicalthings.block.entity.magical_block_entity;
import com.asqool.magicalthings.block.entity.shield_blockE;

import net.minecraft.core.BlockPos;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;


public class shield_block extends BaseEntityBlock{

    public shield_block() {
        super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.GLASS).explosionResistance(150).strength(1).noLootTable().noOcclusion());
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }


    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new shield_blockE(pos,state);
    }
    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
            BlockEntityType<T> type) {
        return createTickerHelper(type,magical_block_entity.shield_blockE.get(),shield_blockE::tick);
    }
    
}
