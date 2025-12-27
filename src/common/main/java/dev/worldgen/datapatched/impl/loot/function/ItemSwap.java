package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public final class ItemSwap extends LootItemConditionalFunction {
    public static final MapCodec<ItemSwap> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).and(
        Codec.unboundedMap(ResourceKey.codec(Registries.ITEM), ResourceKey.codec(Registries.ITEM)).fieldOf("items").forGetter(ItemSwap::items)
    ).apply(instance, ItemSwap::new));
    public static final LootItemFunctionType<ItemSwap> TYPE = new LootItemFunctionType<>(CODEC);
    private final Map<ResourceKey<Item>, ResourceKey<Item>> items;

    public ItemSwap(List<LootItemCondition> conditions, Map<ResourceKey<Item>, ResourceKey<Item>> items) {
        super(conditions);
        this.items = items;
    }

    public LootItemFunctionType<ItemSwap> getType() {
        return TYPE;
    }

    public ItemStack run(ItemStack stack, LootContext context) {
        HolderLookup.RegistryLookup<Item> registry = context.getLevel().registryAccess().lookupOrThrow(Registries.ITEM);
        ResourceKey<Item> key = stack.getItemHolder().unwrapKey().get();
        if (this.items.containsKey(key)) {
            Optional<Holder.Reference<Item>> value = registry.get(this.items.get(key));
            if (value.isPresent()) {
                return stack.transmuteCopy(value.get().value());
            }
        }

        return stack;
    }

    public Map<ResourceKey<Item>, ResourceKey<Item>> items() {
        return this.items;
    }
}
