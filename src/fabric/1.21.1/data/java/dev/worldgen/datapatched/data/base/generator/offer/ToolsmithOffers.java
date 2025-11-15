package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class ToolsmithOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_coal"), TradeOfferBuilder.emeraldsForItems(Items.COAL, 15, 16, 2)),
            register(context, name(1, "sell_stone_axe"), TradeOfferBuilder.itemsForEmeralds(Items.STONE_AXE, 1, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_stone_shovel"), TradeOfferBuilder.itemsForEmeralds(Items.STONE_SHOVEL, 1, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_stone_pickaxe"), TradeOfferBuilder.itemsForEmeralds(Items.STONE_PICKAXE, 1, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_stone_hoe"), TradeOfferBuilder.itemsForEmeralds(Items.STONE_HOE, 1, 1, 12, 1, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_iron_ingot"), TradeOfferBuilder.emeraldsForItems(Items.IRON_INGOT, 4, 12, 10)),
            register(context, name(2, "sell_bell"), TradeOfferBuilder.itemsForEmeralds(Items.BELL, 36, 1, 12, 5, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_flint"), TradeOfferBuilder.emeraldsForItems(Items.FLINT, 30, 12, 10)),
            register(context, name(3, "sell_enchanted_stone_axe"), TradeOfferBuilder.enchantedItem(Items.STONE_AXE, 1, 3, 10, 0.2f)),
            register(context, name(3, "sell_enchanted_stone_shovel"), TradeOfferBuilder.enchantedItem(Items.STONE_SHOVEL, 2, 3, 10, 0.2f)),
            register(context, name(3, "sell_enchanted_stone_pickaxe"), TradeOfferBuilder.enchantedItem(Items.STONE_PICKAXE, 3, 3, 10, 0.2f)),
            register(context, name(3, "sell_diamond_hoe"), TradeOfferBuilder.itemsForEmeralds(Items.DIAMOND_HOE, 4, 1, 3, 10, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_diamond"), TradeOfferBuilder.emeraldsForItems(Items.DIAMOND, 1, 12, 10)),
            register(context, name(4, "sell_enchanted_diamond_axe"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_AXE, 12, 3, 15, 0.2f)),
            register(context, name(4, "sell_enchanted_diamond_shovel"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_enchanted_diamond_pickaxe"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2f))
        ));
    }

    private static String name(int level, String name) {
        return String.format("toolsmith/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
