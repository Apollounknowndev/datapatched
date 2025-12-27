package dev.worldgen.datapatched.impl.loot.number;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.util.Mth;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

public record Sum(List<NumberProvider> summands) implements NumberProvider {
    public static final MapCodec<Sum> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        NumberProviders.CODEC.listOf().fieldOf("summands").forGetter(Sum::summands)
    ).apply(instance, Sum::new));
    public static final LootNumberProviderType TYPE = new LootNumberProviderType(CODEC);

    public static Sum sum(NumberProvider ... summands) {
        return new Sum(List.of(summands));
    }

    public MapCodec<Sum> codec() {
        return CODEC;
    }

    @Override
    public int getInt(LootContext context) {
        float value = 0.0f;
        for (NumberProvider provider : this.summands) {
            value += provider.getFloat(context);
        }
        return Mth.floor(value);
    }

    @Override
    public LootNumberProviderType getType() {
        return TYPE;
    }

    @Override
    public float getFloat(LootContext context) {
        float value = 0.0f;
        for (NumberProvider provider : this.summands) {
            value += provider.getFloat(context);
        }
        return value;
    }
}
