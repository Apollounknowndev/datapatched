package dev.worldgen.datapatched.impl.trade;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.trade.offer.Runtime;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.msrandom.multiplatform.annotations.Expect;

public class TradeHelper {
    public static VillagerTrades.ItemListing[] NO_MODDED_TRADES = new VillagerTrades.ItemListing[]{};

    public static void addDatapatchedTrades(AbstractVillager entity, MerchantOffers tradeOfferList, TradeOfferProvider.TradeTier tradeTier, VillagerTrades.ItemListing[] moddedTierTrades) {
        HolderSet<TradeOffer> tradeSet = tradeTier.trades();
        int count = Math.min(tradeTier.count(), tradeSet.size() + moddedTierTrades.length);

        ArrayList<TradeOffer> trades = new ArrayList<>(tradeSet.stream().map(Holder::value).toList());
        Arrays.stream(moddedTierTrades).forEach(listing -> trades.add(new Runtime(listing)));
        int i = 0;

        while(i < count) {
            if (trades.isEmpty()) {
                return;
            }

            List<MerchantOffer> offers = (trades.remove(entity.getRandom().nextInt(trades.size()))).create(entity, entity.getRandom());
            if (!offers.isEmpty()) {
                tradeOfferList.addAll(offers);
                ++i;
            }
        }

    }

    @Expect public static Identifier getProfession(VillagerData data);
    @Expect public static Identifier getType(VillagerData data);
    @Expect public static int getLevel(VillagerData data);
}
