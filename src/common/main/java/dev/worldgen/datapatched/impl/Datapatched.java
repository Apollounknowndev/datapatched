package dev.worldgen.datapatched.impl;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.loot.function.ApplyDyesFunction;
import dev.worldgen.datapatched.impl.loot.function.DiscardFunction;
import dev.worldgen.datapatched.impl.loot.function.ItemSwapFunction;
import dev.worldgen.datapatched.impl.loot.modifier.AddEntries;
import dev.worldgen.datapatched.impl.loot.modifier.AddPools;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;
import dev.worldgen.datapatched.impl.trade.offer.Base;
import dev.worldgen.datapatched.impl.trade.offer.Condition;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import dev.worldgen.datapatched.impl.trade.offer.EnchantedBook;
import dev.worldgen.datapatched.impl.trade.offer.EnchantedItem;
import dev.worldgen.datapatched.impl.trade.offer.Group;
import dev.worldgen.datapatched.impl.trade.offer.Random;
import dev.worldgen.datapatched.impl.trade.offer.TypeSpecific;
import java.util.function.BiConsumer;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.msrandom.multiplatform.annotations.Actual;
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

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath("datapatched", name);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> resourceKey, String name) {
        return ResourceKey.create(resourceKey, id(name));
    }

    public static void registerTradeOffers(BiConsumer<String, MapCodec<? extends TradeOffer>> consumer) {
        consumer.accept("base", Base.CODEC);
        consumer.accept("condition", Condition.CODEC);
        consumer.accept("empty", Empty.CODEC);
        consumer.accept("enchanted_book", EnchantedBook.CODEC);
        consumer.accept("enchanted_item", EnchantedItem.CODEC);
        consumer.accept("group", Group.CODEC);
        consumer.accept("random", Random.CODEC);
        consumer.accept("type_specific", TypeSpecific.CODEC);
    }

    public static void registerLootFunctions(BiConsumer<String, LootItemFunctionType<?>> consumer) {
        consumer.accept("item_swap", ItemSwapFunction.TYPE);
        consumer.accept("discard", DiscardFunction.TYPE);
        consumer.accept("apply_dyes", ApplyDyesFunction.TYPE);
    }

    public static void registerLootModifiers(BiConsumer<String, MapCodec<? extends LootModifier>> consumer) {
        consumer.accept("add_entries", AddEntries.CODEC);
        consumer.accept("add_pools", AddPools.CODEC);
        consumer.accept("apply_function", ApplyFunction.CODEC);
    }
}