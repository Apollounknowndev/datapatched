package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.core.HolderSet;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;

public record Group(HolderSet<TradeOffer> trades) implements TradeOffer {
    public static final MapCodec<Group> CODEC = TradeOffer.LIST_CODEC.fieldOf("trades").xmap(Group::new, Group::trades);

    @Override
    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        List<MerchantOffer> offers = new ArrayList<>();
        this.trades.stream().map(entry -> entry.value().create(merchant, random)).forEach(offers::addAll);
        return offers;
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
