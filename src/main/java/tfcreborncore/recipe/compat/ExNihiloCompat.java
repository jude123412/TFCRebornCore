package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;
import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.mods.TFCRecipeHelper;

public class ExNihiloCompat implements ICompatModule {

    @Override
    public List<String> dependancies() {
        return Arrays.asList(
                Mods.EX_NIHILO_CREATIO.getName());
    }

    @Override
    public void registerCraftingRecipe() {}

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        TFCRecipeHelper.addBarrelRecipe(r, IIngredient.of(getFluidStack("salt_water", 1000)),
                IIngredient.of(getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "item_material", 3)),
                getFluidStack("witchwater", 1000), ItemStack.EMPTY, 0, "witch_water_from_ancient_spores");

        TFCRecipeHelper.addBarrelRecipeFluidMixin(r, IIngredient.of(getFluidStack("salt_water", 9000)),
                getFluidStack("witchwater", 1000), getFluidStack("witchwater", 10000), 0,
                "witch_water_from_witch_water");
    }
}
