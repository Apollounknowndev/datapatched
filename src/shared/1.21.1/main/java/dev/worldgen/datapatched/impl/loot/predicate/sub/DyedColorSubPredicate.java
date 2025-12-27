package dev.worldgen.datapatched.impl.loot.predicate.sub;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.criterion.ItemSubPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class DyedColorSubPredicate implements ItemSubPredicate {
    public static final Codec<DyedColorSubPredicate> CODEC = Codec.unit(DyedColorSubPredicate::new);
    public static final ItemSubPredicate.Type<DyedColorSubPredicate> TYPE = new Type<>(CODEC);

    @Override
    public boolean matches(ItemStack stack) {
        return stack.has(DataComponents.DYED_COLOR);
    }
}
