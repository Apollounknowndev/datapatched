package dev.worldgen.datapatched.impl.trade;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.msrandom.multiplatform.annotations.Actual;

public class TradeHelperActual {
    @Actual
    public static Identifier getProfession(VillagerData data) {
        return data.profession().unwrapKey().get().identifier();
    }

    @Actual
    public static Identifier getType(VillagerData data) {
        return data.type().unwrapKey().get().identifier();
    }

    @Actual
    public static int getLevel(VillagerData data) {
        return data.level();
    }
}
