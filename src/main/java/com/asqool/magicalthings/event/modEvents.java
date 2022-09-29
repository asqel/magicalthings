package com.asqool.magicalthings.event;


import com.asqool.magicalthings.magicalthings;
import com.asqool.magicalthings.effect.magical_effects;
import com.asqool.magicalthings.variables.playervars;
import com.asqool.magicalthings.variables.playervarsProvider;
import com.asqool.magicalthings.commands.knowmagic;
import com.asqool.magicalthings.commands.mana;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid=magicalthings.modid)
public class modEvents {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new mana(event.getDispatcher());
        new knowmagic(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }



    public static void execCommand(String com,Level level){
        level.getServer().getCommands().performPrefixedCommand(level.getServer().createCommandSourceStack(), com);
    }
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(playervarsProvider.PLAYER_VARS).isPresent()){
                event.addCapability(new ResourceLocation(magicalthings.modid,"properties"),new playervarsProvider());
            }
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(playervars.class);
    }



    @SubscribeEvent
    public static void Clone(PlayerEvent.Clone event){
        if (event.isWasDeath()){
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(playervarsProvider.PLAYER_VARS).ifPresent(oldStore -> {
                event.getEntity().getCapability(playervarsProvider.PLAYER_VARS).ifPresent(newStore -> {
                   newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().invalidateCaps();
        } 
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if (event.side==LogicalSide.SERVER){
            event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                if(caps.get("mana")<100){
                    caps.add("mana",0.2f);
                }
            });
            if(event.player.isCrouching()){
                event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                    float a=caps.get("mana");
                    event.player.sendSystemMessage(Component.literal(Float.toString(a)));
                });
            }
            if(event.player.hasEffect(magical_effects.long_arm.get())){
                execCommand("attribute @ forge:reach_distance base set 15".replace("@",event.player.getName().getString()), event.player.level);
                event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                    caps.set("long_arm_or_not",1f);
                });
            }
            else{
                event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                    if(caps.get("long_arm_or_not")==1f){
                        execCommand("attribute @ forge:reach_distance base set 5".replace("@",event.player.getName().getString()), event.player.level);
                        caps.set("long_arm_or_not",0f);
                    }
                });
            }

            if(event.player.hasEffect(magical_effects.name_hide.get())){
                execCommand("attribute @ forge:nametag_distance base set 0".replace("@",event.player.getName().getString()), event.player.level);
                event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                    caps.set("namehideornot",1f);
                });
            }
            else{
                event.player.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                    if(caps.get("namehideornot")==1f){
                        execCommand("attribute @ forge:nametag_distance base set 64".replace("@",event.player.getName().getString()), event.player.level);
                        caps.set("namehideornot",0f);
                    }
                });
            }
        }
    }
}
