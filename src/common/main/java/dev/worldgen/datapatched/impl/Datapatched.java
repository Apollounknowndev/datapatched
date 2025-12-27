package dev.worldgen.datapatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.impl.loot.function.*;
import dev.worldgen.datapatched.impl.loot.modifier.AddEntries;
import dev.worldgen.datapatched.impl.loot.modifier.AddPools;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;
import java.util.function.BiConsumer;

import dev.worldgen.datapatched.impl.loot.number.Sum;
import dev.worldgen.datapatched.impl.loot.predicate.VillagerTypePredicate;
import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.msrandom.multiplatform.annotations.Expect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Datapatched {
    public static final String MOD_ID = "datapatched";
    public static final Logger LOGGER = LoggerFactory.getLogger("datapatched");

    @Expect
    public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key);

    @Expect
    public static Pack.ResourcesSupplier createTradeRebalanceSupplier();

    @Expect
    public static MerchantOffer getOffer(VillagerTrades.ItemListing listing, LootContext context);

    @Expect
    public static Entity getEntity(LootContext context);

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath("datapatched", name);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> resourceKey, String name) {
        return ResourceKey.create(resourceKey, id(name));
    }

    public static void registerDataComponents(BiConsumer<Identifier, DataComponentType<?>> consumer) {
        consumer.accept(Identifier.withDefaultNamespace("additional_trade_cost"), VillagerTrade.ADDITIONAL_TRADE_COST);
    }

    public static void registerNumberProviders(BiConsumer<Identifier, LootNumberProviderType> consumer) {
        consumer.accept(Identifier.withDefaultNamespace("sum"), Sum.TYPE);
    }

    public static void registerLootConditions(BiConsumer<String, LootItemConditionType> consumer) {
        consumer.accept("villager_type", VillagerTypePredicate.TYPE);
    }

    public static void registerLootFunctions(BiConsumer<Identifier, LootItemFunctionType<?>> consumer) {
        consumer.accept(Datapatched.id("item_swap"), ItemSwap.TYPE);
        consumer.accept(Datapatched.id("filtered"), NewFiltered.TYPE);
        consumer.accept(Datapatched.id("enchant_randomly"), NewEnchantRandomly.TYPE);
        consumer.accept(Datapatched.id("enchant_with_levels"), NewEnchantWithLevels.TYPE);
        consumer.accept(Identifier.withDefaultNamespace("set_random_dyes"), SetRandomDyes.TYPE);
        consumer.accept(Identifier.withDefaultNamespace("set_random_potion"), SetRandomPotion.TYPE);
    }

    public static void registerLootModifiers(BiConsumer<String, MapCodec<? extends LootModifier>> consumer) {
        consumer.accept("add_entries", AddEntries.CODEC);
        consumer.accept("add_pools", AddPools.CODEC);
        consumer.accept("apply_function", ApplyFunction.CODEC);
    }
}