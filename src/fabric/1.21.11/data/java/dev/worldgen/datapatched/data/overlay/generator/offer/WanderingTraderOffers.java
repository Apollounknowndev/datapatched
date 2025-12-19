package dev.worldgen.datapatched.data.overlay.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.overlay.generator.OverlayTradeOfferBootstrap;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.overlay.generator.OverlayTradeOfferBootstrap.key;

public class WanderingTraderOffers {
    public static final List<ResourceKey<TradeOffer>> NORMAL_OVERLAY_OFFERS = new ArrayList<>();
    public static final List<ResourceKey<TradeOffer>> SPECIAL_OVERLAY_OFFERS = new ArrayList<>();
    public static final List<ResourceKey<TradeOffer>> BUYING_OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        normal(context, Items.VINE, 1, 3, 12, false);
        normal(context, Items.BROWN_MUSHROOM, 1, 3, 12, false);
        normal(context, Items.RED_MUSHROOM, 1, 3, 12, false);
        normal(context, Items.LILY_PAD, 1, 5, 5, false);
        normal(context, Items.TROPICAL_FISH_BUCKET, 3, 1, 4, true);
        normal(context, Items.PUFFERFISH_BUCKET, 3, 1, 4, true);

        context.register(baseKey("special/sell_", Items.TROPICAL_FISH_BUCKET), new Empty());
        context.register(baseKey("special/sell_", Items.PUFFERFISH_BUCKET), new Empty());
        special(context, Items.PACKED_ICE, 1, 1, 6, false);
        special(context, Items.GUNPOWDER, 1, 4, 8, false);
        special(context, Items.ACACIA_LOG, 1, 8, 4, true);
        special(context, Items.BIRCH_LOG, 1, 8, 4, true);
        special(context, Items.CHERRY_LOG, 1, 8, 4, true);
        special(context, Items.DARK_OAK_LOG, 1, 8, 4, true);
        special(context, Items.JUNGLE_LOG, 1, 8, 4, true);
        special(context, Items.MANGROVE_LOG, 1, 8, 4, true);
        special(context, Items.OAK_LOG, 1, 8, 4, true);
        special(context, Items.PALE_OAK_LOG, 1, 8, 4, true);
        special(context, Items.SPRUCE_LOG, 1, 8, 4, true);
        special(context, PotionContents.createItemStack(Items.POTION, Potions.LONG_INVISIBILITY), 5, 1, 1, true);

        var enchantedKey = baseKey("special/sell_enchanted_", Items.IRON_PICKAXE);
        context.register(enchantedKey, TradeOfferBuilder.enchantedItem(Items.IRON_PICKAXE, 1, 1, 1, 0.05f));
        SPECIAL_OVERLAY_OFFERS.add(enchantedKey);

        buying(context, new ItemCost(Items.POTION).withComponents(builder -> builder.expect(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER))), 1, 2);
        buying(context, new ItemCost(Items.WATER_BUCKET), 2, 2);
        buying(context, new ItemCost(Items.MILK_BUCKET), 2, 2);
        buying(context, new ItemCost(Items.FERMENTED_SPIDER_EYE), 3, 2);
        buying(context, new ItemCost(Items.BAKED_POTATO, 4), 1, 2);
        buying(context, new ItemCost(Items.HAY_BLOCK), 1, 2);
    }

    private static void normal(BootstrapContext<TradeOffer> context, ItemLike item, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        var key = baseKey("normal/sell_", item);
        context.register(key, TradeOfferBuilder.itemsForEmeralds(item, buyingCount, sellingCount, maxUses));
        if (addToTag) NORMAL_OVERLAY_OFFERS.add(key);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemLike item, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        special(context, new ItemStack(item, sellingCount), buyingCount, sellingCount, maxUses, addToTag);
    }

    private static void special(BootstrapContext<TradeOffer> context, ItemStack stack, int buyingCount, int sellingCount, int maxUses, boolean addToTag) {
        var key = baseKey("special/sell_", stack.getItem());
        context.register(key, TradeOfferBuilder.itemsForEmeralds(stack, buyingCount, sellingCount, maxUses, 0.05f));
        if (addToTag) SPECIAL_OVERLAY_OFFERS.add(key);
    }

    private static void buying(BootstrapContext<TradeOffer> context, ItemCost cost, int emeralds, int maxUses) {
        var key = baseKey("buying/buy_", cost.item().value());
        context.register(key, TradeOfferBuilder.emeraldsForItems(cost, emeralds, maxUses, 1));
        BUYING_OFFERS.add(key);
    }

    private static ResourceKey<TradeOffer> baseKey(String prefix, ItemLike item) {
        return key("wandering_trader/" + prefix + OverlayTradeOfferBootstrap.itemPath(item));
    }
}
