package dev.worldgen.datapatched.api.loot.modifier;

import dev.worldgen.datapatched.impl.loot.modifier.AddEntries;
import dev.worldgen.datapatched.impl.loot.modifier.AddPools;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

import java.util.List;
import java.util.function.Predicate;

public class DatapatchedLootModifiers {
	static LootModifier addEntries(Predicate<Identifier> target, LootPoolEntryContainer... entries) {
		return new AddEntries(new CommonModifierData(target, 0), List.of(entries));
	}
	
	static LootModifier addEntries(Predicate<Identifier> target, int priority, LootPoolEntryContainer... entries) {
		return new AddEntries(new CommonModifierData(target, priority), List.of(entries));
	}
	
	static LootModifier addPools(Predicate<Identifier> target, LootPool... pools) {
		return new AddPools(new CommonModifierData(target, 1000), List.of(pools));
	}
	
	static LootModifier addPools(Predicate<Identifier> target, int priority, LootPool... pools) {
		return new AddPools(new CommonModifierData(target, priority), List.of(pools));
	}
	
	static LootModifier applyFunction(Predicate<Identifier> target, LootItemFunction function) {
		return new ApplyFunction(new CommonModifierData(target, 2000), function);
	}
	
	static LootModifier applyFunction(Predicate<Identifier> target, int priority, LootItemFunction function) {
		return new ApplyFunction(new CommonModifierData(target, priority), function);
	}
}
