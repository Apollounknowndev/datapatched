package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.List;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

public record Random(HolderSet<TradeOffer> trades) implements TradeOffer {
    public static final MapCodec<Random> CODEC = TradeOffer.LIST_CODEC.fieldOf("trades").xmap(Random::new, Random::trades);

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        return this.trades.getRandomElement(random).map(entry -> entry.value().create(merchant, random)).orElse(List.of());
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
