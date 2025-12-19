package dev.worldgen.datapatched.impl;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.VillagerType;

public class VillagerKeys {
    public static final ResourceKey<VillagerType> DESERT = type("desert");
    public static final ResourceKey<VillagerType> JUNGLE = type("jungle");
    public static final ResourceKey<VillagerType> PLAINS = type("plains");
    public static final ResourceKey<VillagerType> SAVANNA = type("savanna");
    public static final ResourceKey<VillagerType> SNOW = type("snow");
    public static final ResourceKey<VillagerType> SWAMP = type("swamp");
    public static final ResourceKey<VillagerType> TAIGA = type("taiga");

    private static ResourceKey<VillagerType> type(String $$0) {
        return ResourceKey.create(Registries.VILLAGER_TYPE, Identifier.withDefaultNamespace($$0));
    }
}
