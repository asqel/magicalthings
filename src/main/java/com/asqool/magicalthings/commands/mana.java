package com.asqool.magicalthings.commands;

import com.asqool.magicalthings.commands.suggestions.names;
import com.asqool.magicalthings.variables.playervarsProvider;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;


public class mana {
    public mana(CommandDispatcher<CommandSourceStack> dispatcher){
        register(dispatcher);
    }
    public static void register(CommandDispatcher<CommandSourceStack> d) {
        d.register(Commands.literal("magicalthings:mana").
        then(Commands.literal("set").then(Commands.argument("name", StringArgumentType.string()).suggests(names.names).then(Commands.argument("amount", FloatArgumentType.floatArg()).executes((Command<CommandSourceStack>)command->{
                    String n=StringArgumentType.getString(command, "name").toLowerCase().toString();
                    for (ServerPlayer i:command.getSource().getLevel().players()){
                        if(i.getName().toString().toLowerCase().replace("literal{", "").replace("}","").toString().equals(n.toString())){
                            i.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                                caps.set("mana",FloatArgumentType.getFloat(command, "amount"));
                                if(command.getSource().isPlayer()){
                                    String name=i.getName().toString().replace("literal{", "").replace("}","");
                                    command.getSource().sendSuccess(Component.literal(name+" has "+Float.toString((int)caps.get("mana"))+" mana"), false);
                                }
                                });
                            break;
                        }
                    }
                    return 0;
                })))).
        then(Commands.literal("get").then(Commands.argument("name", StringArgumentType.string()).suggests(names.names).executes((Command<CommandSourceStack>)command->{ 
            if(!command.getSource().isPlayer()){return 0;}
            String n=StringArgumentType.getString(command, "name").toLowerCase().toString();
            for (ServerPlayer i:command.getSource().getLevel().players()){
                if(i.getName().toString().toLowerCase().replace("literal{", "").replace("}","").toString().equals(n.toString())){
                    i.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                        String name=i.getName().toString().replace("literal{", "").replace("}","");
                        command.getSource().sendSuccess(Component.literal(name+" has "+Float.toString((int)caps.get("mana"))+" mana"), false);
                    });
                    break;
                }
            }
            return 0;
        }))).
        then(Commands.literal("add").then(Commands.argument("name", StringArgumentType.string()).suggests(names.names).then(Commands.argument("amount", FloatArgumentType.floatArg()).executes((Command<CommandSourceStack>)command->{
            String n=StringArgumentType.getString(command, "name").toLowerCase().toString();
            for (ServerPlayer i:command.getSource().getLevel().players()){
                if(i.getName().toString().toLowerCase().replace("literal{", "").replace("}","").toString().equals(n.toString())){
                    i.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                        caps.add("mana",FloatArgumentType.getFloat(command, "amount"));
                        if(command.getSource().isPlayer()){
                            String name=i.getName().toString().replace("literal{", "").replace("}","");
                            command.getSource().sendSuccess(Component.literal(name+" has "+Float.toString((int)caps.get("mana"))+" mana"), false);
                        }
                        });
                    break;
                }
            }
            return 0;
        })))).
        then(Commands.literal("remove").then(Commands.argument("name", StringArgumentType.string()).suggests(names.names).then(Commands.argument("amount", FloatArgumentType.floatArg()).executes((Command<CommandSourceStack>)command->{
            String n=StringArgumentType.getString(command, "name").toLowerCase().toString();
            for (ServerPlayer i:command.getSource().getLevel().players()){
                if(i.getName().toString().toLowerCase().replace("literal{", "").replace("}","").toString().equals(n.toString())){
                    i.getCapability(playervarsProvider.PLAYER_VARS).ifPresent(caps->{
                        caps.remove("mana",FloatArgumentType.getFloat(command, "amount"));
                        if(command.getSource().isPlayer()){
                            String name=i.getName().toString().replace("literal{", "").replace("}","");
                            command.getSource().sendSuccess(Component.literal(name+" has "+Float.toString((int)caps.get("mana"))+" mana"), false);
                        }
                        });
                    break;
                }
            }
            return 0;
        })))));
    }

}
