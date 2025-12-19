package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.trade.TradeHelper;

import java.util.Arrays;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.npc.villager.VillagerDataHolder;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.trading.MerchantOffer;

public record TypeSpecific(List<Entry> entries, Holder<TradeOffer> fallback) implements TradeOffer {
    public static final MapCodec<TypeSpecific> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Entry.CODEC.listOf().fieldOf("entries").forGetter(TypeSpecific::entries),
        TradeOffer.ENTRY_CODEC.fieldOf("fallback").orElse(EMPTY).forGetter(TypeSpecific::fallback)
    ).apply(instance, TypeSpecific::new));

    public TypeSpecific(Entry... entries) {
        this(Arrays.stream(entries).toList(), EMPTY);
    }

    public List<MerchantOffer> create(AbstractVillager merchant, RandomSource random) {
        if (merchant instanceof VillagerDataHolder container) {
            ResourceKey<VillagerType> key = ResourceKey.create(Registries.VILLAGER_TYPE, TradeHelper.getType(container.getVillagerData()));
            for (Entry entry : this.entries) {
                if (entry.types().contains(key)) {
                    return entry.trade().value().create(merchant, random);
                }
            }
        }
        return this.fallback.value().create(merchant, random);
    }

    @SafeVarargs
    public static Entry entry(TradeOffer offer, ResourceKey<VillagerType>... types) {
        return new Entry(Holder.direct(offer), Arrays.stream(types).toList());
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }

    public record Entry(Holder<TradeOffer> trade, List<ResourceKey<VillagerType>> types) {
        private static final Codec<ResourceKey<VillagerType>> TYPE_CODEC = ResourceKey.codec(Registries.VILLAGER_TYPE);
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TradeOffer.ENTRY_CODEC.fieldOf("trade").orElse(EMPTY).forGetter(Entry::trade),
            Codec.withAlternative(TYPE_CODEC.listOf(), TYPE_CODEC, List::of).fieldOf("types").forGetter(Entry::types)
        ).apply(instance, Entry::new));
    }
}
