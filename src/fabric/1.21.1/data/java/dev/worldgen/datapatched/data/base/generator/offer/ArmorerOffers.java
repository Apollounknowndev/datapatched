package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class ArmorerOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_coal"), TradeOfferBuilder.emeraldsForItems(Items.COAL, 15, 16, 2)),
            register(context, name(1, "sell_iron_leggings"), TradeOfferBuilder.itemsForEmeralds(Items.IRON_LEGGINGS, 7, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_iron_boots"), TradeOfferBuilder.itemsForEmeralds(Items.IRON_BOOTS, 4, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_iron_helmet"), TradeOfferBuilder.itemsForEmeralds(Items.IRON_HELMET, 5, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_iron_chestplate"), TradeOfferBuilder.itemsForEmeralds(Items.IRON_CHESTPLATE, 9, 1, 12, 1, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_iron_ingot"), TradeOfferBuilder.emeraldsForItems(Items.IRON_INGOT, 4, 12, 10)),
            register(context, name(2, "sell_bell"), TradeOfferBuilder.itemsForEmeralds(Items.BELL, 36, 1, 12, 5, 0.2f)),
            register(context, name(2, "sell_chainmail_boots"), TradeOfferBuilder.itemsForEmeralds(Items.CHAINMAIL_BOOTS, 1, 1, 12, 5, 0.2f)),
            register(context, name(2, "sell_chainmail_leggings"), TradeOfferBuilder.itemsForEmeralds(Items.CHAINMAIL_LEGGINGS, 3, 1, 12, 5, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_lava_bucket"), TradeOfferBuilder.emeraldsForItems(Items.LAVA_BUCKET, 1, 12, 20)),
            register(context, name(3, "buy_diamond"), TradeOfferBuilder.emeraldsForItems(Items.DIAMOND, 1, 12, 20)),
            register(context, name(3, "sell_chainmail_helmet"), TradeOfferBuilder.itemsForEmeralds(Items.CHAINMAIL_HELMET, 1, 1, 12, 10, 0.2f)),
            register(context, name(3, "sell_chainmail_chestplate"), TradeOfferBuilder.itemsForEmeralds(Items.CHAINMAIL_CHESTPLATE, 4, 1, 12, 10, 0.2f)),
            register(context, name(3, "sell_shield"), TradeOfferBuilder.itemsForEmeralds(Items.SHIELD, 5, 1, 12, 10, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "sell_enchanted_diamond_leggings"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2f)),
            register(context, name(4, "sell_enchanted_diamond_boots"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_enchanted_diamond_helmet"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_HELMET, 8, 3, 30, 0.2f)),
            register(context, name(5, "sell_enchanted_diamond_chestplate"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2f))
        ));
    }

    private static String name(int level, String name) {
        return String.format("armorer/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
