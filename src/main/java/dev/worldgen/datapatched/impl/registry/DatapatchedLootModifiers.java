package dev.worldgen.datapatched.impl.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.loot.modifier.AddEntries;
import dev.worldgen.datapatched.impl.loot.modifier.AddPools;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;

public interface DatapatchedLootModifiers {
	static void init() {
		register("add_entries", AddEntries.CODEC);
		register("add_pools", AddPools.CODEC);
		register("apply_function", ApplyFunction.CODEC);
	}
	
	static void register(String name, MapCodec<? extends LootModifier> codec) {
		Datapatched.REGISTRAR.register(DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE, name, codec);
	}
}
