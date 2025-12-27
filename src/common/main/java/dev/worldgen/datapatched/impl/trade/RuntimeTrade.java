package dev.worldgen.datapatched.impl.trade;

import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;

public record RuntimeTrade(VillagerTrades.ItemListing listing) implements MerchantOfferProvider {
    @Override
    public MerchantOffer getOffer(LootContext context) {
        return Datapatched.getOffer(listing, context);
    }
}
