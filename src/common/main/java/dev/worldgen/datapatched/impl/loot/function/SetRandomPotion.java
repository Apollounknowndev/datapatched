package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;

import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class SetRandomPotion extends LootItemConditionalFunction {
    public static final MapCodec<SetRandomPotion> CODEC = RecordCodecBuilder.mapCodec(instance -> SetRandomPotion.commonFields(instance).and(
        RegistryCodecs.homogeneousList(Registries.POTION).optionalFieldOf("options").forGetter(f -> f.options)
    ).apply(instance, SetRandomPotion::new));
    public static final LootItemFunctionType<SetRandomPotion> TYPE = new LootItemFunctionType<>(CODEC);
    private final Optional<HolderSet<Potion>> options;

    private SetRandomPotion(List<LootItemCondition> predicates, Optional<HolderSet<Potion>> options) {
        super(predicates);
        this.options = options;
    }

    public MapCodec<SetRandomPotion> codec() {
        return CODEC;
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack stack, LootContext context) {
        if (this.options.isPresent()) {
            var potion = this.options.get().getRandomElement(context.getRandom());
            potion.ifPresent(potionHolder -> stack.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, potionHolder, PotionContents::withPotion));
        } else {
            var potion = Util.getRandomSafe(context.getLevel().registryAccess().lookupOrThrow(Registries.POTION).listElements().toList(), context.getRandom());
            potion.ifPresent(potionHolder -> stack.update(DataComponents.POTION_CONTENTS, PotionContents.EMPTY, potionHolder, PotionContents::withPotion));
        }
        return stack;
    }
}

