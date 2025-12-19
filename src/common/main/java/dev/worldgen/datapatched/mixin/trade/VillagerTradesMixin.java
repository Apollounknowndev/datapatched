package dev.worldgen.datapatched.mixin.trade;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(value = {VillagerTrades.class}, priority = -1000)
public abstract class VillagerTradesMixin {
    @Shadow @Final @Mutable public static Map<ResourceKey<VillagerProfession>, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES;
    @Shadow @Final @Mutable public static Map<ResourceKey<VillagerProfession>, Int2ObjectMap<VillagerTrades.ItemListing[]>> EXPERIMENTAL_TRADES;


    /**
     * Clears all *vanilla* trades.
     * If a mod/modloader adds new trades to the maps, they can be injected into the trade offer provider pools.
     */
    static {
        TRADES = new HashMap<>();
        EXPERIMENTAL_TRADES = new HashMap<>();
    }
}