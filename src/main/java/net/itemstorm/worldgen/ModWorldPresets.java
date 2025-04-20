package net.itemstorm.worldgen;

import net.itemstorm.ItemStorm;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

import java.util.Map;

public class ModWorldPresets {
    public static final ResourceKey<WorldPreset> SKYBLOCK = register("skyblock");

    private static ResourceKey<WorldPreset> register(String name) {
        return ResourceKey.create(Registries.WORLD_PRESET, ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, name));
    }

    public static void bootstrap(BootstrapContext<WorldPreset> context) {
        new Bootstrap(context).bootstrap();
    }

    private static class Bootstrap {
        private final BootstrapContext<WorldPreset> context;
        private final HolderGetter<Biome> biomes;
        private final Holder<DimensionType> overworldDimensionType;
        private final HolderGetter<NoiseGeneratorSettings> noiseSettings;
        private final HolderGetter<MultiNoiseBiomeSourceParameterList> multiNoiseBiomeSourceParameterLists;
        private final LevelStem netherStem;
        private final LevelStem endStem;

        public Bootstrap(BootstrapContext<WorldPreset> context) {
            this.context = context;
            HolderGetter<DimensionType> holderGetter = context.lookup(Registries.DIMENSION_TYPE);
            this.noiseSettings = context.lookup(Registries.NOISE_SETTINGS);
            this.biomes = context.lookup(Registries.BIOME);
            this.multiNoiseBiomeSourceParameterLists = context.lookup(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST);
            this.overworldDimensionType = holderGetter.getOrThrow(BuiltinDimensionTypes.OVERWORLD);
            Holder<DimensionType> holder = holderGetter.getOrThrow(BuiltinDimensionTypes.NETHER);
            Holder<NoiseGeneratorSettings> holder2 = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.NETHER);
            Holder.Reference<MultiNoiseBiomeSourceParameterList> reference = this.multiNoiseBiomeSourceParameterLists.getOrThrow(MultiNoiseBiomeSourceParameterLists.NETHER);
            this.netherStem = new LevelStem(holder, new NoiseBasedChunkGenerator(MultiNoiseBiomeSource.createFromPreset(reference), holder2));
            Holder<DimensionType> holder3 = holderGetter.getOrThrow(BuiltinDimensionTypes.END);
            Holder<NoiseGeneratorSettings> holder4 = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.END);
            this.endStem = new LevelStem(holder3, new NoiseBasedChunkGenerator(TheEndBiomeSource.create(this.biomes), holder4));
        }

        private LevelStem makeOverworld(ChunkGenerator generator) {
            return new LevelStem(this.overworldDimensionType, generator);
        }

        private WorldPreset createPresetWithCustomOverworld(LevelStem overworldStem) {
            return new WorldPreset(Map.of(LevelStem.OVERWORLD, overworldStem, LevelStem.NETHER, this.netherStem, LevelStem.END, this.endStem));
        }

        private void registerCustomOverworldPreset(ResourceKey<WorldPreset> dimensionKey, LevelStem levelStem) {
            this.context.register(dimensionKey, this.createPresetWithCustomOverworld(levelStem));
        }

        public void bootstrap() {
            Holder.Reference<Biome> plains = biomes.getOrThrow(Biomes.PLAINS);
            registerCustomOverworldPreset(SKYBLOCK, makeOverworld(new NbtChunkGenerator(plains, ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, "skyblock"))));
        }
    }
}
