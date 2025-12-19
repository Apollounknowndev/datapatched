package dev.worldgen.datapatched.impl.trade;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.msrandom.multiplatform.annotations.Actual;

public class TradeHelperActual {
    @Actual
    public static Identifier getProfession(VillagerData data) {
        return Identifier.parse(data.getProfession().toString());
    }

    @Actual
    public static Identifier getType(VillagerData data) {
        return Identifier.parse(data.getType().toString());
    }

    @Actual
    public static int getLevel(VillagerData data) {
        return data.getLevel();
    }
}
