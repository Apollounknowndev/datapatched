package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.trade.TradeHelper;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import java.util.Optional;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.flag.FeatureFlags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(
        at = @At("HEAD"),
        method = "updateTrades",
        cancellable = true
    )
    private void sellcraft$injectSellcraftTrades(CallbackInfo ci) {
        Villager $this = (Villager) (Object) this;
        VillagerData data = $this.getVillagerData();
        ResourceLocation id = TradeHelper.getProfession(data);

        ResourceKey<VillagerProfession> profession = ResourceKey.create(Registries.VILLAGER_PROFESSION, id);
        Optional<TradeOfferProvider> optional = TradeOfferProvider.getProvider($this.registryAccess(), profession.location());
        if (optional.isEmpty()) return;

        TradeOfferProvider provider = optional.get();
        int level = TradeHelper.getLevel(data);
        if (level <= provider.tiers().size()) {
            VillagerTrades.ItemListing[] moddedTierTrades = new VillagerTrades.ItemListing[]{};
            if (!provider.overrideModdedTrades()) {
                Int2ObjectMap<VillagerTrades.ItemListing[]> moddedFullTrades;
                if ($this.level().enabledFeatures().contains(FeatureFlags.TRADE_REBALANCE)) {
                    Int2ObjectMap<VillagerTrades.ItemListing[]> experimentalModdedTrades = (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.EXPERIMENTAL_TRADES.get(profession);
                    moddedFullTrades = experimentalModdedTrades != null ? experimentalModdedTrades : (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.TRADES.get(profession);
                } else {
                    moddedFullTrades = (Int2ObjectMap<VillagerTrades.ItemListing[]>) VillagerTrades.TRADES.get(profession);
                }
                moddedTierTrades = moddedFullTrades.get(level);
            }

            TradeOfferProvider.TradeTier tradeTier = provider.tiers().get(level - 1);
            TradeHelper.addDatapatchedTrades($this, $this.getOffers(), tradeTier, moddedTierTrades);
            ci.cancel();
        }

    }
}