package tfcreborncore.mixins.terrafirmacraft;

import net.dries007.tfc.api.capability.heat.Heat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import tfcreborncore.api.capability.heat.ExtendedHeat;

@Mixin(Heat.class)
public class RCExtendedHeatMixin {

    @Inject(method = "maxVisibleTemperature",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void injectExtendedMaxVisable(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(ExtendedHeat.maxVisibleTemperature());
    }

    @Inject(method = "getTooltip",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void injectExtendedTooltip(float temperature, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(ExtendedHeat.getTooltip(temperature));
    }

    @Inject(method = "getTooltipAlternate",
            at = @At("HEAD"),
            cancellable = true,
            remap = false)
    private static void injectAltExtendedTooltip(float temperature, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(ExtendedHeat.getTooltipAlternate(temperature));
    }
}
