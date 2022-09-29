package com.asqool.magicalthings.variables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class playervarsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<playervars>PLAYER_VARS=CapabilityManager.get(new CapabilityToken<playervars>() {});

    private playervars vars=null;
    private final LazyOptional<playervars> optional=LazyOptional.of(this::createPlayervars);

    private playervars createPlayervars(){
        if(this.vars==null){
            this.vars=new playervars();
        }
        return this.vars;
    } 

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt=new CompoundTag();
        createPlayervars().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayervars().loadNBTData(nbt);
        
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap==PLAYER_VARS){
            return optional.cast();
        }
        return LazyOptional.empty();
    }
    
}
