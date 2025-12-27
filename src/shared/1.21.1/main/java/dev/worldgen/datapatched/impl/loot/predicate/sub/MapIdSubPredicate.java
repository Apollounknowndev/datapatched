package dev.worldgen.datapatched.impl.loot.predicate.sub;

import com.mojang.serialization.Codec;
import net.minecraft.advancements.criterion.ItemSubPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class MapIdSubPredicate implements ItemSubPredicate {
    public static final Codec<MapIdSubPredicate> CODEC = Codec.unit(MapIdSubPredicate::new);
    public static final ItemSubPredicate.Type<MapIdSubPredicate> TYPE = new Type<>(CODEC);

    @Override
    public boolean matches(ItemStack stack) {
        return stack.has(DataComponents.MAP_ID);
    }
}
