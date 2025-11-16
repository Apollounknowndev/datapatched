package dev.worldgen.datapatched.data;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.data.overlay.OverlayRegistriesProvider;
import dev.worldgen.datapatched.data.overlay.OverlayTagsProvider;
import dev.worldgen.datapatched.data.overlay.generator.OverlayTradeOfferBootstrap;
import dev.worldgen.datapatched.data.overlay.generator.OverlayTradeOfferProviderBootstrap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.RegistrySetBuilder;

public class DatapatchedDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider((output, registriesFuture) -> new OverlayRegistriesProvider(putInOverlay(output), registriesFuture));
        pack.addProvider((output, registriesFuture) -> new OverlayTagsProvider(putInOverlay(output), registriesFuture));
    }

    public FabricDataOutput putInOverlay(FabricDataOutput output) {
        return new FabricDataOutput(output.getModContainer(), output.getOutputFolder().resolve("overlay"), output.isStrictValidationEnabled());
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(DatapatchedRegistries.TRADE_OFFER, OverlayTradeOfferBootstrap::bootstrap);
        builder.add(DatapatchedRegistries.TRADE_OFFER_PROVIDER, OverlayTradeOfferProviderBootstrap::bootstrap);
    }
}
