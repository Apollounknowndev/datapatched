package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

//? if fabric {
/*import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
*///? } else {
import dev.worldgen.datapatched.impl.Datapatched;
import net.neoforged.neoforge.registries.DeferredRegister;
import dev.worldgen.datapatched.impl.platform.neoforge.DatapatchedNeoforge;
//? }

public interface DatapatchedBuiltInRegistries {
    Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE = createRegistry(DatapatchedRegistries.LOOT_MODIFIER_TYPE);

    static void init() {
    }
    
    static <T> Registry<T> createRegistry(ResourceKey<Registry<T>> key) {
        //? if fabric {
        /*return FabricRegistryBuilder.create(key).buildAndRegister();
        *///? } else {
        var registry = DeferredRegister.create(key, Datapatched.MOD_ID).makeRegistry(b -> {});
        DatapatchedNeoforge.REGISTRIES.add(registry);
        return registry;
        //? }
    }
}
