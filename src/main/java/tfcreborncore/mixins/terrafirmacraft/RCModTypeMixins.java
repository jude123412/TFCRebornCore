package tfcreborncore.mixins.terrafirmacraft;

import net.minecraft.block.SoundType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import mrthomas20121.tfc_decoration.api.ModTypes;

@Mixin(ModTypes.class)
public class RCModTypeMixins {

    /**
     * Replace only the SoundType for RAW_MUD during ModTypes.init()
     */
    @ModifyArg(
               method = "init",
               at = @At(
                        value = "INVOKE",
                        target = "Lmrthomas20121/tfc_decoration/api/ModTypes;addRockType(Ljava/lang/String;Lnet/minecraft/block/SoundType;IZ)Lmrthomas20121/tfc_decoration/api/ModTypes$RockType;",
                        remap = false),
               index = 1, // the SoundType parameter
               remap = false)
    private static SoundType replaceRawMudSound(SoundType original) {
        // Only override the sound for raw_mud
        return SoundType.GROUND;
    }
}
