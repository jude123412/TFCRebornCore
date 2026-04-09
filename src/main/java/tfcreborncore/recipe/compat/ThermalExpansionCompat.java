package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class ThermalExpansionCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.THERMAL_EXPANSION.getName());
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // GemCinnabar
        OreDictionary.registerOre("gemCinnabar",
                RecipeHelper.getItemStack("thermalfoundation", "material", 866));
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        TerrafirmacraftRecipeManager.addBarrelRecipe(r, IIngredient.of(getFluidStack("fresh_water", 1000)),
                IIngredient.of("dustPyrotheum", 1), getFluidStack("hot_water", 1000), ItemStack.EMPTY, 0,
                "hot_water_from_pyrotheum");
    }
}
