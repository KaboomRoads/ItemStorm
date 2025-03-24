package net.itemstorm.mixin.chaos;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;
import java.util.UUID;

@Mixin(TrialSpawnerData.class)
public abstract class TrialSpawnerDataMixin {
    @Shadow
    @Final
    protected Set<UUID> detectedPlayers;

    @WrapMethod(method = "countAdditionalPlayers")
    private int suppress(BlockPos pos, Operation<Integer> original) {
        return Math.max(0, detectedPlayers.size() - 1);
    }
}
