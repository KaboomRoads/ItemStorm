package net.itemstorm.mixin.chaos;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.WorldGenRegion;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldGenRegion.class)
public abstract class WorldGenRegionMixin {
    @WrapOperation(method = "getBlockEntity", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;)V", remap = false))
    private void suppress(Logger instance, String s, Object o, Operation<Void> original) {
    }

//    @WrapMethod(method = "setBlock")
//    private boolean modify(BlockPos pos, BlockState state, int flags, int recursionLeft, Operation<Boolean> original) {
//        int x = pos.getX();
//        int y = pos.getY();
//        int z = pos.getZ();
//        BlockState modified = state;
//        if (!modified.isAir()) {
//            int id = BuiltInRegistries.BLOCK.getId(modified.getBlock()) + x + (z % 2 == 0 ? 3 : -3) + (y % 2 == 0 ? 2 : -2);
//            ImmutableList<BlockState> possible;
//            int i = 0;
//            int glorglorp = z + (x % 2 == 0 ? 3 : -3) + (y % 2 == 0 ? 2 : -2);
//            boolean b = glorglorp % 2 == 0;
//            while (true) {
//                Block glorp = BuiltInRegistries.BLOCK.byId(loopClamp(id + (b ? i++ : i--), BuiltInRegistries.BLOCK.size()));
//                BlockState florp = glorp.defaultBlockState();
//                if (!florp.isSolidRender() == (!modified.isSolidRender() && modified.getFluidState().isEmpty())) {
//                    possible = glorp.getStateDefinition().getPossibleStates();
//                    break;
//                }
//            }
//            modified = possible.get(loopClamp(glorglorp, possible.size()));
//        }
//        return original.call(pos, modified, flags, recursionLeft);
//    }
//
//    @Unique
//    private static int loopClamp(int i, int max) {
//        int g = i % max;
//        if (g < 0) g += max;
//        return g;
//    }
}
