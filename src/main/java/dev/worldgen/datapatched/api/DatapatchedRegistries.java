package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public interface DatapatchedRegistries {
    ResourceKey<Registry<MapCodec<? extends LootModifier>>> LOOT_MODIFIER_TYPE = key(Datapatched.id("loot_modifier_type"));
    ResourceKey<Registry<LootModifier>> LOOT_MODIFIER = key(Datapatched.id("loot_modifier"));

    static <T> ResourceKey<Registry<T>> key(Identifier id) {
        return ResourceKey.createRegistryKey(id);
    }
}