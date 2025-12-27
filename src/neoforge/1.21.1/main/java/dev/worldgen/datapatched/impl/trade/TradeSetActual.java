package dev.worldgen.datapatched.impl.trade;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.msrandom.multiplatform.annotations.Actual;

import java.util.List;

public class TradeSetActual {
    @Actual
    public static boolean addOffers(ServerLevel serverLevel, AbstractVillager villager, ResourceKey<VillagerProfession> profession, int level, Identifier tradeKey) {
        var optional = Datapatched.registry(serverLevel.registryAccess(), DatapatchedRegistries.TRADE_SET).getOptional(tradeKey);
        if (optional.isEmpty()) return false;
        TradeSet tradeSet = optional.get();

        LootContext lootContext = new LootContext.Builder(new LootParams.Builder(serverLevel).withParameter(LootContextParams.ORIGIN, villager.position()).withParameter(LootContextParams.THIS_ENTITY, villager).create(LootContextParamSets.GIFT)).create(tradeSet.randomSequence());
        int numberOfOffers = tradeSet.calculateNumberOfTrades(lootContext);
        List<MerchantOfferProvider> offers = TradeSet.collectPotentialOffers(villager, profession, level, tradeSet);

        if (tradeSet.allowDuplicates()) {
            TradeSet.addOffersFromItemListings(lootContext, villager.getOffers(), offers, numberOfOffers);
        } else {
            TradeSet.addOffersFromItemListingsWithoutDuplicates(lootContext, villager.getOffers(), offers, numberOfOffers);
        }
        return true;
    }
}
