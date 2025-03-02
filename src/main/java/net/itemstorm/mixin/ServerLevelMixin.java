package net.itemstorm.mixin;

import net.itemstorm.mixinimpl.ModPrimaryLevelData;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BooleanSupplier;


@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {
    @Shadow
    public abstract @NotNull List<ServerPlayer> players();

    protected ServerLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess, Holder<DimensionType> dimensionTypeRegistration, boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickItemStorm(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
        if (((ModPrimaryLevelData) getLevelData()).itemStorm$getItemStorm())
            if (getGameTime() % 200 == 0) {
                for (ServerPlayer player : players()) {
                    Item item = BuiltInRegistries.ITEM.get(random.nextInt(BuiltInRegistries.ITEM.size())).get().value();
                    player.getInventory().add(new ItemStack(item, random.nextInt(1, item.getDefaultMaxStackSize() + 1)));
                }
            }
    }
}
