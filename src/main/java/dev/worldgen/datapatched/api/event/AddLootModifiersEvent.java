package dev.worldgen.datapatched.api.event;

import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.impl.event.DatapatchedEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;

import java.util.function.BiConsumer;

public interface AddLootModifiersEvent {
	DatapatchedEvent<AddLootModifiersEvent> EVENT = new DatapatchedEvent<>(callbacks -> ((ops, consumer) -> {
		for (AddLootModifiersEvent callback : callbacks) {
			callback.addLootModifiers(ops, consumer);
		}
	}));
	
	void addLootModifiers(RegistryOps<Object> ops, BiConsumer<Identifier, LootModifier> consumer);
}
