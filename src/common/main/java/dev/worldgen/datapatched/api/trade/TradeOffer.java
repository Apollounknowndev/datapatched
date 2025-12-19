package dev.worldgen.datapatched.api.trade;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.worldgen.datapatched.api.DatapatchedBuiltInRegistries;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.msrandom.multiplatform.annotations.Expect;

public interface TradeOffer {
    Codec<TradeOffer> CODEC = DatapatchedBuiltInRegistries.TRADE_OFFER_TYPE.byNameCodec().dispatch(TradeOffer::codec, Function.identity());
    Codec<Holder<TradeOffer>> ENTRY_CODEC = RegistryFileCodec.create(DatapatchedRegistries.TRADE_OFFER, CODEC);
    Codec<HolderSet<TradeOffer>> LIST_CODEC = RegistryCodecs.homogeneousList(DatapatchedRegistries.TRADE_OFFER, CODEC, false);
    Codec<ItemCost> BUYING_CODEC = Codec.withAlternative(ItemCost.CODEC, ItemStack.SIMPLE_ITEM_CODEC, (item) -> new ItemCost(item.getItem()));
    Codec<ItemStack> SELLING_CODEC = Codec.withAlternative(ItemStack.CODEC, ItemStack.SIMPLE_ITEM_CODEC, (t) -> t);
    Holder<TradeOffer> EMPTY = Holder.direct(new Empty());

    List<MerchantOffer> create(AbstractVillager var1, RandomSource var2);

    MapCodec<? extends TradeOffer> codec();

    @Expect static LootContext createContext(AbstractVillager entity);
}
