package net.itemstorm.worldgen;

import com.mojang.serialization.MapCodec;
import net.itemstorm.ItemStorm;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class ModChunkGenerators {
    public static MapCodec<? extends ChunkGenerator> NBT = register("nbt", NbtChunkGenerator.CODEC);

    public static MapCodec<? extends ChunkGenerator> register(String name, MapCodec<? extends ChunkGenerator> codec) {
        return Registry.register(BuiltInRegistries.CHUNK_GENERATOR, ResourceLocation.fromNamespaceAndPath(ItemStorm.MOD_ID, name), codec);
    }

    public static void init() {
    }
}