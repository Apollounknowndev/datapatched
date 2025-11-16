package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.List;

public record Runtime(VillagerTrades.ItemListing listing) implements TradeOffer {
    @Override
    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        MerchantOffer offer = listing.getOffer(merchant, random);
        return offer == null ? List.of() : List.of(offer);
    }

    @Override
    public MapCodec<? extends TradeOffer> codec() {
        return null;
    }
}
