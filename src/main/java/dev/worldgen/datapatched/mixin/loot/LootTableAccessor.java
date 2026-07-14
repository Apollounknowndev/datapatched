package dev.worldgen.datapatched.mixin.loot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.function.BiFunction;

@Mixin(LootTable.class)
public interface LootTableAccessor {
    @Accessor("pools") @Mutable
    List<LootPool> datapatched$getPools();

    @Accessor("pools") @Mutable
    void datapatched$setPools(List<LootPool> pools);

    @Accessor("functions") @Mutable
    List<LootItemFunction> datapatched$getFunctions();

    @Accessor("functions") @Mutable
    void datapatched$setFunctions(List<LootItemFunction> pools);

    @Accessor("compositeFunction") @Mutable
    void datapatched$setCompositeFunction(BiFunction<ItemStack, LootContext, ItemStack> compositeFunction);
}
