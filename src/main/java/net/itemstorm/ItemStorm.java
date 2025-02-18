package net.itemstorm;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.itemstorm.command.ItemStormCommand;

public class ItemStorm implements ModInitializer {
    public static final String MOD_ID = "itemstorm";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandBuildContext, commandSelection) -> {
            ItemStormCommand.register(commandDispatcher);

        });
    }
}
