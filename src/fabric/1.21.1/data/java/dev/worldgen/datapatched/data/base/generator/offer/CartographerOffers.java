package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.VillagerKeys;
import dev.worldgen.datapatched.impl.trade.offer.Base;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.SequenceFunction;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.worldgen.datapatched.api.trade.TradeOfferBuilder.*;
import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class CartographerOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_paper"), emeraldsForItems(Items.PAPER, 24, 12, 2)),
            register(context, name(1, "sell_map"), itemsForEmeralds(Items.MAP, 7, 1, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_glass_pane"), emeraldsForItems(Items.GLASS_PANE, 11, 16, 10)),
            register(context, name(2, "sell_ocean_explorer_map"),
                map(13, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.OCEAN_MONUMENT, 12, 10)
            )
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_compass"), emeraldsForItems(Items.COMPASS, 1, 12, 20)),
            register(context, name(3, "sell_woodland_explorer_map"),
                map(14, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.WOODLAND_MANSION, 12, 30)
            ),
            register(context, name(3, "sell_trial_chambers_map"),
                map(12, StructureTags.ON_TRIAL_CHAMBERS_MAPS, "filled_map.trial_chambers", MapDecorationTypes.TRIAL_CHAMBERS, 12, 10)
            )
        ));
        OFFERS.add(List.of(
            register(context, name(4, "sell_item_frame"), itemsForEmeralds(Items.ITEM_FRAME, 7, 1, 12, 15)),
            register(context, name(4, "sell_blue_banner"), itemsForEmeralds(Items.BLUE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_white_banner"), itemsForEmeralds(Items.WHITE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_red_banner"), itemsForEmeralds(Items.RED_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_green_banner"), itemsForEmeralds(Items.GREEN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_lime_banner"), itemsForEmeralds(Items.LIME_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_purple_banner"), itemsForEmeralds(Items.PURPLE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_cyan_banner"), itemsForEmeralds(Items.CYAN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_yellow_banner"), itemsForEmeralds(Items.YELLOW_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_orange_banner"), itemsForEmeralds(Items.ORANGE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_brown_banner"), itemsForEmeralds(Items.BROWN_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_magenta_banner"), itemsForEmeralds(Items.MAGENTA_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_light_blue_banner"), itemsForEmeralds(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_pink_banner"), itemsForEmeralds(Items.PINK_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_gray_banner"), itemsForEmeralds(Items.GRAY_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_black_banner"), itemsForEmeralds(Items.BLACK_BANNER, 3, 1, 12, 15)),
            register(context, name(4, "sell_light_gray_banner"), itemsForEmeralds(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_globe_banner_pattern"), itemsForEmeralds(Items.GLOBE_BANNER_PATTERN, 8, 1, 12, 30))
        ));
    }

    private static TradeOffer map(int emeraldCost, TagKey<Structure> destination, String name, Holder<MapDecorationType> decoration, int maxUses, int xp) {
        var explorationFunction = new ExplorationMapFunction.Builder().setSearchRadius(100).setDestination(destination).setMapDecoration(decoration).build();
        var nameFunction = SetNameFunction.setName(Component.translatable(name), SetNameFunction.Target.ITEM_NAME).build();
        return new Base(
            new ItemCost(Items.EMERALD, emeraldCost),
            Optional.of(new ItemCost(Items.COMPASS)),
            new ItemStack(Items.MAP),
            Optional.of(SequenceFunction.of(List.of(explorationFunction, nameFunction))),
            maxUses,
            xp,
            0.2f
        );
    }

    private static String name(int level, String name) {
        return String.format("cartographer/%s/%s", LEVEL_TO_NAME.get(level - 1), name);
    }
}
