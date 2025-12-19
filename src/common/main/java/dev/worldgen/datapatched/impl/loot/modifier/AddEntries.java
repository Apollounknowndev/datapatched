package dev.worldgen.datapatched.impl.loot.modifier;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.mixin.loot.LootPoolAccessor;
import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

public record AddEntries(CommonData commonData, List<LootPoolEntryContainer> entries) implements LootModifier {
    public static final MapCodec<AddEntries> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        CommonData.codec(0).forGetter(AddEntries::commonData),
        LootPoolEntries.CODEC.listOf().fieldOf("entries").forGetter(AddEntries::entries)
    ).apply(instance, AddEntries::new));

    public void apply(LootTable table, Identifier key) {
        for(LootPool pool : this.accessor(table).datapatched$getPools()) {
            ImmutableList<LootPoolEntryContainer> baseEntries = ImmutableList.<LootPoolEntryContainer>builder().addAll(accessor(pool).datapatched$getEntries()).addAll(this.entries).build();
            accessor(pool).datapatched$setEntries(baseEntries);
        }
    }

    private static LootPoolAccessor accessor(LootPool pool) {
        return (LootPoolAccessor)pool;
    }

    public MapCodec<? extends LootModifier> codec() {
        return CODEC;
    }
}
