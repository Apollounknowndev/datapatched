package dev.worldgen.datapatched.data.overlay.generator;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.overlay.generator.offer.CartographerOffers;
import dev.worldgen.datapatched.data.overlay.generator.offer.WanderingTraderOffers;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;

public class OverlayTradeOfferBootstrap {
    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        CartographerOffers.bootstrap(context);
        WanderingTraderOffers.bootstrap(context);
    }

    public static ResourceKey<TradeOffer> key(String name) {
        return ResourceKey.create(DatapatchedRegistries.TRADE_OFFER, Datapatched.id(name));
    }

    public static ResourceKey<TradeOffer> register(BootstrapContext<TradeOffer> context, String name, TradeOffer offer) {
        var key = key(name);
        context.register(key, offer);
        return key;
    }

    public static String itemPath(ItemLike item) {
        return BuiltInRegistries.ITEM.getKey(item.asItem()).getPath();
    }
}
