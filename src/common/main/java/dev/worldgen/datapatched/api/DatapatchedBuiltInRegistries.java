package dev.worldgen.datapatched.api;

import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.loot.LootModifier;
import net.minecraft.core.Registry;
import net.msrandom.multiplatform.annotations.Expect;

public class DatapatchedBuiltInRegistries {
    @Expect public static final Registry<MapCodec<? extends LootModifier>> LOOT_MODIFIER_TYPE;

    public static void init() {

    }
}
