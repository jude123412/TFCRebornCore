package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;
import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.recipes.RecipeUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import net.minecraftforge.registries.IForgeRegistryModifiable;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.mods.TFCRecipeHelper;

public class ExNihiloCompat implements ICompatModule {

    @Override
    public List<String> dependancies() {
        return Arrays.asList(
                Mods.EX_NIHILO_CREATIO.getName());
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable) event.getRegistry();

        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "hammer_wood", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "hammer_stone", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "hammer_iron", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "hammer_diamond", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "hammer_gold", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.getName(), "item_mesh", OreDictionary.WILDCARD_VALUE));
    }

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
