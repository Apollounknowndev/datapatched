package dev.worldgen.datapatched.api.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.impl.loot.modifier.CommonData;
import dev.worldgen.datapatched.mixin.loot.LootTableAccessor;
import java.util.function.Function;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.LootTable;

public interface LootModifier {
    Codec<LootModifier> CODEC = DatapatchedBuiltInRegistries.LOOT_MODIFIER_TYPE.byNameCodec().dispatch(LootModifier::codec, Function.identity());

    CommonData commonData();

    void apply(LootTable table, Identifier key);

    MapCodec<? extends LootModifier> codec();

    default boolean tryApply(LootTable table, Identifier id) {
        if (this.commonData().target().test(id)) {
            this.apply(table, id);
            return true;
        } else {
            return false;
        }
    }

    default LootTableAccessor accessor(LootTable table) {
        return (LootTableAccessor)table;
    }
}
