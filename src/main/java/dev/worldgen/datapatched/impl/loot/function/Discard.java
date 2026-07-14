package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

//? if < 26.1 {
/*import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
*///? }

public class Discard extends LootItemConditionalFunction {
    public static final MapCodec<Discard> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).apply(instance, Discard::new));
    
    public Discard(List<LootItemCondition> conditions) {
        super(conditions);
    }
    
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return CODEC;
    }

    public ItemStack run(ItemStack stack, LootContext context) {
        return ItemStack.EMPTY;
    }
    
    //? if < 26.1 {
    /*public static final LootItemFunctionType<Discard> TYPE = new LootItemFunctionType<>(CODEC);
    
    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return TYPE;
    }
    *///? }
}
