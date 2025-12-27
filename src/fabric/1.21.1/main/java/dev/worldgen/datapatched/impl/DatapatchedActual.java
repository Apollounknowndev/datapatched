package dev.worldgen.datapatched.impl;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.msrandom.multiplatform.annotations.Actual;

import java.nio.file.Path;

import static dev.worldgen.datapatched.impl.Datapatched.MOD_ID;

public class DatapatchedActual {
    @Actual
    public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key) {
        return registries.registryOrThrow(key);
    }

    @Actual
    public static Pack.ResourcesSupplier createTradeRebalanceSupplier() {
        Path resourcePath = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow().findPath("trade_rebalance").orElseThrow();
        return new PathPackResources.PathResourcesSupplier(resourcePath);
    }

    @Actual
    public static MerchantOffer getOffer(VillagerTrades.ItemListing listing, LootContext context) {
        return listing.getOffer(context.getParam(LootContextParams.THIS_ENTITY), context.getRandom());
    }

    @Actual
    public static Entity getEntity(LootContext context) {
        return context.getParamOrNull(LootContextParams.THIS_ENTITY);
    }
}
