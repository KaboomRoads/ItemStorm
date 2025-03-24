package net.itemstorm.gamerule;

import net.minecraft.world.level.GameRules;

public class ModGameRules {
    public static final GameRules.Key<GameRules.IntegerValue> GIVE_TICKS = GameRules.register(
            "giveTicks", GameRules.Category.UPDATES, GameRules.IntegerValue.create(200)
    );

    public static void init() {
    }
}
