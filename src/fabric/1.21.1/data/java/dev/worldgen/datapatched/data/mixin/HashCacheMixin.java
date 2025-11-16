package dev.worldgen.datapatched.data.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.data.HashCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.nio.file.FileVisitor;
import java.nio.file.Path;

@Mixin(HashCache.class)
public class HashCacheMixin {
    @WrapOperation(
        method = "purgeStaleAndWrite",
        at = @At(
            value = "INVOKE",
            target = "Ljava/nio/file/Files;walkFileTree(Ljava/nio/file/Path;Ljava/nio/file/FileVisitor;)Ljava/nio/file/Path;"
        )
    )
    private Path cancelPurging(Path start, FileVisitor<? super Path> visitor, Operation<Path> original) {
        return start;
    }
}
