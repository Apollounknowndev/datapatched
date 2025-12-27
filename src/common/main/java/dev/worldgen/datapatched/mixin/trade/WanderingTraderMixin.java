package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.trade.TradeHelper;
import dev.worldgen.datapatched.impl.trade.TradeSet;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
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
        var level = $this.level();
        if (level instanceof ServerLevel serverLevel) {
            boolean bl = false;
            bl |= TradeSet.addOffers(serverLevel, $this, TradeHelper.WANDERING_TRADER, 1, Identifier.withDefaultNamespace("wandering_trader/buying"));
            bl |= TradeSet.addOffers(serverLevel, $this, TradeHelper.WANDERING_TRADER, 1, Identifier.withDefaultNamespace("wandering_trader/common"));
            bl |= TradeSet.addOffers(serverLevel, $this, TradeHelper.WANDERING_TRADER, 1, Identifier.withDefaultNamespace("wandering_trader/uncommon"));
            if (bl) ci.cancel();
        }
    }
}