package net.itemstorm.client.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.itemstorm.client.data.tags.ModBlockTagProvider;
import net.itemstorm.client.data.tags.ModItemTagProvider;

public class ItemStormDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(ModDynamicRegistryProvider::new);
        ModBlockTagProvider blockTagProvider = pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider((output, lookup) -> new ModItemTagProvider(output, lookup, blockTagProvider));
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModLanguageProvider::new);
    }
}
