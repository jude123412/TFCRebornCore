package tfcreborncore.mixins.terrafirmacraft;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "cofh.core.inventory.ComparableItemStackValidated")
public class RCComparableItemStackValidatedMixin {

    @Inject(method = "getOreID*", at = @At("HEAD"), cancellable = true)
    private void disableOreDict(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(-1);
    }
}
