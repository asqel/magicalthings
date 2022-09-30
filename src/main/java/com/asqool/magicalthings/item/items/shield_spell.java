package com.asqool.magicalthings.item.items;

import com.asqool.magicalthings.event.modEvents;
import com.asqool.magicalthings.variables.playervarsProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class shield_spell extends Item{

    public shield_spell(){
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        user.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
            if(caps.get("knowmagic")==2f&caps.get("mana")>=50){
                double x=user.getX();
                double y=user.getY();
                double z=user.getZ();
                float xr=user.xRotO;
                float yr=user.yRotO;
                System.out.println(yr);
                if(user.isCrouching()){
                    if (xr>0){
                        modEvents.execCommand("execute at @@ as @@ run fill ~1 ~-1 ~1 ~-1 ~-1 ~-1 obsidian keep".replace("@@",user.getName().getString()), world);
                    }
                    else{
                        System.out.println(user.getName().toString());
                        modEvents.execCommand("execute at @@ as @@ run fill ~1 ~2 ~1 ~-1 ~2 ~-1 obsidian keep".replace("@@",user.getName().getString()), world);
                    }
                }

            }
        });
        return super.use(world, user, hand);
    }
}
