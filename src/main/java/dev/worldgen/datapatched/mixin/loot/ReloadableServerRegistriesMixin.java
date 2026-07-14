package dev.worldgen.datapatched.mixin.loot;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.worldgen.datapatched.api.event.AddLootModifiersEvent;
import dev.worldgen.datapatched.api.loot.modifier.LootModifier;
import dev.worldgen.datapatched.api.DatapatchedRegistries;
import dev.worldgen.datapatched.impl.Datapatched;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.*;

@Mixin(ReloadableServerRegistries.class)
public class ReloadableServerRegistriesMixin {
    @ModifyReturnValue(
        method = {
            // fabric 1.21.1
            "method_58279(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // neoforge 1.21.1
            "lambda$scheduleElementParse$4(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // fabric 26.1
            "lambda$scheduleRegistryLoad$0(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/resources/RegistryOps;)Lnet/minecraft/core/WritableRegistry;",
            // neoforge 26.1
            "lambda$scheduleRegistryLoad$0(Lnet/minecraft/world/level/storage/loot/LootDataType;Lnet/minecraft/resources/RegistryOps;Lnet/minecraft/server/packs/resources/ResourceManager;)Lnet/minecraft/core/WritableRegistry;"
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
            Map<Identifier, LootModifier> modifiersById = new HashMap<>();
            lookup.listElements().forEach(holder -> {
                modifiersById.put(holder.key().identifier(), holder.value());
            });
            AddLootModifiersEvent.EVENT.invoker().addLootModifiers(ops, (id, injector) -> {
                if (!modifiersById.containsKey(id)) {
                    modifiersById.put(id, injector);
                }
            });
            
            var modifiers = datapatched$sortModifiersByPriority(modifiersById);
            for (Identifier id : registry.keySet()) {
                LootTable table = (LootTable) registry.getOptional(id).get();
                for (Map.Entry<Identifier, LootModifier> entry : modifiers) {
                    entry.getValue().tryApply(table, id);
                }
            }
        } else {
            Datapatched.LOGGER.error("Couldn't get loot modifier registry, not applying loot modifiers :(");
        }
        return registry;
    }
    
    @Unique
    private static List<Map.Entry<Identifier, LootModifier>> datapatched$sortModifiersByPriority(Map<Identifier, LootModifier> modifiers) {
        return modifiers.entrySet().stream().sorted(Comparator.comparingInt(entry -> entry.getValue().commonData().priority())).toList();
    }
}
