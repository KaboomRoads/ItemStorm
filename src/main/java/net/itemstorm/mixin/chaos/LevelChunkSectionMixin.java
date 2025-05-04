package net.itemstorm.mixin.chaos;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.itemstorm.ItemStorm;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LevelChunkSection.class)
public abstract class LevelChunkSectionMixin {
    @WrapMethod(method = "setBlockState(IIILnet/minecraft/world/level/block/state/BlockState;Z)Lnet/minecraft/world/level/block/state/BlockState;")
    private BlockState modify(int x, int y, int z, BlockState blockState, boolean bl, Operation<BlockState> original) {
        BlockState modified = blockState;
        if (ItemStorm.CHAOSGEN.get()) {
            if (!modified.isAir()) {
                int id = BuiltInRegistries.BLOCK.getId(modified.getBlock()) + x + (z % 2 == 0 ? 3 : -3) + (y % 2 == 0 ? 2 : -2);
                ImmutableList<BlockState> possible;
                int i = 0;
                int glorglorp = z + (x % 2 == 0 ? 3 : -3) + (y % 2 == 0 ? 2 : -2);
                boolean b = glorglorp % 2 == 0;
                while (true) {
                    Block glorp = BuiltInRegistries.BLOCK.byId(loopClamp(id + (b ? i++ : i--), BuiltInRegistries.BLOCK.size()));
                    BlockState florp = glorp.defaultBlockState();
                    if (!florp.isSolidRender() == (!modified.isSolidRender() && modified.getFluidState().isEmpty())) {
                        possible = glorp.getStateDefinition().getPossibleStates();
                        break;
                    }
                }
                modified = possible.get(loopClamp(glorglorp, possible.size()));
            }
        }
        return original.call(x, y, z, modified, bl);
    }

    @Unique
    private static int loopClamp(int i, int max) {
        int g = i % max;
        if (g < 0) g += max;
        return g;
    }
}
