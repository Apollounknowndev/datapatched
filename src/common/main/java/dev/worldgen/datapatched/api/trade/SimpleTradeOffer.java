package dev.worldgen.datapatched.api.trade;

import java.util.List;
import java.util.Optional;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public interface SimpleTradeOffer extends TradeOffer {
    ItemTrade createItemTrade(AbstractVillager var1, RandomSource var2);

    int maxUses();

    int experience();

    float priceMultiplier();

    default List<MerchantOffer> create(AbstractVillager entity, RandomSource random) {
        ItemTrade trade = this.createItemTrade(entity, random);
        if (trade == null) {
            return null;
        } else {
            MerchantOffer offer = new MerchantOffer(trade.buying, trade.buyingSecondary, trade.selling, this.maxUses(), this.experience(), this.priceMultiplier());
            return List.of(offer);
        }
    }

    record ItemTrade(ItemCost buying, Optional<ItemCost> buyingSecondary, ItemStack selling) { }
}

