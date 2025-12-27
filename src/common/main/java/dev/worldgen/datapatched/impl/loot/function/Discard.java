package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class Discard extends LootItemConditionalFunction {
    public static final MapCodec<Discard> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, Discard::new));
    public static final LootItemFunctionType<Discard> TYPE = new LootItemFunctionType<>(CODEC);

    protected Discard(List<LootItemCondition> conditions) {
        super(conditions);
    }

    public LootItemFunctionType<Discard> getType() {
        return TYPE;
    }

    public ItemStack run(ItemStack stack, LootContext context) {
        return ItemStack.EMPTY;
    }
}
