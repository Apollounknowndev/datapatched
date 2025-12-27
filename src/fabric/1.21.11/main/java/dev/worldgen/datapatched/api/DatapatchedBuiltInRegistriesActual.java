package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Actual;

public class DatapatchedBuiltInRegistriesActual {
    @Actual public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE = FabricRegistryBuilder.createSimple(DatapatchedRegistries.LOOT_MODIFIER_TYPE).buildAndRegister();
}
