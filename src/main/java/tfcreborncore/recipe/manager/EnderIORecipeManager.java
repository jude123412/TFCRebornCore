package tfcreborncore.recipe.manager;

import com.enderio.core.common.util.stackable.Things;

import crazypants.enderio.base.recipe.Recipe;
import crazypants.enderio.base.recipe.RecipeBonusType;
import crazypants.enderio.base.recipe.RecipeInput;
import crazypants.enderio.base.recipe.RecipeLevel;
import crazypants.enderio.base.recipe.RecipeOutput;
import crazypants.enderio.base.recipe.ThingsRecipeInput;
import crazypants.enderio.base.recipe.sagmill.SagMillRecipeManager;

public class EnderIORecipeManager {

    /**
     * Removes every SAG Mill recipe currently registered in Ender IO.
     * <p>
     * This performs a full reset by clearing the internal recipe list returned by
     * {@link SagMillRecipeManager#getRecipes()}. All default Ender IO recipes and
     * any mod-added SAG Mill recipes are removed. Use this when replacing or
     * rebuilding the entire SAG Mill processing chain.
     */
    public static void removeAllSagMillRecipes() {
        SagMillRecipeManager.getInstance().getRecipes().clear();
    }

    /**
     * Registers a new SAG Mill recipe using a concrete {@link RecipeInput}.
     * <p>
     * A new {@link Recipe} instance is constructed with the provided input,
     * outputs, energy cost, bonus type, and recipe level. The recipe is then
     * registered through {@link SagMillRecipeManager#addRecipe(Recipe)}.
     * <p>
     * This variant is used when the input is a specific item, metadata, or NBT
     * value rather than an OreDictionary entry.
     *
     * @param input       The concrete input definition for the recipe.
     * @param result      The array of possible outputs (primary and optional secondary).
     * @param energy      The energy cost required to process the recipe.
     * @param bonusType   Determines how grinding balls affect output chances.
     * @param recipeLevel Controls recipe visibility and override priority.
     */
    public static void registerSagMillRecipe(RecipeInput input, RecipeOutput[] result, int energy,
                                             RecipeBonusType bonusType, RecipeLevel recipeLevel) {
        Recipe recipe = new Recipe(new RecipeInput(input), energy, bonusType, recipeLevel, result);
        SagMillRecipeManager.getInstance().addRecipe(recipe);
    }

    /**
     * Registers a new SAG Mill recipe using an OreDictionary key as the input.
     * <p>
     * This creates a {@link ThingsRecipeInput} backed by a {@link Things} wrapper
     * around the OreDictionary name. All items matching the OreDictionary entry
     * become valid inputs for the recipe. The resulting {@link Recipe} is then
     * added to the SAG Mill recipe manager.
     * <p>
     * Use this variant when you want all modded variants of a material (e.g.
     * "oreCopper", "ingotLead") to share the same SAG Mill processing behavior.
     *
     * @param oreInput    The OreDictionary key representing valid input items.
     * @param result      The array of outputs produced by the recipe.
     * @param energy      The energy cost required to process the recipe.
     * @param bonusType   Determines how grinding balls affect output chances.
     * @param recipeLevel Controls recipe visibility and override priority.
     */
    public static void registerSagMillRecipe(String oreInput, RecipeOutput[] result, int energy,
                                             RecipeBonusType bonusType, RecipeLevel recipeLevel) {
        Recipe recipe = new Recipe(new ThingsRecipeInput(new Things(oreInput)), energy, bonusType, recipeLevel, result);
        SagMillRecipeManager.getInstance().addRecipe(recipe);
    }
}
