package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class NewFiltered extends LootItemConditionalFunction {
    public static final MapCodec<NewFiltered> CODEC = RecordCodecBuilder.mapCodec(i -> NewFiltered.commonFields(i).and(i.group(
        ItemPredicate.CODEC.fieldOf("item_filter").forGetter(f -> f.filter),
        LootItemFunctions.ROOT_CODEC.optionalFieldOf("on_pass").forGetter(f -> f.onPass),
        LootItemFunctions.ROOT_CODEC.optionalFieldOf("on_fail").forGetter(f -> f.onFail))
    ).apply(i, NewFiltered::new));
    public static final LootItemFunctionType<NewFiltered> TYPE = new LootItemFunctionType<>(CODEC);

    private final ItemPredicate filter;
    private final Optional<LootItemFunction> onPass;
    private final Optional<LootItemFunction> onFail;

    private NewFiltered(List<LootItemCondition> predicates, ItemPredicate filter, Optional<LootItemFunction> onPass, Optional<LootItemFunction> onFail) {
        super(predicates);
        this.filter = filter;
        this.onPass = onPass;
        this.onFail = onFail;
    }

    public MapCodec<NewFiltered> codec() {
        return CODEC;
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack itemStack, LootContext context) {
        Optional<LootItemFunction> function = this.filter.test(itemStack) ? this.onPass : this.onFail;
        if (function.isPresent()) {
            return function.get().apply(itemStack, context);
        }
        return itemStack;
    }
}
