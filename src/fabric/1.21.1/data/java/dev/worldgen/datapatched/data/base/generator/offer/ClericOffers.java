package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class ClericOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_rotten_flesh"), TradeOfferBuilder.emeraldsForItems(Items.STICK, 32, 16, 2)),
            register(context, name(1, "sell_redstone"), TradeOfferBuilder.itemsForEmeralds(Items.REDSTONE, 1, 2, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_gold_ingot"), TradeOfferBuilder.emeraldsForItems(Items.GOLD_INGOT, 3, 12, 10)),
            register(context, name(2, "sell_lapis_lazuli"), TradeOfferBuilder.itemsForEmeralds(Items.LAPIS_LAZULI, 1, 1, 12, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_rabbit_foot"), TradeOfferBuilder.emeraldsForItems(Items.RABBIT_FOOT, 2, 12, 20)),
            register(context, name(3, "sell_glowstone"), TradeOfferBuilder.itemsForEmeralds(Items.GLOWSTONE, 4, 1, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_turtle_scute"), TradeOfferBuilder.emeraldsForItems(Items.TURTLE_SCUTE, 4, 12, 30)),
            register(context, name(4, "buy_glass_bottle"), TradeOfferBuilder.emeraldsForItems(Items.GLASS_BOTTLE, 9, 12, 30)),
            register(context, name(4, "sell_ender_pearl"), TradeOfferBuilder.itemsForEmeralds(Items.ENDER_PEARL, 5, 1, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "buy_nether_wart"), TradeOfferBuilder.emeraldsForItems(Items.NETHER_WART, 22, 12, 30)),
            register(context, name(5, "sell_experience_bottle"), TradeOfferBuilder.itemsForEmeralds(Items.EXPERIENCE_BOTTLE, 3, 1, 12, 30))
        ));
    }

    private static String name(int level, String name) {
        return String.format("cleric/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
