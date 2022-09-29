package com.asqool.magicalthings.effect.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class long_arm extends MobEffect {
    public long_arm(){
        super(MobEffectCategory.BENEFICIAL,9613278);
    }


    @Override
    public void applyEffectTick(LivingEntity user, int amplifier) {
        super.applyEffectTick(user, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return true;
    }
}
