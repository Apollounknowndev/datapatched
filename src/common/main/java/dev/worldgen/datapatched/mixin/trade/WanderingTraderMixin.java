package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.TradeHelper;
import dev.worldgen.datapatched.impl.trade.provider.TradeOfferProvider;
import java.util.Optional;

import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin {

    @Inject(
        at = @At("HEAD"),
        method = "updateTrades",
        cancellable = true
    )
    private void sellcraft$injectSellcraftTrades(CallbackInfo ci) {
        WanderingTrader $this = (WanderingTrader) (Object) this;
        Optional<TradeOfferProvider> tradeProvider = TradeOfferProvider.getProvider($this.registryAccess(), Datapatched.id("wandering_trader"));
        if (tradeProvider.isPresent()) {
            for(TradeOfferProvider.TradeTier tradeTier : tradeProvider.get().tiers()) {
                TradeHelper.addDatapatchedTrades($this, $this.getOffers(), tradeTier, TradeHelper.NO_MODDED_TRADES);
            }

            ci.cancel();
        }
    }
}