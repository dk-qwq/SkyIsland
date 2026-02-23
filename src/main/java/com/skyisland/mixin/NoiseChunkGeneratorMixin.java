package com.skyisland.mixin;

import java.util.concurrent.CompletableFuture;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public abstract class NoiseChunkGeneratorMixin {
    /**
     * Skip vanilla noise terrain fill entirely.
     * Structures are generated in dedicated structure stages, so they remain intact.
     */
    @Inject(method = "populateNoise", at = @At("HEAD"), cancellable = true)
    private void skyisland$skipTerrainNoise(
        Blender blender,
        NoiseConfig noiseConfig,
        StructureAccessor structureAccessor,
        Chunk chunk,
        CallbackInfoReturnable<CompletableFuture<Chunk>> cir
    ) {
        cir.setReturnValue(CompletableFuture.completedFuture(chunk));
    }
}
