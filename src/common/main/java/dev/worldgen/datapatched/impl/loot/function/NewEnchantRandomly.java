package dev.worldgen.datapatched.impl.loot.function;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import dev.worldgen.datapatched.impl.trade.VillagerTrade;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.slf4j.Logger;

public class NewEnchantRandomly extends LootItemConditionalFunction {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MapCodec<NewEnchantRandomly> CODEC = RecordCodecBuilder.mapCodec(i -> NewEnchantRandomly.commonFields(i).and(i.group(
        RegistryCodecs.homogeneousList(Registries.ENCHANTMENT).optionalFieldOf("options").forGetter(f -> f.options),
        Codec.BOOL.optionalFieldOf("only_compatible", true).forGetter(f -> f.onlyCompatible),
        Codec.BOOL.optionalFieldOf("include_additional_cost_component", false).forGetter(f -> f.includeAdditionalCostComponent))
    ).apply(i, NewEnchantRandomly::new));
    public static final LootItemFunctionType<NewEnchantRandomly> TYPE = new LootItemFunctionType<>(CODEC);
    private final Optional<HolderSet<Enchantment>> options;
    private final boolean onlyCompatible;
    private final boolean includeAdditionalCostComponent;

    private NewEnchantRandomly(List<LootItemCondition> predicates, Optional<HolderSet<Enchantment>> options, boolean onlyCompatible, boolean includeAdditionalCostComponent) {
        super(predicates);
        this.options = options;
        this.onlyCompatible = onlyCompatible;
        this.includeAdditionalCostComponent = includeAdditionalCostComponent;
    }

    public MapCodec<NewEnchantRandomly> codec() {
        return CODEC;
    }

    @Override
    public LootItemFunctionType<? extends LootItemConditionalFunction> getType() {
        return TYPE;
    }

    @Override
    public ItemStack run(ItemStack itemStack, LootContext context) {
        RandomSource random = context.getRandom();
        boolean targetIsBook = itemStack.is(Items.BOOK);
        boolean shouldCheckCompatibility = !targetIsBook && this.onlyCompatible;
        Stream<Holder<Enchantment>> compatibleEnchantmentsStream = this.options.map(HolderSet::stream).orElseGet(() -> context.getLevel().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).listElements().map(Function.identity())).filter(candidate -> !shouldCheckCompatibility || ((Enchantment)candidate.value()).canEnchant(itemStack));
        List<Holder<Enchantment>> compatibleEnchantments = compatibleEnchantmentsStream.toList();
        Optional<Holder<Enchantment>> enchantment = Util.getRandomSafe(compatibleEnchantments, random);
        if (enchantment.isEmpty()) {
            LOGGER.warn("Couldn't find a compatible enchantment for {}", itemStack);
            return itemStack;
        }
        return this.enchantItem(itemStack, enchantment.get(), context);
    }

    private ItemStack enchantItem(ItemStack itemStack, Holder<Enchantment> enchantment, LootContext context) {
        RandomSource random = context.getRandom();
        int level = Mth.nextInt(random, enchantment.value().getMinLevel(), enchantment.value().getMaxLevel());
        if (itemStack.is(Items.BOOK)) {
            itemStack = new ItemStack(Items.ENCHANTED_BOOK);
        }
        itemStack.enchant(enchantment, level);
        if (this.includeAdditionalCostComponent) {
            itemStack.set(VillagerTrade.ADDITIONAL_TRADE_COST, 2 + random.nextInt(5 + level * 10) + 3 * level);
        }
        return itemStack;
    }
}

