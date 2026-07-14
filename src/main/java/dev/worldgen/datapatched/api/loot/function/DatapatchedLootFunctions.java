package dev.worldgen.datapatched.api.loot.function;

import dev.worldgen.datapatched.impl.loot.function.Discard;
import dev.worldgen.datapatched.impl.loot.function.ItemSwap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Map;

public class DatapatchedLootFunctions {
	static LootItemFunction discard(LootItemCondition... conditions) {
		return new Discard(List.of(conditions));
	}
	
	static LootItemFunction itemSwap(Map<ResourceKey<Item>, ResourceKey<Item>> items, LootItemCondition... conditions) {
		return new ItemSwap(List.of(conditions), items);
	}
}
