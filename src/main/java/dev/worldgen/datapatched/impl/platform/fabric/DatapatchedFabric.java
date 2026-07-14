//? if fabric {
/*package dev.worldgen.datapatched.impl.platform.fabric;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.event.AddLootModifiersEvent;
import dev.worldgen.datapatched.api.loot.modifier.CommonModifierData;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.loot.modifier.ApplyFunction;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.functions.SetLoreFunction;

public class DatapatchedFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		Datapatched.init();
		Datapatched.REGISTRAR.registerAll();
		
		DynamicRegistries.register(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
	}
}
*///? }