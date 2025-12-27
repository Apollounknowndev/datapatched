package dev.worldgen.datapatched.impl.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

public record TradeCost(Holder<Item> item, NumberProvider count, DataComponentExactPredicate components) {
    private static final Codec<Holder<Item>> ITEM_CODEC = BuiltInRegistries.ITEM.holderByNameCodec().validate(item -> item.is(Items.AIR.builtInRegistryHolder()) ? DataResult.error(() -> "Item must not be minecraft:air") : DataResult.success(item));

    public static final Codec<TradeCost> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        ITEM_CODEC.fieldOf("id").forGetter(TradeCost::item),
        NumberProviders.CODEC.optionalFieldOf("count", ConstantValue.exactly(1.0f)).forGetter(TradeCost::count),
        DataComponentExactPredicate.CODEC.optionalFieldOf("components", DataComponentExactPredicate.EMPTY).forGetter(TradeCost::components)
    ).apply(instance, TradeCost::new));

    public TradeCost(ItemLike item, int count) {
        this(item.asItem().builtInRegistryHolder(), ConstantValue.exactly(count), DataComponentExactPredicate.EMPTY);
    }

    public TradeCost(ItemLike item, NumberProvider count) {
        this(item.asItem().builtInRegistryHolder(), count, DataComponentExactPredicate.EMPTY);
    }

    public ItemCost toItemCost(LootContext lootContext, int additionalCost) {
        int count = Mth.clamp(this.count().getInt(lootContext) + additionalCost, 0, this.item().value().getDefaultMaxStackSize());
        return new ItemCost(this.item(), count, this.components());
    }
}

