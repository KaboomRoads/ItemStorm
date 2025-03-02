package net.itemstorm.command;

import com.mojang.brigadier.CommandDispatcher;
import net.itemstorm.mixinimpl.ModPrimaryLevelData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.PacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import org.jetbrains.annotations.NotNull;

public class ItemStormCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("itemstorm")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("start")
                        .executes(context -> {
                            context.getSource().sendSuccess(() -> Component.translatable("commands.itemstorm.start"), true);
                            ((ModPrimaryLevelData) context.getSource().getLevel().getLevelData()).itemStorm$setItemStorm(true);
                            return 1;
                        }))
                .then(Commands.literal("stop")
                        .executes(context -> {
                            context.getSource().sendSuccess(() -> Component.translatable("commands.itemstorm.stop"), true);
                            ((ModPrimaryLevelData) context.getSource().getLevel().getLevelData()).itemStorm$setItemStorm(false);
                            return 1;
                        }))
                .then(Commands.literal("config")
                        .executes(context -> {
                            context.getSource().getPlayer().connection.send(new Packet<>() {
                                @NotNull
                                @Override
                                public PacketType<? extends Packet<PacketListener>> type() {
                                    return null;
                                }

                                @Override
                                public void handle(PacketListener handler) {
                                }
                            });
                            return 1;
                        }))
        );
    }
}
