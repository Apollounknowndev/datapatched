package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.DatapatchedEntrypoint;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Actual;

public class DatapatchedBuiltInRegistriesActual {
    @Actual public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE = DatapatchedEntrypoint.DEFERRED_LOOT_MODIFIER_TYPES.makeRegistry(b -> {});
    @Actual public static final Registry<MapCodec<? extends TradeOffer>> TRADE_OFFER_TYPE = DatapatchedEntrypoint.DEFERRED_TRADE_OFFER_TYPES.makeRegistry(b -> {});
}
