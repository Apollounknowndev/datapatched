package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.TradeSet;
import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface DatapatchedRegistries {
    ResourceKey<Registry<MapCodec<? extends LootModifier>>> LOOT_MODIFIER_TYPE = key(Datapatched.id("loot_modifier_type"));
    ResourceKey<Registry<LootModifier>> LOOT_MODIFIER = key(Datapatched.id("loot_modifier"));

    ResourceKey<Registry<VillagerTrade>> VILLAGER_TRADE = key(Identifier.withDefaultNamespace("villager_trade"));
    ResourceKey<Registry<TradeSet>> TRADE_SET = key(Identifier.withDefaultNamespace("trade_set"));

    private static <T> ResourceKey<Registry<T>> key(Identifier id) {
        return ResourceKey.createRegistryKey(id);
    }
}