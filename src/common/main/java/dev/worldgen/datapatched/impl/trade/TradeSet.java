package dev.worldgen.datapatched.impl.trade;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Util;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.msrandom.multiplatform.annotations.Expect;

import java.util.*;


public record TradeSet(HolderSet<VillagerTrade> trades, NumberProvider amount, boolean allowDuplicates, Optional<Identifier> randomSequence, boolean overrideModdedTrades) {
    public static final Codec<TradeSet> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        RegistryCodecs.homogeneousList(DatapatchedRegistries.VILLAGER_TRADE).fieldOf("trades").forGetter(TradeSet::trades), 
        NumberProviders.CODEC.fieldOf("amount").forGetter(TradeSet::amount), 
        Codec.BOOL.optionalFieldOf("allow_duplicates", false).forGetter(TradeSet::allowDuplicates),
        Identifier.CODEC.optionalFieldOf("random_sequence").forGetter(TradeSet::randomSequence),
        Codec.BOOL.optionalFieldOf("override_modded_trades", false).forGetter(TradeSet::overrideModdedTrades)
    ).apply(instance, TradeSet::new));


    public int calculateNumberOfTrades(LootContext lootContext) {
        return this.amount.getInt(lootContext);
    }

    @Expect
    public static boolean addOffers(ServerLevel serverLevel, AbstractVillager villager, ResourceKey<VillagerProfession> profession, int level, Identifier tradeKey);

    @SuppressWarnings("all")
    public static List<MerchantOfferProvider> collectPotentialOffers(AbstractVillager villager, ResourceKey<VillagerProfession> profession, int level, TradeSet set) {
        List<MerchantOfferProvider> offers = new ArrayList<>();
        set.trades().forEach(holder -> offers.add(holder.value()));

        // Hack for wandering traders
        if (profession.equals(TradeHelper.WANDERING_TRADER)) return offers;

        // Cursed nonsense to add modded trades in
        if (!set.overrideModdedTrades()) {
            Int2ObjectMap<VillagerTrades.ItemListing[]> moddedFullTrades;
            if (villager.level().enabledFeatures().contains(FeatureFlags.TRADE_REBALANCE)) {
                Int2ObjectMap<VillagerTrades.ItemListing[]> experimentalModdedTrades = (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.EXPERIMENTAL_TRADES.get(profession);
                moddedFullTrades = experimentalModdedTrades != null ? experimentalModdedTrades : (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.TRADES.get(profession);
            } else {
                moddedFullTrades = (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.TRADES.get(profession);
            }
            if (moddedFullTrades != null) {
                Arrays.stream(moddedFullTrades.getOrDefault(level, TradeHelper.NO_MODDED_TRADES)).forEach(listing -> offers.add(new RuntimeTrade(listing)));
            }
        }
        return offers;
    }

    public static void addOffersFromItemListings(LootContext lootContext, MerchantOffers merchantOffers, List<MerchantOfferProvider> potentialOffers, int numberOfOffers) {
        Optional<MerchantOfferProvider> villagerTrade;
        int offersFound = 0;
        while (offersFound < numberOfOffers && !(villagerTrade = Util.getRandomSafe(potentialOffers, lootContext.getRandom())).isEmpty()) {
            MerchantOffer offer = villagerTrade.get().getOffer(lootContext);
            if (offer == null) continue;
            merchantOffers.add(offer);
            ++offersFound;
        }
    }

    public static void addOffersFromItemListingsWithoutDuplicates(LootContext lootContext, MerchantOffers merchantOffers, List<MerchantOfferProvider> potentialOffers, int numberOfOffers) {
        ArrayList<MerchantOfferProvider> leftoverOffers = Lists.newArrayList(potentialOffers);
        int offersFound = 0;
        while (offersFound < numberOfOffers && !leftoverOffers.isEmpty()) {
            MerchantOfferProvider villagerTrade = leftoverOffers.remove(lootContext.getRandom().nextInt(leftoverOffers.size()));
            MerchantOffer offer = villagerTrade.getOffer(lootContext);
            if (offer == null) continue;
            merchantOffers.add(offer);
            ++offersFound;
        }
    }
}

