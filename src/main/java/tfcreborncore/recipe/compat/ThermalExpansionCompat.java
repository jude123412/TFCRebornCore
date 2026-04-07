package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.mods.TFCRecipeHelper;

public class ThermalExpansionCompat implements ICompatModule {

    @Override
    public List<String> dependancies() {
        return Arrays.asList(
                Mods.THERMAL_EXPANSION.getName());
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        TFCRecipeHelper.addBarrelRecipe(r, IIngredient.of(getFluidStack("fresh_water", 1000)),
                IIngredient.of("dustPyrotheum", 1), getFluidStack("hot_water", 1000), ItemStack.EMPTY, 0,
                "hot_water_from_pyrotheum");
    }
}
