package dev.worldgen.datapatched.impl.trade;

import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.msrandom.multiplatform.annotations.Expect;

public class TradeHelper {
    public static ResourceKey<VillagerProfession> WANDERING_TRADER = ResourceKey.create(Registries.VILLAGER_PROFESSION, Datapatched.id("wandering_trader_placeholder"));
    public static VillagerTrades.ItemListing[] NO_MODDED_TRADES = new VillagerTrades.ItemListing[]{};

    @Expect public static Identifier getProfession(VillagerData data);
    @Expect public static Identifier getType(VillagerData data);
    @Expect public static int getLevel(VillagerData data);
}
