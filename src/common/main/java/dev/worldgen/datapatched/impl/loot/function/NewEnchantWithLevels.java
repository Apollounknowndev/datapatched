package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;

import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

public class NewEnchantWithLevels extends LootItemConditionalFunction {
    public static final MapCodec<NewEnchantWithLevels> CODEC = RecordCodecBuilder.mapCodec(i -> NewEnchantWithLevels.commonFields(i).and(i.group(
        NumberProviders.CODEC.fieldOf("levels").forGetter(f -> f.levels),
        RegistryCodecs.homogeneousList(Registries.ENCHANTMENT).optionalFieldOf("options").forGetter(f -> f.options),
        Codec.BOOL.optionalFieldOf("include_additional_cost_component", false).forGetter(f -> f.includeAdditionalCostComponent))
    ).apply(i, NewEnchantWithLevels::new));
    public static final LootItemFunctionType<NewEnchantWithLevels> TYPE = new LootItemFunctionType<>(CODEC);

    private final NumberProvider levels;
    private final Optional<HolderSet<Enchantment>> options;
    private final boolean includeAdditionalCostComponent;

    private NewEnchantWithLevels(List<LootItemCondition> predicates, NumberProvider levels, Optional<HolderSet<Enchantment>> options, boolean includeAdditionalCostComponent) {
        super(predicates);
        this.levels = levels;
        this.options = options;
        this.includeAdditionalCostComponent = includeAdditionalCostComponent;
    }

    public MapCodec<NewEnchantWithLevels> codec() {
        return CODEC;
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack itemStack, LootContext context) {
        RandomSource random = context.getRandom();
        RegistryAccess registryAccess = context.getLevel().registryAccess();
        int enchantmentCost = this.levels.getInt(context);
        ItemStack result = EnchantmentHelper.enchantItem(random, itemStack, enchantmentCost, registryAccess, this.options);
        if (this.includeAdditionalCostComponent && !result.isEmpty() && enchantmentCost > 0) {
            result.set(VillagerTrade.ADDITIONAL_TRADE_COST, enchantmentCost);
        }
        return result;
    }
}
