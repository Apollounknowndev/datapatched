package dev.worldgen.datapatched.mixin.loot;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.worldgen.datapatched.api.loot.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Mixin(ReloadableServerRegistries.class)
public class ReloadableServerRegistriesMixin {
    @ModifyReturnValue(
        method = {
            // fabric 1.21.1
            "method_58279(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // fabric 1.21.9
            "lambda$scheduleRegistryLoad$3(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // neoforge 1.21.1
            "lambda$scheduleElementParse$4(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // neoforge 1.21.9
            "lambda$scheduleRegistryLoad$5(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/resources/RegistryOps;Lnet/minecraft/server/packs/resources/ResourceManager;)Lnet/minecraft/core/WritableRegistry;",
        },
        at = @At("RETURN")
    )
    private static WritableRegistry<?> applyLootModifiers(
        WritableRegistry<?> registry,
        @Local(ordinal = 0, argsOnly = true) LootDataType<?> dataType,
        @Local(ordinal = 0, argsOnly = true) ResourceManager manager,
        @Local(ordinal = 0, argsOnly = true) RegistryOps<Object> ops
    ) {
        if (!dataType.registryKey().equals(Registries.LOOT_TABLE)) return registry;

        Optional<HolderGetter<LootModifier>> getter = ops.getter(DatapatchedRegistries.LOOT_MODIFIER);
        if (getter.isPresent() && getter.get() instanceof HolderLookup<LootModifier> lookup) {
            ArrayList<Holder.Reference<LootModifier>> modifiers = new ArrayList<>(lookup.listElements().toList());
            modifiers.sort(Comparator.comparingInt(holder -> holder.value().commonData().priority()));

            for (Identifier id : registry.keySet()) {
                LootTable table = (LootTable) registry.getOptional(id).get();
                for (Holder.Reference<LootModifier> modifier : modifiers) {
                    modifier.value().tryApply(table, id);
                }
            }
        } else {
            Datapatched.LOGGER.error("Couldn't get loot modifier registry, not applying loot modifiers :(");
        }
        return registry;
    }
}
//// fabric 1.21.1
//"method_58279(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
//// fabric 1.21.9
//"method_61240(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
//// neoforge 1.21.1
//"lambda$scheduleElementParse$4(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
//// neoforge 1.21.9
//"lambda$scheduleRegistryLoad$5(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/resources/RegistryOps;Lnet/minecraft/server/packs/resources/ResourceManager;)Lnet/minecraft/core/WritableRegistry;",

