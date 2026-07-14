package dev.worldgen.datapatched.impl;

import dev.worldgen.apollib.Apollib;
import dev.worldgen.apollib.registry.ApollibRegistrar;

import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.impl.registry.DatapatchedLootFunctions;
import dev.worldgen.datapatched.impl.registry.DatapatchedLootModifiers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Datapatched {
    public static final String MOD_ID = "datapatched";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ApollibRegistrar REGISTRAR = Apollib.createRegistrar(MOD_ID);
    
    public static void init() {
        DatapatchedBuiltInRegistries.init();
        DatapatchedLootFunctions.init();
        DatapatchedLootModifiers.init();
    }

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }

    public static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String name) {
        return ResourceKey.create(registry, id(name));
    }
}