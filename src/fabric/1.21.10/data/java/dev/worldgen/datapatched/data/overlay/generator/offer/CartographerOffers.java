package dev.worldgen.datapatched.data.overlay.generator.offer;

import dev.worldgen.datapatched.api.trade.TradeOffer;
import dev.worldgen.datapatched.impl.trade.offer.Base;
import dev.worldgen.datapatched.impl.trade.offer.Empty;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import net.minecraft.world.level.storage.loot.functions.ExplorationMapFunction;
import net.minecraft.world.level.storage.loot.functions.SequenceFunction;
import net.minecraft.world.level.storage.loot.functions.SetNameFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.worldgen.datapatched.api.trade.TradeOfferBuilder.*;
import static dev.worldgen.datapatched.data.overlay.generator.OverlayTradeOfferBootstrap.register;

public class CartographerOffers {
    public static final List<List<ResourceKey<TradeOffer>>> OFFERS = new ArrayList<>();

    public static void bootstrap(BootstrapContext<TradeOffer> context) {
        OFFERS.add(List.of());
        OFFERS.add(List.of(
            register(context, name(2, "sell_taiga_village_map"), typeSpecific(
                map(8, StructureTags.ON_TAIGA_VILLAGE_MAPS, "filled_map.village_taiga", MapDecorationTypes.TAIGA_VILLAGE, 12, 5),
                VillagerType.SWAMP, VillagerType.SNOW, VillagerType.PLAINS
            )),
            register(context, name(2, "sell_swamp_explorer_map"), typeSpecific(
                map(8, StructureTags.ON_SWAMP_EXPLORER_MAPS, "filled_map.explorer_swamp", MapDecorationTypes.SWAMP_HUT, 12, 5),
                VillagerType.TAIGA, VillagerType.SNOW, VillagerType.JUNGLE
            )),
            register(context, name(2, "sell_snowy_village_map"), typeSpecific(
                map(8, StructureTags.ON_SNOWY_VILLAGE_MAPS, "filled_map.village_snowy", MapDecorationTypes.SNOWY_VILLAGE, 12, 5),
                VillagerType.TAIGA, VillagerType.SWAMP
            )),
            register(context, name(2, "sell_savanna_village_map"), typeSpecific(
                map(8, StructureTags.ON_SAVANNA_VILLAGE_MAPS, "filled_map.village_savanna", MapDecorationTypes.SAVANNA_VILLAGE, 12, 5),
                VillagerType.PLAINS, VillagerType.JUNGLE, VillagerType.DESERT
            )),
            register(context, name(2, "sell_plains_village_map"), typeSpecific(
                map(8, StructureTags.ON_PLAINS_VILLAGE_MAPS, "filled_map.village_plains", MapDecorationTypes.PLAINS_VILLAGE, 12, 5),
                VillagerType.TAIGA, VillagerType.SNOW, VillagerType.SAVANNA, VillagerType.DESERT
            )),
            register(context, name(2, "sell_jungle_explorer_map"), typeSpecific(
                map(8, StructureTags.ON_JUNGLE_EXPLORER_MAPS, "filled_map.explorer_jungle", MapDecorationTypes.JUNGLE_TEMPLE, 12, 5),
                VillagerType.SWAMP, VillagerType.SAVANNA, VillagerType.DESERT
            )),
            register(context, name(2, "sell_desert_village_map"), typeSpecific(
                map(8, StructureTags.ON_DESERT_VILLAGE_MAPS, "filled_map.village_desert", MapDecorationTypes.DESERT_VILLAGE, 12, 5),
                VillagerType.SAVANNA, VillagerType.JUNGLE
            )),
            register(context, name(2, "sell_ocean_explorer_map"), new Empty())
        ));
        OFFERS.add(List.of(
            register(context, name(3, "sell_woodland_explorer_map"), new Empty()),
            register(context, name(3, "sell_ocean_explorer_map"),
                map(13, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.OCEAN_MONUMENT, 12, 10)
            )
        ));
        OFFERS.add(List.of(
            register(context, name(4, "sell_blue_banner"), typeSpecific(itemsForEmeralds(Items.BLUE_BANNER, 2, 1, 12, 15), VillagerType.SNOW, VillagerType.TAIGA)),
            register(context, name(4, "sell_white_banner"), typeSpecific(itemsForEmeralds(Items.WHITE_BANNER, 2, 1, 12, 15), VillagerType.SNOW, VillagerType.PLAINS)),
            register(context, name(4, "sell_red_banner"), typeSpecific(itemsForEmeralds(Items.RED_BANNER, 2, 1, 12, 15), VillagerType.SNOW, VillagerType.SAVANNA)),
            register(context, name(4, "sell_green_banner"), typeSpecific(itemsForEmeralds(Items.GREEN_BANNER, 2, 1, 12, 15), VillagerType.DESERT, VillagerType.SAVANNA, VillagerType.JUNGLE)),
            register(context, name(4, "sell_lime_banner"), typeSpecific(itemsForEmeralds(Items.LIME_BANNER, 2, 1, 12, 15), VillagerType.DESERT, VillagerType.TAIGA)),
            register(context, name(4, "sell_purple_banner"), typeSpecific(itemsForEmeralds(Items.PURPLE_BANNER, 2, 1, 12, 15), VillagerType.TAIGA, VillagerType.SWAMP)),
            register(context, name(4, "sell_cyan_banner"), typeSpecific(itemsForEmeralds(Items.CYAN_BANNER, 2, 1, 12, 15), VillagerType.DESERT, VillagerType.SNOW)),
            register(context, name(4, "sell_yellow_banner"), typeSpecific(itemsForEmeralds(Items.YELLOW_BANNER, 2, 1, 12, 15), VillagerType.PLAINS, VillagerType.JUNGLE)),
            register(context, name(4, "sell_orange_banner"), typeSpecific(itemsForEmeralds(Items.ORANGE_BANNER, 2, 1, 12, 15), VillagerType.SAVANNA, VillagerType.DESERT)),
            register(context, name(4, "sell_brown_banner"), typeSpecific(itemsForEmeralds(Items.BROWN_BANNER, 2, 1, 12, 15), VillagerType.PLAINS, VillagerType.JUNGLE)),
            register(context, name(4, "sell_magenta_banner"), typeSpecific(itemsForEmeralds(Items.MAGENTA_BANNER, 2, 1, 12, 15), VillagerType.SAVANNA)),
            register(context, name(4, "sell_light_blue_banner"), typeSpecific(itemsForEmeralds(Items.LIGHT_BLUE_BANNER, 2, 1, 12, 15), VillagerType.SNOW, VillagerType.SWAMP)),
            register(context, name(4, "sell_pink_banner"), typeSpecific(itemsForEmeralds(Items.PINK_BANNER, 2, 1, 12, 15), VillagerType.TAIGA, VillagerType.PLAINS)),
            register(context, name(4, "sell_gray_banner"), typeSpecific(itemsForEmeralds(Items.GRAY_BANNER, 2, 1, 12, 15), VillagerType.DESERT)),
            register(context, name(4, "sell_black_banner"), typeSpecific(itemsForEmeralds(Items.BLACK_BANNER, 2, 1, 12, 15), VillagerType.SWAMP)),
            register(context, name(4, "sell_light_gray_banner"), typeSpecific(itemsForEmeralds(Items.LIGHT_GRAY_BANNER, 2, 1, 12, 15)))
        ));
        OFFERS.add(List.of(
            register(context, name(5, "sell_woodland_explorer_map"),
                map(14, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.WOODLAND_MANSION, 12, 30)
            )
        ));
    }

    private static TradeOffer map(int emeraldCost, TagKey<Structure> destination, String name, Holder<MapDecorationType> decoration, int maxUses, int xp) {
        var explorationFunction = new ExplorationMapFunction.Builder().setSearchRadius(100).setDestination(destination).setMapDecoration(decoration).build();
        var nameFunction = SetNameFunction.setName(Component.translatable(name), SetNameFunction.Target.ITEM_NAME).build();
        return new Base(
            new ItemCost(Items.EMERALD, emeraldCost),
            Optional.of(new ItemCost(Items.COMPASS)),
            new ItemStack(Items.MAP),
            Optional.of(SequenceFunction.of(List.of(explorationFunction, nameFunction))),
            maxUses,
            xp,
            0.2f
        );
    }

    private static String name(int level, String name) {
        return String.format("cartographer/%s/%s", LEVEL_TO_NAME.get(level - 1), name);
    }
}
