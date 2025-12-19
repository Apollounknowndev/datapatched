package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.SimpleTradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.ItemCost;

public record EnchantedBook(Optional<ItemCost> buyingSecondary, HolderSet<Enchantment> enchantments, int minLevel, int maxLevel, int maxUses, int experience, float priceMultiplier) implements SimpleTradeOffer {
    public static final MapCodec<EnchantedBook> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        BUYING_CODEC.optionalFieldOf("buying_secondary").forGetter(EnchantedBook::buyingSecondary),
        RegistryCodecs.homogeneousList(Registries.ENCHANTMENT).fieldOf("enchantments").forGetter(EnchantedBook::enchantments),
        Codec.intRange(1, 255).fieldOf("min_level").orElse(1).forGetter(EnchantedBook::minLevel),
        Codec.intRange(1, 255).fieldOf("max_level").orElse(255).forGetter(EnchantedBook::maxLevel),
        Codec.INT.fieldOf("max_uses").orElse(12).forGetter(EnchantedBook::maxUses),
        Codec.INT.fieldOf("experience").orElse(1).forGetter(EnchantedBook::experience),
        Codec.FLOAT.fieldOf("price_multiplier").orElse(0.2F).forGetter(EnchantedBook::priceMultiplier)
    ).apply(instance, EnchantedBook::new));

    public SimpleTradeOffer.ItemTrade createItemTrade(AbstractVillager entity, RandomSource random) {
        Optional<Holder<Enchantment>> optional = this.enchantments.getRandomElement(random);
        if (optional.isEmpty()) {
            return null;
        } else {
            Holder<Enchantment> holder = optional.get();
            Enchantment enchantment = holder.value();

            int min = Math.max(this.minLevel, enchantment.getMinLevel());
            int max = Math.min(this.maxLevel, enchantment.getMaxLevel());

            int level;
            if (min < max) {
                level = random.nextIntBetweenInclusive(min, max);
            } else {
                level = min;
            }

            ItemStack book = createBook(holder, level);
            int emeralds = 2 + random.nextInt(5 + level * 10) + 3 * level;
            if (holder.is(EnchantmentTags.DOUBLE_TRADE_PRICE)) {
                emeralds *= 2;
            }

            if (emeralds > 64) {
                emeralds = 64;
            }

            return new SimpleTradeOffer.ItemTrade(new ItemCost(Items.EMERALD, emeralds), this.buyingSecondary, book);
        }
    }

    private static ItemStack createBook(Holder<Enchantment> holder, int level) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        stack.enchant(holder, level);
        return stack;
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
