package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.List;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

public record Empty() implements TradeOffer {
    public static final MapCodec<Empty> CODEC = MapCodec.unit(Empty::new);

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        return List.of();
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
