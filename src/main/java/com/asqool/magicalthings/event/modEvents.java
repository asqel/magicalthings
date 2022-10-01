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
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
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
            if(event.player.hasEffect(magical_effects.long_arm.get())){
                if (event.player.getAttributes().hasModifier(ForgeMod.REACH_DISTANCE.get(), magicalthings.long_arm_uuid)){
                    if (event.player.getAttributes().getModifierValue(ForgeMod.REACH_DISTANCE.get(), magicalthings.long_arm_uuid)!=10){
                        execCommand("/attribute @s forge:reach_distance modifier remove d71e0cde-84eb-4dc3-b941-47be1935a1c0".replace("@s", event.player.getName().getString()), event.player.getLevel());
                        execCommand("/attribute @s forge:reach_distance modifier add d71e0cde-84eb-4dc3-b941-47be1935a1c0 long_arm 10 add".replace("@s", event.player.getName().getString()), event.player.getLevel());}}
                else{
                    execCommand("/attribute @s forge:reach_distance modifier add d71e0cde-84eb-4dc3-b941-47be1935a1c0 long_arm 10 add".replace("@s", event.player.getName().getString()), event.player.getLevel());}}
            else{
                if (event.player.getAttributes().hasModifier(ForgeMod.REACH_DISTANCE.get(), magicalthings.long_arm_uuid)){
                        execCommand("/attribute @s forge:reach_distance modifier remove d71e0cde-84eb-4dc3-b941-47be1935a1c0".replace("@s", event.player.getName().getString()), event.player.getLevel());}}

            if(event.player.hasEffect(magical_effects.name_hide.get())){
                if (event.player.getAttributes().hasModifier(ForgeMod.NAMETAG_DISTANCE.get(), magicalthings.name_hide_uuid)){
                    if (event.player.getAttributes().getModifierValue(ForgeMod.NAMETAG_DISTANCE.get(), magicalthings.name_hide_uuid)!=0){
                        execCommand("/attribute @s forge:nametag_distance modifier remove @@".replace("@s", event.player.getName().getString()).replace("@@",magicalthings.name_hide_uuid.toString()), event.player.getLevel());
                        execCommand("/attribute @s forge:nametag_distance modifier add @@ name_hide 0 multiply".replace("@s", event.player.getName().getString()).replace("@@",magicalthings.name_hide_uuid.toString()), event.player.getLevel());}}
                else{
                    execCommand("/attribute @s forge:nametag_distance modifier add @@ name_hide 0 multiply".replace("@s", event.player.getName().getString()).replace("@@",magicalthings.name_hide_uuid.toString()), event.player.getLevel());}}
            else{
                if (event.player.getAttributes().hasModifier(ForgeMod.NAMETAG_DISTANCE.get(), magicalthings.name_hide_uuid)){
                    execCommand("/attribute @s forge:nametag_distance modifier remove @@".replace("@s", event.player.getName().getString()).replace("@@",magicalthings.name_hide_uuid.toString()), event.player.getLevel());}}
        }           
    }
}
