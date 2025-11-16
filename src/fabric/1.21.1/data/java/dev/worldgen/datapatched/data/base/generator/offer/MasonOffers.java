package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class MasonOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_clay_ball"), TradeOfferBuilder.emeraldsForItems(Items.CLAY_BALL, 10, 16, 2)),
            register(context, name(1, "sell_brick"), TradeOfferBuilder.itemsForEmeralds(Items.BRICK, 1, 10, 16, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_stone"), TradeOfferBuilder.emeraldsForItems(Items.STONE, 20, 16, 10)),
            register(context, name(2, "sell_chiseled_stone_bricks"), TradeOfferBuilder.itemsForEmeralds(Items.CHISELED_STONE_BRICKS, 1, 4, 16, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_granite"), TradeOfferBuilder.emeraldsForItems(Items.GRANITE, 16, 16, 20)),
            register(context, name(3, "buy_andesite"), TradeOfferBuilder.emeraldsForItems(Items.ANDESITE, 16, 16, 20)),
            register(context, name(3, "buy_diorite"), TradeOfferBuilder.emeraldsForItems(Items.DIORITE, 16, 16, 20)),
            register(context, name(3, "sell_dripstone_block"), TradeOfferBuilder.itemsForEmeralds(Items.DRIPSTONE_BLOCK, 1, 4, 16, 10)),
            register(context, name(3, "sell_polished_andesite"), TradeOfferBuilder.itemsForEmeralds(Items.POLISHED_ANDESITE, 1, 4, 16, 10)),
            register(context, name(3, "sell_polished_diorite"), TradeOfferBuilder.itemsForEmeralds(Items.POLISHED_DIORITE, 1, 4, 16, 10)),
            register(context, name(3, "sell_polished_granite"), TradeOfferBuilder.itemsForEmeralds(Items.POLISHED_GRANITE, 1, 4, 16, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_quartz"), TradeOfferBuilder.emeraldsForItems(Items.QUARTZ, 12, 12, 30)),
            register(context, name(4, "sell_white_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_orange_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_magenta_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_light_blue_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_yellow_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_lime_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_pink_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_gray_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_light_gray_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_cyan_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_purple_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_blue_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_brown_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_green_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_red_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.RED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_black_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_white_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_orange_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_magenta_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_light_blue_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_yellow_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_lime_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_pink_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_gray_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_light_gray_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_cyan_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_purple_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_blue_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_brown_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_green_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_red_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15)),
            register(context, name(4, "sell_black_glazed_terracotta"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_quartz_pillar"), TradeOfferBuilder.itemsForEmeralds(Items.QUARTZ_PILLAR, 1, 1, 12, 30)),
            register(context, name(5, "sell_quartz_block"), TradeOfferBuilder.itemsForEmeralds(Items.QUARTZ_BLOCK, 1, 1, 12, 30))
        ));
    }

    private static String name(int level, String name) {
        return String.format("mason/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
