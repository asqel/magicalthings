package com.asqool.magicalthings.commands.suggestions;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.commands.CommandSourceStack;

public class names {
    public static final SuggestionProvider<CommandSourceStack> names=new SuggestionProvider<CommandSourceStack>() {
        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context,
                SuggestionsBuilder builder) throws CommandSyntaxException {
                    for (String i:context.getSource().getServer().getPlayerNames()){
                        builder.suggest(i);
                    }
                    return builder.buildFuture();
                }};
}
