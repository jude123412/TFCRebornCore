package tfcreborncore.mixins.forestry;

import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = "forestry.factory.recipes.jei.moistener.MoistenerRecipeWrapper")
public abstract class RCMoistenerRecipeWrapperMixin {

    /**
     * Redirect the creation of the FluidStack used in JEI display.
     * Forestry uses: new FluidStack(FluidRegistry.WATER, amount)
     * We replace WATER with TFC fresh water.
     */
    @Redirect(
              method = "getIngredients",
              at = @At(
                       value = "NEW",
                       target = "net/minecraftforge/fluids/FluidStack"))
    private FluidStack redirectFluidStack(Fluid fluid, int amount) {
        return new FluidStack(FluidsTFC.FRESH_WATER.get(), amount);
    }
}
