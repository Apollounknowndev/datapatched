package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class WeaponsmithOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_coal"), TradeOfferBuilder.emeraldsForItems(Items.COAL, 15, 16, 2)),
            register(context, name(1, "sell_iron_axe"), TradeOfferBuilder.itemsForEmeralds(Items.IRON_AXE, 3, 1, 12, 1, 0.2f)),
            register(context, name(1, "sell_enchanted_iron_sword"), TradeOfferBuilder.enchantedItem(Items.IRON_SWORD, 2, 3, 1, 0.05f))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_iron_ingot"), TradeOfferBuilder.emeraldsForItems(Items.IRON_INGOT, 4, 12, 10)),
            register(context, name(2, "sell_bell"), TradeOfferBuilder.itemsForEmeralds(Items.BELL, 36, 1, 12, 5, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_flint"), TradeOfferBuilder.emeraldsForItems(Items.FLINT, 24, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_diamond"), TradeOfferBuilder.emeraldsForItems(Items.DIAMOND, 1, 12, 30)),
            register(context, name(4, "sell_enchanted_diamond_axe"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_AXE, 12, 3, 15, 0.2f))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_enchanted_diamond_sword"), TradeOfferBuilder.enchantedItem(Items.DIAMOND_SWORD, 8, 3, 30, 0.2f))
        ));
    }

    private static String name(int level, String name) {
        return String.format("weaponsmith/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
