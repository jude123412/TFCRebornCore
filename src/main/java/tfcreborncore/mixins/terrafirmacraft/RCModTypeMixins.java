package tfcreborncore.mixins.terrafirmacraft;

import net.minecraft.block.SoundType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import mrthomas20121.tfc_decoration.api.ModTypes;

@Mixin(ModTypes.class)
public class RCModTypeMixins {

    /**
     * Replace only the SoundType for RAW_MUD during ModTypes.init()
     */
    @ModifyArgs(
                method = "init",
                at = @At(
                         value = "INVOKE",
                         target = "Lmrthomas20121/tfc_decoration/api/ModTypes;addRockType(Ljava/lang/String;Lnet/minecraft/block/SoundType;IZ)Lmrthomas20121/tfc_decoration/api/ModTypes$RockType;",
                         remap = false),
                remap = false)
    private static void replaceRawMudSound(Args args) {
        String name = args.get(0);

        if ("raw_mud".equals(name)) {
            args.set(1, SoundType.SLIME);
        }
    }
}
