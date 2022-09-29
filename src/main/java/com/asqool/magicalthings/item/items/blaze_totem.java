package com.asqool.magicalthings.item.items;

import com.asqool.magicalthings.item.magical_items;
import com.asqool.magicalthings.variables.playervarsProvider;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class blaze_totem extends Item{

    public blaze_totem() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).fireResistant().stacksTo(8));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        
        
        if (!world.isClientSide()){
        
        
            user.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                if(caps.get("knowmagic")==2){
                    if (caps.get("mana")>=30){
                        caps.remove("mana", 30);
                        user.swing(hand);
                        user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,3000,0,false,false));//time,level,ambient tick,particle
                        user.getInventory().removeItem(new ItemStack(magical_items.blaze_totem.get()));
                        Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(magical_items.blaze_totem.get()));
                    }
                }
            });
        }
        return super.use(world,user,hand);
    }
    
}
