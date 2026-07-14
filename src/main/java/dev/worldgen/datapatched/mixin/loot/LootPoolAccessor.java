package dev.worldgen.datapatched.mixin.loot;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LootPool.class)
public interface LootPoolAccessor {
    @Accessor("entries") @Mutable
    List<LootPoolEntryContainer> datapatched$getEntries();

    @Accessor("entries") @Mutable
    void datapatched$setEntries(List<LootPoolEntryContainer> pools);
}
