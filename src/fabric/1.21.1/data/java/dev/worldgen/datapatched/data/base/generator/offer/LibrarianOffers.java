package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import dev.worldgen.datapatched.impl.trade.offer.EnchantedBook;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.ItemCost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class LibrarianOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_paper"), TradeOfferBuilder.emeraldsForItems(Items.STICK, 24, 16, 2)),
            register(context, name(1, "sell_enchanted_book"), book(context, 1)),
            register(context, name(1, "sell_bookshelf"), TradeOfferBuilder.itemsForEmeralds(Items.BOOKSHELF, 9, 1, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_book"), TradeOfferBuilder.emeraldsForItems(Items.BOOK, 4, 12, 10)),
            register(context, name(2, "sell_enchanted_book"), book(context, 5)),
            register(context, name(2, "sell_lantern"), TradeOfferBuilder.itemsForEmeralds(Items.LANTERN, 1, 1, 12, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_ink_sac"), TradeOfferBuilder.emeraldsForItems(Items.INK_SAC, 5, 12, 20)),
            register(context, name(3, "sell_enchanted_book"), book(context, 10)),
            register(context, name(3, "sell_glass"), TradeOfferBuilder.itemsForEmeralds(Items.GLASS, 1, 4, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_writable_book"), TradeOfferBuilder.emeraldsForItems(Items.WRITABLE_BOOK, 2, 12, 30)),
            register(context, name(4, "sell_enchanted_book"), book(context, 15)),
            register(context, name(4, "sell_clock"), TradeOfferBuilder.itemsForEmeralds(Items.CLOCK, 5, 1, 12, 15)),
            register(context, name(4, "sell_compass"), TradeOfferBuilder.itemsForEmeralds(Items.COMPASS, 4, 1, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_name_tag"), TradeOfferBuilder.itemsForEmeralds(Items.NAME_TAG, 20, 1, 12, 30)),
            register(context, name(5, "sell_enchanted_book"), new Empty()) // Trade Rebalance fills this
        ));
    }

    private static TradeOffer book(BootstrapContext<TradeOffer> context, int xp) {
        HolderSet.Named<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT).get(EnchantmentTags.TRADEABLE).get();
        return new EnchantedBook(Optional.of(new ItemCost(Items.BOOK)), enchantments, 1, 255, 12, xp, 0.2f);
    }

    private static String name(int level, String name) {
        return String.format("librarian/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
