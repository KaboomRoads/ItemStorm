package net.itemstorm.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ItemStormCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("itemstorm")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("start"))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.translatable("commands.itemstorm.start"), true);

                    return 1;
                })
                .then(Commands.literal("stop"))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.translatable("commands.itemstorm.stop"), true);

                    return 1;
                })
                .then(Commands.literal("config"))
                .executes(context -> {
                    context.getSource().sendSuccess(() -> Component.translatable("commands.itemstorm.config"), true);

                    return 1;
                })
        );
    }
}
