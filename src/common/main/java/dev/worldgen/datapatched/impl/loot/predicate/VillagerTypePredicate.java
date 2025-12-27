package dev.worldgen.datapatched.impl.loot.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.impl.Datapatched;
import dev.worldgen.datapatched.impl.trade.TradeHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.List;

public record VillagerTypePredicate(List<Identifier> types) implements LootItemCondition {
    public static final MapCodec<VillagerTypePredicate> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
        Codec.withAlternative(Identifier.CODEC.listOf(), Identifier.CODEC, List::of).fieldOf("types").forGetter(VillagerTypePredicate::types)
    ).apply(i, VillagerTypePredicate::new));
    public static final LootItemConditionType TYPE = new LootItemConditionType(CODEC);

    @Override
    public LootItemConditionType getType() {
        return TYPE;
    }

    @Override
    public boolean test(LootContext context) {
        var entity = Datapatched.getEntity(context);
        if (entity instanceof Villager villager) {
            return this.types.contains(TradeHelper.getType(villager.getVillagerData()));
        }
        return false;
    }
}
