package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

import java.util.ArrayList;
import java.util.List;

public class SetRandomDyes extends LootItemConditionalFunction {
    public static final MapCodec<SetRandomDyes> CODEC = RecordCodecBuilder.mapCodec(instance -> SetRandomDyes.commonFields(instance).and(
        NumberProviders.CODEC.fieldOf("number_of_dyes").forGetter(f -> f.numberOfDyes)
    ).apply(instance, SetRandomDyes::new));
    public static final LootItemFunctionType<SetRandomDyes> TYPE = new LootItemFunctionType<>(CODEC);
    private final NumberProvider numberOfDyes;

    private SetRandomDyes(List<LootItemCondition> predicates, NumberProvider numberOfDyes) {
        super(predicates);
        this.numberOfDyes = numberOfDyes;
    }

    public MapCodec<SetRandomDyes> codec() {
        return CODEC;
    }

    @Override
    public LootItemFunctionType<SetRandomDyes> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        if (stack.is(ItemTags.DYEABLE)) {
            RandomSource random = context.getRandom();
            int rolls = this.numberOfDyes.getInt(context);
            if (rolls <= 0) {
                return stack;
            }
            ArrayList<DyeItem> dyes = new ArrayList<DyeItem>(rolls);
            for (int i = 0; i < rolls; ++i) {
                dyes.add(DyeItem.byColor(Util.getRandom(DyeColor.values(), random)));
            }
            return DyedItemColor.applyDyes(stack, dyes);
        }
        return stack;
    }
}

