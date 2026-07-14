package dev.worldgen.datapatched.impl.loot.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.loot.modifier.CommonModifierData;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.mixin.loot.LootTableAccessor;
import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;

public record ApplyFunction(CommonModifierData commonData, LootItemFunction function) implements LootModifier {
    public static final MapCodec<ApplyFunction> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(CommonModifierData.codec(2000).forGetter(ApplyFunction::commonData), LootItemFunctions.ROOT_CODEC.fieldOf("function").forGetter(ApplyFunction::function)).apply(instance, ApplyFunction::new));

    public void apply(LootTable table, Identifier key) {
        LootTableAccessor accessor = this.accessor(table);
        List<LootItemFunction> fullFunctions = ImmutableList.<LootItemFunction>builder().addAll(accessor.datapatched$getFunctions()).add(this.function).build();
        accessor.datapatched$setFunctions(fullFunctions);
        accessor.datapatched$setCompositeFunction(LootItemFunctions.compose(fullFunctions));
    }

    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }
}
