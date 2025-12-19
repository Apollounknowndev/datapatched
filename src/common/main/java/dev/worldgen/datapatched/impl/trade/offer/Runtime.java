package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.List;

public record Runtime(VillagerTrades.ItemListing listing) implements TradeOffer {
    @Override
    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        MerchantOffer offer = Datapatched.getOffer(listing, merchant, random);
        return offer == null ? List.of() : List.of(offer);
    }

    @Override
    public MapCodec<? extends TradeOffer> codec() {
        return null;
    }
}
