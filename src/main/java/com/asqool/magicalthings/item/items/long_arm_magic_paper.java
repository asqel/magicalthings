package com.asqool.magicalthings.item.items;


import com.asqool.magicalthings.effect.magical_effects;
import com.asqool.magicalthings.item.magical_items;
import com.asqool.magicalthings.variables.playervarsProvider;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class long_arm_magic_paper extends Item {
    
    public long_arm_magic_paper(){
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8));
    }
   
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
            if (caps.get("knowmagic")==2f){
                if(40<=caps.get("mana")){
                    caps.remove("mana", 40);
                    user.addEffect(new MobEffectInstance(magical_effects.long_arm.get(),3600,0,false,false));//time,level,ambient tick,particle
                    user.swing(hand);
                    user.getInventory().removeItem(new ItemStack(magical_items.long_arm_magic_paper.get()));
                }
            }
        });
        
        return super.use(world,user,hand);
    }
}
