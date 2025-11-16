package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import dev.worldgen.datapatched.impl.loot.function.ApplyDyesFunction;
import dev.worldgen.datapatched.impl.trade.offer.Base;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;
import static dev.worldgen.datapatched.impl.loot.function.ApplyDyesFunction.entry;

public class LeatherworkerOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_leather"), TradeOfferBuilder.emeraldsForItems(Items.LEATHER, 6, 16, 2)),
            register(context, name(1, "sell_leather_leggings"), dyed(Items.LEATHER_LEGGINGS, 3, 12, 1)),
            register(context, name(1, "sell_leather_chestplate"), dyed(Items.LEATHER_CHESTPLATE, 7, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_flint"), TradeOfferBuilder.emeraldsForItems(Items.FLINT, 26, 12, 10)),
            register(context, name(2, "sell_leather_helmet"), dyed(Items.LEATHER_HELMET, 5, 12, 5)),
            register(context, name(2, "sell_leather_boots"), dyed(Items.LEATHER_BOOTS, 4, 12, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_rabbit_hide"), TradeOfferBuilder.emeraldsForItems(Items.RABBIT_HIDE, 9, 12, 20)),
            register(context, name(3, "sell_leather_chestplate"), dyed(Items.LEATHER_CHESTPLATE, 7, 12, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "buy_turtle_scute"), TradeOfferBuilder.emeraldsForItems(Items.TURTLE_SCUTE, 4, 12, 30)),
            register(context, name(4, "sell_leather_horse_armor"), dyed(Items.LEATHER_HORSE_ARMOR, 6, 12, 15))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_saddle"), TradeOfferBuilder.itemsForEmeralds(Items.SADDLE, 6, 1, 12, 30, 0.2f)),
            register(context, name(5, "sell_leather_helmet"), dyed(Items.LEATHER_HELMET, 5, 12, 30))
        ));
    }

    private static TradeOffer dyed(ItemLike item, int emeraldCount, int maxUses, int xp) {
        return new Base(
            new ItemCost(Items.EMERALD, emeraldCount),
            Optional.empty(),
            new ItemStack(item),
            Optional.of(new ApplyDyesFunction(List.of(), List.of(entry(1.0f), entry(0.8f), entry(0.7f)))),
            maxUses,
            xp,
            0.2f
        );
    }

    private static String name(int level, String name) {
        return String.format("leatherworker/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
