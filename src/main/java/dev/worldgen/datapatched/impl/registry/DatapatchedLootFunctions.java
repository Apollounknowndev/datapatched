package dev.worldgen.datapatched.impl.registry;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.loot.function.Discard;
import dev.worldgen.datapatched.impl.loot.function.ItemSwap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

//? if < 26.1 {
/*import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
*///? }

public interface DatapatchedLootFunctions {
	static void init() {
		register("discard", Discard.CODEC);
		register("item_swap", ItemSwap.CODEC);
	}
	
	static void register(String name, MapCodec<? extends LootItemFunction> codec) {
		//? if < 26.1 {
		/*Datapatched.REGISTRAR.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, name, new LootItemFunctionType<>(codec));
		*///? } else {
		Datapatched.REGISTRAR.register(BuiltInRegistries.LOOT_FUNCTION_TYPE, name, codec);
		//? }
	}
}
