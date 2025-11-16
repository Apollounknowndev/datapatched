package dev.worldgen.datapatched.data;

import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.data.base.BaseRegistriesProvider;
import dev.worldgen.datapatched.data.base.BaseTagsProvider;
import dev.worldgen.datapatched.data.base.generator.BaseTradeOfferBootstrap;
import dev.worldgen.datapatched.data.base.generator.BaseTradeOfferProviderBootstrap;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class DatapatchedDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(BaseRegistriesProvider::new);
        pack.addProvider(BaseTagsProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(DatapatchedRegistries.TRADE_OFFER, BaseTradeOfferBootstrap::bootstrap);
        builder.add(DatapatchedRegistries.TRADE_OFFER_PROVIDER, BaseTradeOfferProviderBootstrap::bootstrap);
    }
}
