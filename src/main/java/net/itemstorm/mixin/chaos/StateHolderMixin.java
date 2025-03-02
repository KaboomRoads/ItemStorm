package net.itemstorm.mixin.chaos;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StateHolder.class)
public abstract class StateHolderMixin<S, O> {
    @Shadow
    public abstract <T extends Comparable<T>> boolean hasProperty(Property<T> property);

    @WrapMethod(method = "getValue")
    private <T extends Comparable<T>> T modify(Property<T> property, Operation<T> original) {
        if (hasProperty(property))
            return original.call(property);
        else return property.getPossibleValues().getFirst();
    }

    @WrapMethod(method = "setValue")
    private <T extends Comparable<T>, V extends T> S modify(Property<T> property, V comparable, Operation<S> original) {
        if (!hasProperty(property)) return (S) this;
        return original.call(property, comparable);
    }
}
