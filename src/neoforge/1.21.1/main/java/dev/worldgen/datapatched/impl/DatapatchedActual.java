package dev.worldgen.datapatched.impl;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.msrandom.multiplatform.annotations.Actual;
import net.neoforged.fml.ModList;

import java.nio.file.Path;

import static dev.worldgen.datapatched.impl.Datapatched.MOD_ID;

public class DatapatchedActual {
    @Actual
    public static <T> Registry<T> registry(RegistryAccess registries, ResourceKey<? extends Registry<T>> key) {
        return registries.registryOrThrow(key);
    }

    @Actual
    public static Pack.ResourcesSupplier createTradeRebalanceSupplier() {
        Path resourcePath = ModList.get().getModFileById(MOD_ID).getFile().findResource("trade_rebalance");
        return new PathPackResources.PathResourcesSupplier(resourcePath);
    }
}
