package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPacksSource.class)
public abstract class ServerPacksSourceMixin {
    @Shadow
    private static PackLocationInfo createBuiltInPackLocation(String $$0, Component $$1) {
        return null;
    }

    @Shadow @Final private static PackSelectionConfig FEATURE_SELECTION_CONFIG;

    @Inject(
        method = "createBuiltinPack",
        at = @At("HEAD"),
        cancellable = true
    )
    private void replaceTradeRebalancePack(String id, Pack.ResourcesSupplier resources, Component title, CallbackInfoReturnable<Pack> cir) {
        if (id.equals("trade_rebalance")) {
            cir.setReturnValue(
                Pack.readMetaAndCreate(createBuiltInPackLocation(id, title), Datapatched.createTradeRebalanceSupplier(), PackType.SERVER_DATA, FEATURE_SELECTION_CONFIG)
            );
        }
    }
}
