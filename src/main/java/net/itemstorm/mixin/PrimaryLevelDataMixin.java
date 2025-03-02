package net.itemstorm.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.serialization.Dynamic;
import net.itemstorm.mixinimpl.ModPrimaryLevelData;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PrimaryLevelData.class)
public abstract class PrimaryLevelDataMixin implements ModPrimaryLevelData {
    @Unique
    private boolean itemStorm = false;

    @Inject(method = "setTagData", at = @At("HEAD"))
    private void save(RegistryAccess registry, CompoundTag nbt, CompoundTag playerNBT, CallbackInfo ci) {
        nbt.putBoolean("ItemStorm", itemStorm);
    }

    @ModifyReturnValue(method = "parse", at = @At("RETURN"))
    private static <T> PrimaryLevelData parse(PrimaryLevelData original, Dynamic<T> tag) {
        ((ModPrimaryLevelData) original).itemStorm$setItemStorm(tag.get("ItemStorm").asBoolean(false));
        return original;
    }

    @Override
    public void itemStorm$setItemStorm(boolean itemStorm) {
        this.itemStorm = itemStorm;
    }

    @Override
    public boolean itemStorm$getItemStorm() {
        return itemStorm;
    }
}
