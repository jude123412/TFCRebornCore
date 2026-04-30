package tfcreborncore.mixins.forestry;

import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.minecraftforge.fluids.Fluid;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import forestry.core.fluids.FilteredTank;

@Pseudo
@Mixin(targets = "forestry.factory.tiles.TileMoistener")
public class RCTileMoistenerMixin {

    @Final
    @Shadow
    private FilteredTank resourceTank;

    // Allow TFC Fresh water to work for moistener
    @Inject(method = "<init>", at = @At("RETURN"))
    private void afterInit(CallbackInfo ci) {
        this.resourceTank.setFilters(new Fluid[] { FluidsTFC.FRESH_WATER.get() });
    }
}
