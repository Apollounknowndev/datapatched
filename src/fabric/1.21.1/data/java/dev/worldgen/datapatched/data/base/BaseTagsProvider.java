package dev.worldgen.datapatched.data.base;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.base.generator.offer.*;
import dev.worldgen.datapatched.impl.Datapatched;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BaseTagsProvider extends FabricTagProvider<TradeOffer> {
    public BaseTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, DatapatchedRegistries.TRADE_OFFER, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(key("wandering_trader/normal")).addTag(key("wandering_trader/normal_overlay")).addAll(WanderingTraderOffers.NORMAL_OFFERS);
        tag(key("wandering_trader/special")).addTag(key("wandering_trader/special_overlay")).addAll(WanderingTraderOffers.SPECIAL_OFFERS);
        tag(key("wandering_trader/normal_overlay"));
        tag(key("wandering_trader/special_overlay"));

        tag(key("cartographer/novice_overlay"));
        tag(key("cartographer/apprentice_overlay"));
        tag(key("cartographer/journeyman_overlay"));
        tag(key("cartographer/expert_overlay"));
        tag(key("cartographer/master_overlay"));

        tag(key("fletcher/tipped_arrow")).addAll(FletcherOffers.TIPPED_ARROWS);

        base("armorer", ArmorerOffers.OFFERS);
        base("butcher", ArmorerOffers.OFFERS);
        base("cartographer", CartographerOffers.OFFERS, true);
        base("cleric", ClericOffers.OFFERS);
        base("farmer", FarmerOffers.OFFERS);
        base("fisherman", FishermanOffers.OFFERS);
        base("fletcher", FletcherOffers.OFFERS);
        base("leatherworker", LeatherworkerOffers.OFFERS);
        base("librarian", LibrarianOffers.OFFERS);
        base("mason", MasonOffers.OFFERS);
        base("shepherd", ShepherdOffers.OFFERS);
        base("toolsmith", ToolsmithOffers.OFFERS);
        base("weaponsmith", WeaponsmithOffers.OFFERS);
    }

    private void base(String profession, List<List<ResourceKey<TradeOffer>>> offers) {
        base(profession, offers, false);
    }

    private void base(String profession, List<List<ResourceKey<TradeOffer>>> offers, boolean withOverlay) {
        for (int level = 0; level < 5; level++) {
            String name = TradeOfferBuilder.LEVEL_TO_NAME.get(level);
            var appender = tag(key(profession + "/" + name)).addAll(offers.get(level));
            if (withOverlay) {
                appender.addTag(key(profession + "/" + name + "_overlay"));
            }
        }
    }

    public static TagKey<TradeOffer> key(String name) {
        return TagKey.create(DatapatchedRegistries.TRADE_OFFER, Datapatched.id(name));
    }
}
