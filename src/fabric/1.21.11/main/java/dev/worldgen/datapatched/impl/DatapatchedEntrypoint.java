package dev.worldgen.datapatched.impl;

import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
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
        DynamicRegistries.register(DatapatchedRegistries.TRADE_OFFER, TradeOffer.CODEC);
        DynamicRegistries.register(DatapatchedRegistries.TRADE_OFFER_PROVIDER, TradeOfferProvider.CODEC);

        // Registration
        Datapatched.registerLootFunctions((name, type) -> Registry.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, Datapatched.id(name), type));
        Datapatched.registerLootModifiers((name, codec) -> Registry.register(DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE, Datapatched.id(name), codec));
        Datapatched.registerTradeOffers((name, codec) -> Registry.register(DatapatchedBuiltInRegistries.TRADE_OFFER_TYPE, Datapatched.id(name), codec));
    }
}
