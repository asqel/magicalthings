package com.asqool.magicalthings.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class shield_blockE extends BlockEntity {
    public shield_blockE( BlockPos pos, BlockState state) {

        super(magical_block_entity.shield_blockE.get(), pos,state);
    }
    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("life", life);
    }
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.life = nbt.getInt("life");
    }
    
    
    public int life=1600;
    public static <E extends BlockEntity>void tick(Level world,BlockPos pos,BlockState state,shield_blockE pEntity){
        if(world.isClientSide()){
            return;
        }
        shield_blockE b=((shield_blockE)world.getBlockEntity(pos));
        b.life-=1;
        if (b.life<=0){
            world.removeBlock(pos,false);
            world.removeBlockEntity(pos);
        }
        //CompoundTag nbt=new CompoundTag();
        //pEntity.saveAdditional(nbt);
        //if(!nbt.contains("life")){
        //    nbt.putInt("life", 16000);
        //}
        //nbt.putInt("life", nbt.getInt("life")-1);
        //if(nbt.getInt("life")<=0){
        //    world.removeBlockEntity(pos);
        //}
        //pEntity.load(nbt);

        
    }
    
}
