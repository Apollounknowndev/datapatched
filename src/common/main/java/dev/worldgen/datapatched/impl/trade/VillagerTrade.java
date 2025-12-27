package dev.worldgen.datapatched.impl.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

public record VillagerTrade(TradeCost wants, Optional<TradeCost> additionalWants, ItemStack gives, Optional<LootItemCondition> merchantPredicate, List<LootItemFunction> givenItemModifiers, NumberProvider maxUses, NumberProvider xp, NumberProvider reputationDiscount, Optional<HolderSet<Enchantment>> doubleTradePriceEnchantments) implements MerchantOfferProvider {
    public static final DataComponentType<Integer> ADDITIONAL_TRADE_COST = DataComponentType.<Integer>builder().networkSynchronized(ByteBufCodecs.VAR_INT).build();
    public static final Codec<VillagerTrade> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        TradeCost.CODEC.fieldOf("wants").forGetter(VillagerTrade::wants),
        TradeCost.CODEC.optionalFieldOf("additional_wants").forGetter(VillagerTrade::additionalWants),
        ItemStack.CODEC.fieldOf("gives").forGetter(VillagerTrade::gives),
        LootItemCondition.DIRECT_CODEC.optionalFieldOf("merchant_predicate").forGetter(VillagerTrade::merchantPredicate),
        LootItemFunctions.ROOT_CODEC.listOf().optionalFieldOf("given_item_modifiers", List.of()).forGetter(VillagerTrade::givenItemModifiers),
        NumberProviders.CODEC.lenientOptionalFieldOf("max_uses", ConstantValue.exactly(4.0f)).forGetter(VillagerTrade::maxUses),
        NumberProviders.CODEC.lenientOptionalFieldOf("xp", ConstantValue.exactly(1.0f)).forGetter(VillagerTrade::xp),
        NumberProviders.CODEC.lenientOptionalFieldOf("reputation_discount", ConstantValue.exactly(0.0f)).forGetter(VillagerTrade::reputationDiscount),
        RegistryCodecs.homogeneousList(Registries.ENCHANTMENT).optionalFieldOf("double_trade_price_enchantments").forGetter(VillagerTrade::doubleTradePriceEnchantments)
    ).apply(instance, VillagerTrade::new));

    public MerchantOffer getOffer(LootContext context) {
        if (this.merchantPredicate.isPresent() && !this.merchantPredicate.get().test(context)) {
            return null;
        }
        ItemStack result = this.gives.copy();
        int additionalCost = 0;
        for (LootItemFunction outputItemModifier : this.givenItemModifiers) {
            result = outputItemModifier.apply(result, context);
            if (!result.isEmpty()) continue;
            return null;
        }
        Integer additionalTradeCost = result.remove(ADDITIONAL_TRADE_COST);
        if (additionalTradeCost != null) {
            additionalCost += additionalTradeCost;
        }
        if (this.doubleTradePriceEnchantments.isPresent()) {
            HolderSet<Enchantment> enchantments = this.doubleTradePriceEnchantments.get();
            ItemEnchantments itemEnchantments = result.get(DataComponents.STORED_ENCHANTMENTS);
            if (itemEnchantments != null) {
                if (itemEnchantments.keySet().stream().anyMatch(enchantments::contains)) {
                    additionalCost *= 2;
                }
            }
        }
        return new MerchantOffer(this.wants.toItemCost(context, additionalCost), this.additionalWants.map(tradeCost -> tradeCost.toItemCost(context, 0)), result, Math.max(this.maxUses.getInt(context), 1), Math.max(this.xp.getInt(context), 0), Math.max(this.reputationDiscount.getFloat(context), 0.0f));
    }
}
