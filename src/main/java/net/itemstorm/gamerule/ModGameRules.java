package net.itemstorm.gamerule;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static final GameRules.Key<GameRules.IntegerValue> GIVE_TICKS = GameRules.register(
            "giveTicks", GameRules.Category.UPDATES, GameRules.IntegerValue.create(200)
    );
    public static final GameRules.Key<GameRules.IntegerValue> MIN_STACK_SIZE = GameRules.register(
            "minStackSize", GameRules.Category.UPDATES, GameRules.IntegerValue.create(1)
    );
    public static final GameRules.Key<GameRules.IntegerValue> MAX_STACK_SIZE = GameRules.register(
            "maxStackSize", GameRules.Category.UPDATES, GameRules.IntegerValue.create(99)
    );

    public static void init() {
    }
}
