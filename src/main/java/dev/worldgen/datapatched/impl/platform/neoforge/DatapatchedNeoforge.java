//? if neoforge {
package dev.worldgen.datapatched.impl.platform.neoforge;

import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(Datapatched.MOD_ID)
public class DatapatchedNeoforge {
	public static final List<Registry<?>> REGISTRIES = new ArrayList<>();
	
	public DatapatchedNeoforge(IEventBus bus) {
		Datapatched.init();
		Datapatched.REGISTRAR.registerAll();
		
		bus.addListener((DataPackRegistryEvent.NewRegistry event) -> {
			event.dataPackRegistry(DatapatchedRegistries.LOOT_MODIFIER, LootModifier.CODEC);
		});
		bus.addListener(DatapatchedNeoforge::registerRegistries);
	}
	
	public static void registerRegistries(NewRegistryEvent event) {
		for (Registry<?> registry : REGISTRIES) {
			event.register(registry);
		}
	}
}
//? }