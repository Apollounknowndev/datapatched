package dev.worldgen.datapatched.impl.trade.offer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.datapatched.api.trade.SimpleTradeOffer;
import dev.worldgen.datapatched.api.trade.TradeOffer;
import java.util.Optional;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;

public record Base(ItemCost buying, Optional<ItemCost> buyingSecondary, ItemStack selling, Optional<LootItemFunction> sellingModifier, int maxUses, int experience, float priceMultiplier) implements SimpleTradeOffer {
    public static final MapCodec<Base> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        BUYING_CODEC.fieldOf("buying").forGetter(Base::buying),
        BUYING_CODEC.optionalFieldOf("buying_secondary").forGetter(Base::buyingSecondary),
        SELLING_CODEC.fieldOf("selling").forGetter(Base::selling),
        LootItemFunctions.ROOT_CODEC.optionalFieldOf("selling_modifier").forGetter(Base::sellingModifier),
        Codec.INT.fieldOf("max_uses").orElse(12).forGetter(Base::maxUses),
        Codec.INT.fieldOf("experience").orElse(1).forGetter(Base::experience),
        Codec.FLOAT.fieldOf("price_multiplier").orElse(0.05F).forGetter(Base::priceMultiplier)
    ).apply(instance, Base::new));

    public SimpleTradeOffer.ItemTrade createItemTrade(AbstractVillager entity, RandomSource random) {
        return new SimpleTradeOffer.ItemTrade(this.buying, this.buyingSecondary, this.modifyStack(entity, this.selling.copy()));
    }

    private ItemStack modifyStack(AbstractVillager entity, ItemStack original) {
        if (this.sellingModifier.isEmpty()) {
            return original;
        } else {
            LootContext context = TradeOffer.createContext(entity);
            ItemStack modified = this.sellingModifier.get().apply(original, context);
            modified.limitSize(modified.getMaxStackSize());
            return modified;
        }
    }

    public MapCodec<? extends TradeOffer> codec() {
        return CODEC;
    }
}
