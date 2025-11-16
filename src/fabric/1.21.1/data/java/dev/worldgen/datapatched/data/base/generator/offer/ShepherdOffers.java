package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class ShepherdOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_white_wool"), TradeOfferBuilder.emeraldsForItems(Items.WHITE_WOOL, 18, 16, 2)),
            register(context, name(1, "buy_brown_wool"), TradeOfferBuilder.emeraldsForItems(Items.BROWN_WOOL, 18, 16, 2)),
            register(context, name(1, "buy_black_wool"), TradeOfferBuilder.emeraldsForItems(Items.BLACK_WOOL, 18, 16, 2)),
            register(context, name(1, "buy_gray_wool"), TradeOfferBuilder.emeraldsForItems(Items.GRAY_WOOL, 18, 16, 2)),
            register(context, name(1, "sell_shears"), TradeOfferBuilder.itemsForEmeralds(Items.SHEARS, 2, 1, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_white_dye"), TradeOfferBuilder.emeraldsForItems(Items.WHITE_DYE, 12, 16, 10)),
            register(context, name(2, "buy_gray_dye"), TradeOfferBuilder.emeraldsForItems(Items.GRAY_DYE, 12, 16, 10)),
            register(context, name(2, "buy_black_dye"), TradeOfferBuilder.emeraldsForItems(Items.BLACK_DYE, 12, 16, 10)),
            register(context, name(2, "buy_light_blue_dye"), TradeOfferBuilder.emeraldsForItems(Items.LIGHT_BLUE_DYE, 12, 16, 10)),
            register(context, name(2, "buy_lime_dye"), TradeOfferBuilder.emeraldsForItems(Items.LIME_DYE, 12, 16, 10)),
            register(context, name(2, "sell_white_wool"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_orange_wool"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_magenta_wool"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_light_blue_wool"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_yellow_wool"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_lime_wool"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_pink_wool"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_gray_wool"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_light_gray_wool"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_cyan_wool"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_purple_wool"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_blue_wool"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_brown_wool"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_green_wool"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_red_wool"), TradeOfferBuilder.itemsForEmeralds(Items.RED_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_black_wool"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_WOOL, 1, 1, 16, 5)),
            register(context, name(2, "sell_white_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_orange_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_magenta_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_light_blue_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_yellow_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_lime_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_pink_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_gray_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_light_gray_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_cyan_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_purple_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_blue_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_brown_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_green_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_red_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.RED_CARPET, 1, 4, 16, 5)),
            register(context, name(2, "sell_black_carpet"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_CARPET, 1, 4, 16, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_yellow_dye"), TradeOfferBuilder.emeraldsForItems(Items.YELLOW_DYE, 12, 16, 20)),
            register(context, name(3, "buy_light_gray_dye"), TradeOfferBuilder.emeraldsForItems(Items.LIGHT_GRAY_DYE, 12, 16, 20)),
            register(context, name(3, "buy_orange_dye"), TradeOfferBuilder.emeraldsForItems(Items.ORANGE_DYE, 12, 16, 20)),
            register(context, name(3, "buy_red_dye"), TradeOfferBuilder.emeraldsForItems(Items.RED_DYE, 12, 16, 20)),
            register(context, name(3, "buy_pink_dye"), TradeOfferBuilder.emeraldsForItems(Items.PINK_DYE, 12, 16, 20)),

            register(context, name(3, "sell_white_bed"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_orange_bed"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_magenta_bed"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_light_blue_bed"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_yellow_bed"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_lime_bed"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_pink_bed"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_gray_bed"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_light_gray_bed"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_cyan_bed"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_purple_bed"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_blue_bed"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_brown_bed"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_green_bed"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_red_bed"), TradeOfferBuilder.itemsForEmeralds(Items.RED_BED, 3, 1, 12, 10)),
            register(context, name(3, "sell_black_bed"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_BED, 3, 1, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_brown_dye"), TradeOfferBuilder.emeraldsForItems(Items.BROWN_DYE, 12, 16, 30)),
            register(context, name(4, "buy_purple_dye"), TradeOfferBuilder.emeraldsForItems(Items.PURPLE_DYE, 12, 16, 30)),
            register(context, name(4, "buy_blue_dye"), TradeOfferBuilder.emeraldsForItems(Items.BLUE_DYE, 12, 16, 30)),
            register(context, name(4, "buy_green_dye"), TradeOfferBuilder.emeraldsForItems(Items.GREEN_DYE, 12, 16, 30)),
            register(context, name(4, "buy_magenta_dye"), TradeOfferBuilder.emeraldsForItems(Items.MAGENTA_DYE, 12, 16, 30)),
            register(context, name(4, "buy_cyan_dye"), TradeOfferBuilder.emeraldsForItems(Items.CYAN_DYE, 12, 16, 30)),

            register(context, name(4, "sell_white_banner"), TradeOfferBuilder.itemsForEmeralds(Items.WHITE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_orange_banner"), TradeOfferBuilder.itemsForEmeralds(Items.ORANGE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_magenta_banner"), TradeOfferBuilder.itemsForEmeralds(Items.MAGENTA_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_light_blue_banner"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_yellow_banner"), TradeOfferBuilder.itemsForEmeralds(Items.YELLOW_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_lime_banner"), TradeOfferBuilder.itemsForEmeralds(Items.LIME_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_pink_banner"), TradeOfferBuilder.itemsForEmeralds(Items.PINK_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_gray_banner"), TradeOfferBuilder.itemsForEmeralds(Items.GRAY_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_light_gray_banner"), TradeOfferBuilder.itemsForEmeralds(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_cyan_banner"), TradeOfferBuilder.itemsForEmeralds(Items.CYAN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_purple_banner"), TradeOfferBuilder.itemsForEmeralds(Items.PURPLE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_blue_banner"), TradeOfferBuilder.itemsForEmeralds(Items.BLUE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_brown_banner"), TradeOfferBuilder.itemsForEmeralds(Items.BROWN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_green_banner"), TradeOfferBuilder.itemsForEmeralds(Items.GREEN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_red_banner"), TradeOfferBuilder.itemsForEmeralds(Items.RED_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_black_banner"), TradeOfferBuilder.itemsForEmeralds(Items.BLACK_BANNER, 3, 1, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_painting"), TradeOfferBuilder.itemsForEmeralds(Items.PAINTING, 2, 3, 12, 30))
        ));
    }

    private static String name(int level, String name) {
        return String.format("shepherd/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
