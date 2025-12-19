package dev.worldgen.datapatched.api.trade;

import dev.worldgen.datapatched.impl.trade.offer.Base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.worldgen.datapatched.impl.trade.offer.EnchantedItem;
import dev.worldgen.datapatched.impl.trade.offer.TypeSpecific;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;

public class TradeOfferBuilder {
    public static final List<String> LEVEL_TO_NAME = List.of("novice", "apprentice", "journeyman", "expert", "master");



    public static TradeOffer itemsForEmeralds(ItemLike selling, int emeraldCount, int sellingCount, int maxUses) {
        return itemsForEmeralds(selling, emeraldCount, sellingCount, maxUses, 1);
    }

    public static TradeOffer itemsForEmeralds(ItemLike selling, int emeraldCount, int sellingCount, int maxUses, int xp) {
        return itemsForEmeralds(selling, emeraldCount, sellingCount, maxUses, xp, 0.05f);
    }

    public static TradeOffer itemsForEmeralds(ItemLike selling, int emeraldCount, int sellingCount, int maxUses, int xp, float priceMultiplier) {
        return itemsForEmeralds(new ItemStack(selling, sellingCount), emeraldCount, maxUses, xp, priceMultiplier);
    }

    public static TradeOffer itemsForEmeralds(ItemStack selling, int emeraldCount, int maxUses, int xp, float priceMultiplier) {
        return new Base(new ItemCost(Items.EMERALD, emeraldCount), Optional.empty(), selling, Optional.empty(), maxUses, xp, priceMultiplier);
    }



    public static TradeOffer itemsAndEmeraldsForItems(ItemLike buying, int count, int emeraldCount, ItemLike selling, int maxUses, int xp) {
        return itemsAndEmeraldsForItems(new ItemCost(buying, count), emeraldCount, new ItemStack(selling, count), maxUses, xp);
    }

    public static TradeOffer itemsAndEmeraldsForItems(ItemCost buying, int emeraldCount, ItemStack selling, int maxUses, int xp) {
        return new Base(buying, Optional.of(new ItemCost(Items.EMERALD, emeraldCount)), selling, Optional.empty(), maxUses, xp, 0.05F);
    }



    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int maxUses, int xp) {
        return emeraldsForItems(new ItemCost(buying, buyingCount), 1, maxUses, xp);
    }

    public static TradeOffer emeraldsForItems(ItemLike buying, int buyingCount, int emeraldCount, int maxUses, int xp) {
        return emeraldsForItems(new ItemCost(buying, buyingCount), emeraldCount, maxUses, xp);
    }

    public static TradeOffer emeraldsForItems(ItemCost buying, int emeraldCount, int maxUses, int xp) {
        return new Base(buying, Optional.empty(), new ItemStack(Items.EMERALD, emeraldCount), Optional.empty(), maxUses, xp, 0.05F);
    }

    public static TradeOffer enchantedItem(ItemLike selling, int emeraldCount, int maxUses, int xp, float priceMultiplier) {
        return new EnchantedItem(new ItemCost(Items.EMERALD, emeraldCount), selling, maxUses, xp, priceMultiplier);
    }

    @SafeVarargs
    public static TradeOffer typeSpecific(TradeOffer offer, ResourceKey<VillagerType>... types) {
        return new TypeSpecific(TypeSpecific.entry(offer, types));
    }
}