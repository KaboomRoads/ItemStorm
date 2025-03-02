package net.itemstorm.mixin;

import net.itemstorm.mixinimpl.ModPrimaryLevelData;
import net.minecraft.world.level.storage.DerivedLevelData;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DerivedLevelData.class)
public abstract class DerivedLevelDataMixin implements ModPrimaryLevelData {
    @Shadow
    @Final
    private WorldData worldData;

    @Override
    public void itemStorm$setItemStorm(boolean itemStorm) {
        ((ModPrimaryLevelData) worldData).itemStorm$setItemStorm(itemStorm);
    }

    @Override
    public boolean itemStorm$getItemStorm() {
        return ((ModPrimaryLevelData) worldData).itemStorm$getItemStorm();
    }
}
