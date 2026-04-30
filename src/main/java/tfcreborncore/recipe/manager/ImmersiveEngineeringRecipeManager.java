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

    /**
     * Registers a new crusher recipe with a primary output.
     * <p>
     * Crusher recipes define how an input item or ore dictionary entry is processed
     * into a primary output item. This method creates a {@link CrusherRecipe} using
     * the given input object, primary result, and energy cost, then adds it directly
     * to {@link CrusherRecipe#recipeList}.
     *
     * @param input         The input ingredient, either an {@link ItemStack} or an ore dictionary string.
     * @param primaryResult The main output item produced by the crusher.
     * @param energy        The energy cost required to process the recipe.
     */
    public static void addCrusherRecipe(Object input, ItemStack primaryResult, int energy) {
        CrusherRecipe recipe = new CrusherRecipe(primaryResult, input, energy);
        CrusherRecipe.recipeList.add(recipe);
    }

    /**
     * Registers a new crusher recipe with primary and secondary outputs.
     * <p>
     * This variant allows defining additional secondary outputs, each paired with
     * its drop chance. The {@code secondaryResult} array must contain alternating
     * values of {@link ItemStack} and {@link Float} chance values. The method
     * constructs a {@link CrusherRecipe}, applies the secondary outputs using
     * {@link CrusherRecipe#addToSecondaryOutput(Object...)}, and registers it into
     * {@link CrusherRecipe#recipeList}.
     *
     * @param input           The input ingredient, either an {@link ItemStack} or an ore dictionary string.
     * @param primaryResult   The main output item produced by the crusher.
     * @param secondaryResult An array of alternating output items and chance values.
     * @param energy          The energy cost required to process the recipe.
     */
    public static void addCrusherRecipe(Object input, ItemStack primaryResult, Object[] secondaryResult, int energy) {
        CrusherRecipe recipe = new CrusherRecipe(primaryResult, input, energy).addToSecondaryOutput(secondaryResult);
        CrusherRecipe.recipeList.add(recipe);
    }
}
