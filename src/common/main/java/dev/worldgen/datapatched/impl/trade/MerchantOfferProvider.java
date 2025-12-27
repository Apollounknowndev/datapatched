package dev.worldgen.datapatched.impl.trade;

import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;

public interface MerchantOfferProvider {
    MerchantOffer getOffer(LootContext lootContext);
}
