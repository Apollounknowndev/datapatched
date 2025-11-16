package dev.worldgen.datapatched.impl.trade.provider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.Datapatched;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

public record TradeOfferProvider(List<TradeTier> tiers, boolean overrideModdedTrades) {
    public static final Codec<TradeOfferProvider> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        TradeOfferProvider.TradeTier.CODEC.listOf().fieldOf("trade_tiers").forGetter(TradeOfferProvider::tiers),
        Codec.BOOL.fieldOf("override_modded_trades").orElse(false).forGetter(TradeOfferProvider::overrideModdedTrades)
    ).apply(instance, TradeOfferProvider::new));

    public TradeOfferProvider(List<TradeTier> tiers) {
        this(tiers, false);
    }

    public static Optional<TradeOfferProvider> getProvider(RegistryAccess registries, ResourceLocation id) {
        if (id.getNamespace().equals("minecraft")) {
            id = ResourceLocation.fromNamespaceAndPath("datapatched", id.getPath());
        }
        return Datapatched.registry(registries, DatapatchedRegistries.TRADE_OFFER_PROVIDER).getOptional(id);
    }

    public List<TradeTier> tiers() {
        return this.tiers;
    }

    public record TradeTier(int count, HolderSet<TradeOffer> trades) {
        public static final Codec<TradeTier> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ExtraCodecs.NON_NEGATIVE_INT.fieldOf("count").forGetter(TradeTier::count),
            TradeOffer.LIST_CODEC.fieldOf("entries").forGetter(TradeTier::trades)
        ).apply(instance, TradeTier::new));
    }
}
