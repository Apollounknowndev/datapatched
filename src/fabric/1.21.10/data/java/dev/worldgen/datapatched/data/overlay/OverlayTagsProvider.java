package dev.worldgen.datapatched.data.overlay;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.overlay.generator.offer.CartographerOffers;
import dev.worldgen.datapatched.data.overlay.generator.offer.WanderingTraderOffers;
import dev.worldgen.datapatched.impl.Datapatched;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;

import java.util.concurrent.CompletableFuture;

public class OverlayTagsProvider extends FabricTagProvider<TradeOffer> {
    public OverlayTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, DatapatchedRegistries.TRADE_OFFER, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(key("wandering_trader/normal_overlay")).addAll(WanderingTraderOffers.NORMAL_OVERLAY_OFFERS);
        builder(key("wandering_trader/special_overlay")).addAll(WanderingTraderOffers.SPECIAL_OVERLAY_OFFERS);
        builder(key("wandering_trader/buying")).addAll(WanderingTraderOffers.BUYING_OFFERS);

        builder(key("cartographer/novice_overlay")).addAll(CartographerOffers.OFFERS.get(0));
        builder(key("cartographer/apprentice_overlay")).addAll(CartographerOffers.OFFERS.get(1));
        builder(key("cartographer/journeyman_overlay")).addAll(CartographerOffers.OFFERS.get(2));
        builder(key("cartographer/expert_overlay")).addAll(CartographerOffers.OFFERS.get(3));
        builder(key("cartographer/master_overlay")).addAll(CartographerOffers.OFFERS.get(4));
    }

    public static TagKey<TradeOffer> key(String name) {
        return TagKey.create(DatapatchedRegistries.TRADE_OFFER, Datapatched.id(name));
    }

    @Override
    public String getName() {
        return "Overlay tags for datapatched:trade_offer";
    }
}
