package net.itemstorm.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.itemstorm.ItemStorm;
import net.minecraft.core.Holder;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.jetbrains.annotations.NotNull;

public class ChaosChunkGenerator extends NoiseBasedChunkGenerator {
    public static final MapCodec<ChaosChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
                            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(NoiseBasedChunkGenerator::generatorSettings)
                    )
                    .apply(instance, instance.stable(ChaosChunkGenerator::new))
    );

    public ChaosChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
        super(biomeSource, settings);
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel level, ChunkAccess chunk, StructureManager structureManager) {
        ItemStorm.CHAOSGEN.set(true);
        super.applyBiomeDecoration(level, chunk, structureManager);
        ItemStorm.CHAOSGEN.set(false);
    }

    @NotNull
    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @NotNull
    @Override
    public ChunkAccess doFill(Blender blender, StructureManager structureManager, RandomState random, ChunkAccess chunk, int minCellY, int cellCountY) {
        ItemStorm.CHAOSGEN.set(true);
        ChunkAccess chunkAccess = super.doFill(blender, structureManager, random, chunk, minCellY, cellCountY);
        ItemStorm.CHAOSGEN.set(false);
        return chunkAccess;
    }
}
