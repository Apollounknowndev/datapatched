package dev.worldgen.datapatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.loot.function.Discard;
import dev.worldgen.datapatched.impl.loot.predicate.sub.DatapatchedSubPredicates;
import dev.worldgen.datapatched.impl.trade.TradeSet;
import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Datapatched.MOD_ID)
public class DatapatchedEntrypoint {
    public static final DeferredRegister<MapCodec<? extends LootModifier>> DEFERRED_LOOT_MODIFIER_TYPES = DeferredRegister.create(DatapatchedRegistries.LOOT_MODIFIER_TYPE, Datapatched.MOD_ID);

    public DatapatchedEntrypoint(IEventBus bus) {
        DatapatchedBuiltInRegistries.init();

        bus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
            event.dataPackRegistry(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
            event.dataPackRegistry(DatapatchedRegistries.VILLAGER_TRADE, VillagerTrade.CODEC);
            event.dataPackRegistry(DatapatchedRegistries.TRADE_SET, TradeSet.CODEC);
        });

        bus.addListener((RegisterEvent event) -> {
            Datapatched.registerDataComponents((name, type) -> register(event, Registries.DATA_COMPONENT_TYPE, name, type));
            Datapatched.registerNumberProviders((id, type) -> register(event, Registries.LOOT_NUMBER_PROVIDER_TYPE, id, type));
            Datapatched.registerLootConditions((name, type) -> register(event, Registries.LOOT_CONDITION_TYPE, Datapatched.id(name), type));
            Datapatched.registerLootFunctions((id, type) -> register(event, Registries.LOOT_FUNCTION_TYPE, id, type));
            DatapatchedSubPredicates.registerDatapatchedSubPredicates((name, type) -> register(event, Registries.ITEM_SUB_PREDICATE_TYPE, Identifier.withDefaultNamespace(name), type));
            register(event, Registries.LOOT_FUNCTION_TYPE, Identifier.withDefaultNamespace("discard"), Discard.TYPE);
        });

        Datapatched.registerLootModifiers((name, codec) -> DEFERRED_LOOT_MODIFIER_TYPES.register(name, () -> codec));
        DEFERRED_LOOT_MODIFIER_TYPES.register(bus);
    }

    private static <T> void register(RegisterEvent event, ResourceKey<Registry<T>> registry, Identifier id, T object) {
        event.register(registry, helper -> helper.register(id, object));
    }
}
