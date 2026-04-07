package tfcreborncore.recipe.mods;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFluidMixing;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;

public class TFCRecipeHelper {

    public static int getHours(int hours) {
        return hours * 1000;
    }

    public static void addBarrelRecipe(IForgeRegistry<BarrelRecipe> r, IIngredient<FluidStack> inputFluid,
                                       IIngredient<ItemStack> inputStack, FluidStack outputFluid, ItemStack outputStack,
                                       int duration, String regName) {
        r.register(new BarrelRecipe(inputFluid, inputStack, outputFluid, outputStack, duration)
                .setRegistryName(new ResourceLocation(Tags.MODID, "barrel/" + regName.toLowerCase())));
    }

    public static void addBarrelRecipeFluidMixin(IForgeRegistry<BarrelRecipe> r, IIngredient<FluidStack> inputFluid1,
                                                 FluidStack inputFluid2, FluidStack outputFluid, int duration,
                                                 String regName) {
        r.register(new BarrelRecipeFluidMixing(inputFluid1, new IngredientFluidItem(inputFluid2), outputFluid, duration)
                .setRegistryName(new ResourceLocation(Tags.MODID, "barrel/mixing/" + regName.toLowerCase())));
    }
}
