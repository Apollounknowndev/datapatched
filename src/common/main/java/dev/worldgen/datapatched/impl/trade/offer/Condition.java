package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public record Condition(LootItemCondition condition, Holder<TradeOffer> onTrue, Holder<TradeOffer> onFalse) implements TradeOffer {
    public static final MapCodec<Condition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        LootItemCondition.TYPED_CODEC.fieldOf("condition").forGetter(Condition::condition),
        TradeOffer.ENTRY_CODEC.fieldOf("on_true").orElse(EMPTY).forGetter(Condition::onTrue),
        TradeOffer.ENTRY_CODEC.fieldOf("on_false").orElse(EMPTY).forGetter(Condition::onFalse)
    ).apply(instance, Condition::new));

    public List<MerchantOffer> create(AbstractVillager entity, RandomSource random) {
        LootContext context = TradeOffer.createContext(entity);
        Holder<TradeOffer> trade = this.condition.test(context) ? this.onTrue : this.onFalse;
        return trade.value().create(entity, random);
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
