package dev.worldgen.datapatched.impl;

import net.minecraft.server.packs.CompositePackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.jarcontents.JarContents;
import net.neoforged.neoforge.resource.JarContentsPackResources;
import net.neoforged.neoforgespi.language.IModInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static dev.worldgen.datapatched.impl.Datapatched.MOD_ID;

public record DatapatchedResourceSupplier(BiFunction<PackLocationInfo, String, PackResources> resourceGetter) implements Pack.ResourcesSupplier {
    public static DatapatchedResourceSupplier create() {
        IModInfo modInfo = ModList.get().getModContainerById(MOD_ID).orElseThrow(() -> new IllegalArgumentException("Mod not found: " + MOD_ID)).getModInfo();
        BiFunction<PackLocationInfo, String, PackResources> resourceGetter = (info, prefix) -> {
            JarContents contents = modInfo.getOwningFile().getFile().getContents();
            return new JarContentsPackResources(info, contents, prefix);
        };
        return new DatapatchedResourceSupplier(resourceGetter);
    }

    @Override
    public PackResources openPrimary(PackLocationInfo info) {
        return resourceGetter.apply(info, "trade_rebalance");
    }

    @Override
    public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
        PackResources baseResources = resourceGetter.apply(info, "trade_rebalance");
        List<String> overlays = metadata.overlays();
        if (overlays.isEmpty()) {
            return baseResources;
        } else {
            List<PackResources> effectiveOverlays = new ArrayList(overlays.size());

            for(String s : overlays) {
                effectiveOverlays.add(resourceGetter.apply(info, "trade_rebalance/" + s));
            }

            return new CompositePackResources(baseResources, effectiveOverlays);
        }
    }
}
