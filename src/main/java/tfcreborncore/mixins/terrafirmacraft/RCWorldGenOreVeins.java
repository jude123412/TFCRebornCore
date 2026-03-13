package tfcreborncore.mixins.terrafirmacraft;

import java.util.Random;

import net.dries007.tfc.world.classic.worldgen.WorldGenOreVeins;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldGenOreVeins.class)
public class RCWorldGenOreVeins {

    @Inject(
            method = "generate",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private void onGenerate(Random random, int chunkX, int chunkZ, World world,
                            IChunkGenerator chunkGenerator, IChunkProvider chunkProvider,
                            CallbackInfo ci) {
        ci.cancel();
    }
}
