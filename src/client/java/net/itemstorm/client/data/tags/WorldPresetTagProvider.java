package net.itemstorm.client.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.itemstorm.worldgen.ModWorldPresets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.WorldPresetTags;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

import java.util.concurrent.CompletableFuture;

public class WorldPresetTagProvider extends FabricTagProvider<WorldPreset> {
    public WorldPresetTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.WORLD_PRESET, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(WorldPresetTags.NORMAL)
                .add(ModWorldPresets.SKYBLOCK)
                .add(ModWorldPresets.SKYBLOCK_DUO)
                .add(ModWorldPresets.SKYWARS)
                .add(ModWorldPresets.CHAOS)
        ;
    }
}
