package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class ButcherOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_chicken"), TradeOfferBuilder.emeraldsForItems(Items.CHICKEN, 14, 16, 2)),
            register(context, name(1, "buy_porkchop"), TradeOfferBuilder.emeraldsForItems(Items.PORKCHOP, 7, 16, 2)),
            register(context, name(1, "buy_rabbit"), TradeOfferBuilder.emeraldsForItems(Items.RABBIT, 7, 16, 2)),
            register(context, name(1, "sell_rabbit_stew"), TradeOfferBuilder.itemsForEmeralds(Items.RABBIT_STEW, 1, 1, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_coal"), TradeOfferBuilder.emeraldsForItems(Items.COAL, 15, 16, 2)),
            register(context, name(2, "sell_cooked_porkchop"), TradeOfferBuilder.itemsForEmeralds(Items.COOKED_PORKCHOP, 1, 5, 16, 5)),
            register(context, name(2, "sell_cooked_chicken"), TradeOfferBuilder.itemsForEmeralds(Items.COOKED_CHICKEN, 1, 5, 16, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_mutton"), TradeOfferBuilder.emeraldsForItems(Items.MUTTON, 7, 16, 20)),
            register(context, name(3, "buy_beef"), TradeOfferBuilder.emeraldsForItems(Items.BEEF, 10, 16, 20))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_dried_kelp_block"), TradeOfferBuilder.emeraldsForItems(Items.DRIED_KELP_BLOCK, 10, 12, 30))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "buy_sweet_berries"), TradeOfferBuilder.emeraldsForItems(Items.DRIED_KELP_BLOCK, 10, 12, 30))
        ));
    }

    private static String name(int level, String name) {
        return String.format("butcher/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
