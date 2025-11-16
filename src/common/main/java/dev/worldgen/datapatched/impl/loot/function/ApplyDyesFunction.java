package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class ApplyDyesFunction extends LootItemConditionalFunction {
    public static final MapCodec<ApplyDyesFunction> CODEC = RecordCodecBuilder.mapCodec(instance -> commonFields(instance).and(
        Entry.CODEC.listOf().fieldOf("dyes").forGetter(ApplyDyesFunction::entries)
    ).apply(instance, ApplyDyesFunction::new));
    public static final LootItemFunctionType<ApplyDyesFunction> TYPE = new LootItemFunctionType<>(CODEC);
    private final List<Entry> entries;

    public ApplyDyesFunction(List<LootItemCondition> conditions, List<Entry> entries) {
        super(conditions);
        this.entries = entries;
    }

    public LootItemFunctionType<ApplyDyesFunction> getType() {
        return TYPE;
    }

    public ItemStack run(ItemStack stack, LootContext context) {
        if (!stack.is(ItemTags.DYEABLE)) return stack;

        List<DyeItem> dyes = new ArrayList<>();
        for (Entry entry : this.entries) {
            List<DyeColor> colors = entry.dyes.orElse(Arrays.stream(DyeColor.values()).toList());
            dyes.add(DyeItem.byColor(Util.getRandom(colors, context.getRandom())));
        }
        return DyedItemColor.applyDyes(stack, dyes);
    }

    public static Entry entry(float chance) {
        return new Entry(Optional.empty(), chance);
    }

    public List<Entry> entries() {
        return this.entries;
    }

    public record Entry(Optional<List<DyeColor>> dyes, float chance) {
        public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            DyeColor.CODEC.listOf().optionalFieldOf("dyes").forGetter(Entry::dyes),
            Codec.floatRange(0f, 1f).fieldOf("chance").orElse(1f).forGetter(Entry::chance)
        ).apply(instance, Entry::new));
    }
}
