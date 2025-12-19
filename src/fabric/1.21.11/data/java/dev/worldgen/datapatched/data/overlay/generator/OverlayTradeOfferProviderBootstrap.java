package dev.worldgen.datapatched.data.overlay.generator;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.overlay.OverlayTagsProvider;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.List;

public class OverlayTradeOfferProviderBootstrap {
    public static void bootstrap(BootstrapContext<TradeOfferProvider> context) {
        context.register(key("wandering_trader"), new TradeOfferProvider(List.of(
            new TradeOfferProvider.TradeTier(2, tag(context, "wandering_trader/buying")),
            new TradeOfferProvider.TradeTier(5, tag(context, "wandering_trader/normal")),
            new TradeOfferProvider.TradeTier(2, tag(context, "wandering_trader/special"))
        )));
    }

    private static HolderSet<TradeOffer> tag(BootstrapContext<TradeOfferProvider> context, String name) {
        return context.lookup(DatapatchedRegistries.TRADE_OFFER).get(OverlayTagsProvider.key(name)).get();
    }

    private static ResourceKey<TradeOfferProvider> key(String name) {
        return ResourceKey.create(DatapatchedRegistries.TRADE_OFFER_PROVIDER, Datapatched.id(name));
    }
}
