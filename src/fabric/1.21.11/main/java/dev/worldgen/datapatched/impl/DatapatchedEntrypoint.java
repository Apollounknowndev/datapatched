package dev.worldgen.datapatched.impl;

import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.trade.TradeSet;
import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class DatapatchedEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        // Static registries
        DatapatchedBuiltInRegistries.init();

        // Dynamic registries
        DynamicRegistries.register(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
        DynamicRegistries.register(DatapatchedRegistries.VILLAGER_TRADE, VillagerTrade.CODEC);
        DynamicRegistries.register(DatapatchedRegistries.TRADE_SET, TradeSet.CODEC);

        // Registration
        Datapatched.registerDataComponents((name, codec) -> Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, name, codec));
        Datapatched.registerNumberProviders((name, codec) -> Registry.register(BuiltInRegistries.LOOT_NUMBER_PROVIDER_TYPE, name, codec));
        Datapatched.registerLootConditions((name, type) -> Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, Datapatched.id(name), type));
        Datapatched.registerLootFunctions((id, type) -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, id, type));
        Datapatched.registerLootModifiers((name, codec) -> Registry.register(DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE, Datapatched.id(name), codec));
    }
}
