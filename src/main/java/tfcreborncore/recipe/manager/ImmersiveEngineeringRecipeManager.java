package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;

import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;

public class ImmersiveEngineeringRecipeManager {

    /**
     * Removes all registered Crusher recipes.
     * <p>
     * This performs a complete reset of the Crusher processing system by clearing
     * the static {@link CrusherRecipe#recipeList}. All default and mod-added
     * Crusher recipes are removed, leaving the Crusher with no processing
     * definitions until new recipes are registered.
     * <p>
     * Use this when replacing the entire Crusher recipe set or implementing a
     * custom progression overhaul.
     */
    public static void removeAllCrusherRecipes() {
        CrusherRecipe.recipeList.clear();
    }

    public static void addCrusherRecipe(Object input, ItemStack primaryResult, Object[] secondaryResult, int energy) {
        CrusherRecipe recipe = new CrusherRecipe(primaryResult, input, energy).addToSecondaryOutput(secondaryResult);
        CrusherRecipe.recipeList.add(recipe);
    }
}
