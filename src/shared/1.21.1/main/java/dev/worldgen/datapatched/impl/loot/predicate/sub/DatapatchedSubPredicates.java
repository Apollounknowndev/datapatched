package dev.worldgen.datapatched.impl.loot.predicate.sub;

import net.minecraft.advancements.criterion.ItemSubPredicate;

import java.util.function.BiConsumer;

public class DatapatchedSubPredicates {
    public static void registerDatapatchedSubPredicates(BiConsumer<String, ItemSubPredicate.Type<?>> consumer) {
        consumer.accept("map_id", MapIdSubPredicate.TYPE);
        consumer.accept("dyed_color", DyedColorSubPredicate.TYPE);
    }
}
