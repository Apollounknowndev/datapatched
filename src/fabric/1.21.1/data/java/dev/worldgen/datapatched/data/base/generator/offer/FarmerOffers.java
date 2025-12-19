package dev.worldgen.datapatched.data.base.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOfferBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.SuspiciousStewEffects;

import java.util.ArrayList;
import java.util.List;

import static dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap.register;

public class FarmerOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of(
            register(context, name(1, "buy_wheat"), TradeOfferBuilder.emeraldsForItems(Items.WHEAT, 20, 16, 2)),
            register(context, name(1, "buy_potato"), TradeOfferBuilder.emeraldsForItems(Items.POTATO, 26, 16, 2)),
            register(context, name(1, "buy_carrot"), TradeOfferBuilder.emeraldsForItems(Items.CARROT, 22, 16, 2)),
            register(context, name(1, "buy_beetroot"), TradeOfferBuilder.emeraldsForItems(Items.BEETROOT, 15, 16, 2)),
            register(context, name(1, "sell_bread"), TradeOfferBuilder.itemsForEmeralds(Items.BREAD, 1, 6, 16, 1))
        ));
        OFFERS.add(List.of(
            register(context, name(2, "buy_pumpkin"), TradeOfferBuilder.emeraldsForItems(Items.PUMPKIN, 6, 12, 10)),
            register(context, name(2, "sell_pumpkin_pie"), TradeOfferBuilder.itemsForEmeralds(Items.PUMPKIN_PIE, 1, 4, 12, 5)),
            register(context, name(2, "sell_apple"), TradeOfferBuilder.itemsForEmeralds(Items.APPLE, 1, 4, 16, 5))
        ));
        OFFERS.add(List.of(
            register(context, name(3, "buy_melon"), TradeOfferBuilder.emeraldsForItems(Items.MELON, 4, 12, 20)),
            register(context, name(3, "sell_cookie"), TradeOfferBuilder.itemsForEmeralds(Items.COOKIE, 3, 18, 12, 10))
        ));
        OFFERS.add(List.of(
            register(context, name(4, "sell_cake"), TradeOfferBuilder.itemsForEmeralds(Items.COOKIE, 1, 1, 12, 15)),
            suspiciousStew(context, MobEffects.NIGHT_VISION, 100),
            suspiciousStew(context, MobEffects.JUMP, 160),
            suspiciousStew(context, MobEffects.WEAKNESS, 140),
            suspiciousStew(context, MobEffects.BLINDNESS, 120),
            suspiciousStew(context, MobEffects.POISON, 280),
            suspiciousStew(context, MobEffects.SATURATION, 7)
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_golden_carrot"), TradeOfferBuilder.itemsForEmeralds(Items.GOLDEN_CARROT, 3, 3, 12, 30)),
            register(context, name(5, "sell_glistering_melon_slice"), TradeOfferBuilder.itemsForEmeralds(Items.GLISTERING_MELON_SLICE, 4, 3, 12, 30))
        ));
    }

    private static ResourceKey<TradeOffer> suspiciousStew(BootstrapContext<TradeOffer> context, Holder<MobEffect> effect, int duration) {
        ItemStack stack = new ItemStack(Items.SUSPICIOUS_STEW, 1);
        stack.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, duration))));
        return register(
            context,
            name(4, "sell_suspicious_stew_" + effect.unwrapKey().get().identifier().getPath()),
            TradeOfferBuilder.itemsForEmeralds(stack, 1, 12, 15, 0.05f)
        );
    }

    private static String name(int level, String name) {
        return String.format("farmer/%s/%s", TradeOfferBuilder.LEVEL_TO_NAME.get(level - 1), name);
    }
}
