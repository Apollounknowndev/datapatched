package dev.worldgen.datapatched.mixin.trade;

import dev.worldgen.datapatched.impl.trade.TradeHelper;
import dev.worldgen.datapatched.impl.trade.TradeSet;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
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
        Identifier id = TradeHelper.getProfession(data);

        var level = $this.level();
        if (level instanceof ServerLevel serverLevel) {
            // Find villager profession and associated trade set
            ResourceKey<VillagerProfession> profession = ResourceKey.create(Registries.VILLAGER_PROFESSION, id);
            boolean bl = TradeSet.addOffers(serverLevel, $this, profession, TradeHelper.getLevel(data), id.withSuffix("/level_" + TradeHelper.getLevel(data)));
            if (bl) ci.cancel();
        }
    }
}