package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.data.base.BaseTagsProvider;
import dev.worldgen.datapatched.impl.trade.offer.Random;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.trading.ItemCost;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class FletcherOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();
    public static final List<ResourceKey<TradeOffer>> TIPPED_ARROWS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_stick"), TradeOfferBuilder.emeraldsForItems(Items.STICK, 32, 16, 2)),
            register(context, name(1, "sell_arrow"), TradeOfferBuilder.itemsForEmeralds(Items.ARROW, 1, 16, 12, 1)),
            register(context, name(1, "break_gravel"), TradeOfferBuilder.itemsAndEmeraldsForItems(Items.GRAVEL, 10, 1, Items.FLINT, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_flint"), TradeOfferBuilder.emeraldsForItems(Items.FLINT, 26, 12, 10)),
            register(context, name(2, "sell_bow"), TradeOfferBuilder.itemsForEmeralds(Items.BOW, 2, 1, 12, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_string"), TradeOfferBuilder.emeraldsForItems(Items.STRING, 14, 16, 20)),
            register(context, name(3, "sell_crossbow"), TradeOfferBuilder.itemsForEmeralds(Items.CROSSBOW, 3, 1, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_feather"), TradeOfferBuilder.emeraldsForItems(Items.FEATHER, 24, 16, 30)),
            register(context, name(4, "sell_enchanted_bow"), TradeOfferBuilder.enchantedItem(Items.BOW, 2, 3, 15, 0.05f))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "buy_tripwire_hook"), TradeOfferBuilder.emeraldsForItems(Items.TRIPWIRE_HOOK, 8, 12, 30)),
            register(context, name(5, "sell_enchanted_crossbow"), TradeOfferBuilder.enchantedItem(Items.BOW, 3, 3, 15, 0.05f)),
            register(context, name(5, "sell_tipped_arrow"), new Random(context.lookup(DatapatchedRegistries.TRADE_OFFER).get(BaseTagsProvider.key("fletcher/tipped_arrow")).get()))
        ));

        for (Holder.Reference<Potion> potion : getPotions()) {
            ItemStack stack = new ItemStack(Items.TIPPED_ARROW, 5);
            stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
            TIPPED_ARROWS.add(register(context, name(5, "tipped_arrow/" + potion.key().identifier().getPath()), TradeOfferBuilder.itemsAndEmeraldsForItems(new ItemCost(Items.ARROW, 5), 2, stack, 12, 30)));
        }
    }

    private static List<Holder.Reference<Potion>> getPotions() {
        return BuiltInRegistries.POTION.holders().filter(holder ->
            !holder.value().getEffects().isEmpty() && PotionBrewing.bootstrap(FeatureFlags.VANILLA_SET).isBrewablePotion(holder)
        ).toList();
    }

    private static String name(int level, String name) {
        return String.format("fletcher/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
