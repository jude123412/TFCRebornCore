package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.cleanroommc.groovyscript.core.mixin.forestry.CarpenterRecipeManagerAccessor;

import forestry.api.recipes.ICarpenterRecipe;
import forestry.core.recipes.ShapedRecipeCustom;
import forestry.factory.recipes.CarpenterRecipe;

public class ForestryRecipeManager {

    public static void removeAllCarpenterRecipes() {
        CarpenterRecipeManagerAccessor.getRecipes().clear();
    }

    public static void addCarpenterRecipe(int packageTime, ItemStack box, ItemStack result, FluidStack fluidInput,
                                          Object... inputs) {
        ICarpenterRecipe recipe = new CarpenterRecipe(packageTime, fluidInput, box,
                new ShapedRecipeCustom(result, inputs));
        CarpenterRecipeManagerAccessor.getRecipes().add(recipe);
    }
}
