package dev.worldgen.datapatched.api.trade;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.msrandom.multiplatform.annotations.Actual;

import java.util.Optional;

public interface TradeOfferActual {
    @Actual static LootContext createContext(AbstractVillager entity) {
        LootParams worldContext = new LootParams.Builder((ServerLevel) entity.level())
            .withParameter(LootContextParams.ORIGIN, entity.position())
            .withOptionalParameter(LootContextParams.THIS_ENTITY, entity)
            .create(LootContextParamSets.COMMAND);
        return new LootContext.Builder(worldContext).create(Optional.empty());
    }
}
