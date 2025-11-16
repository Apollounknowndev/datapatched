package dev.worldgen.datapatched.data.base.generator;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.data.base.BaseTagsProvider;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.List;

public class BaseTradeOfferProviderBootstrap {
    public static void bootstrap(BootstrapContext<TradeOfferProvider> context) {
        context.register(key("wandering_trader"), new TradeOfferProvider(List.of(
            new TradeOfferProvider.TradeTier(5, tag(context, "wandering_trader/normal")),
            new TradeOfferProvider.TradeTier(1, tag(context, "wandering_trader/special"))
        )));

        base(context, "armorer");
        base(context, "butcher");
        base(context, "cartographer");
        base(context, "cleric");
        base(context, "farmer");
        base(context, "fisherman");
        base(context, "fletcher");
        base(context, "librarian");
        base(context, "shepherd");
        base(context, "toolsmith");
        base(context, "weaponsmith");
    }

    private static void base(BootstrapContext<TradeOfferProvider> context, String name) {
        context.register(key(name), new TradeOfferProvider(List.of(
            new TradeOfferProvider.TradeTier(2, tag(context, name + "/novice")),
            new TradeOfferProvider.TradeTier(2, tag(context, name + "/apprentice")),
            new TradeOfferProvider.TradeTier(2, tag(context, name + "/journeyman")),
            new TradeOfferProvider.TradeTier(2, tag(context, name + "/expert")),
            new TradeOfferProvider.TradeTier(2, tag(context, name + "/master"))
        )));
    }

    private static HolderSet<TradeOffer> tag(BootstrapContext<TradeOfferProvider> context, String name) {
        return context.lookup(DatapatchedRegistries.TRADE_OFFER).get(BaseTagsProvider.key(name)).get();
    }

    private static ResourceKey<TradeOfferProvider> key(String name) {
        return ResourceKey.create(DatapatchedRegistries.TRADE_OFFER_PROVIDER, Datapatched.id(name));
    }
}
