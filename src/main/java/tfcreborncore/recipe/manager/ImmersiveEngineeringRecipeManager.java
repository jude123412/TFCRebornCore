package tfcreborncore.recipe.manager;

import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import net.minecraft.item.ItemStack;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import blusunrize.immersiveengineering.api.crafting.MetalPressRecipe;

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

    /**
     * Removes all Metal Press recipes.
     * <p>
     * This clears the internal {@link MetalPressRecipe#recipeList}, removing all
     * default and mod‑added Metal Press recipes. Use this when replacing or
     * rebuilding the entire Metal Press processing set.
     */
    public static void removeAllMetalPressRecipes() {
        MetalPressRecipe.recipeList.clear();
    }

    /**
     * Registers a new Metal Press recipe.
     * <p>
     * Metal Press recipes define how an input ingredient is shaped using a
     * specific mold. This variant accepts a flexible input type, allowing either
     * an {@link ItemStack}, an item or block instance, or an ore dictionary
     * string. The method constructs a {@link MetalPressRecipe} using the provided
     * input, output item, mold, and energy cost, then registers it into
     * {@link MetalPressRecipe#recipeList} using the mold as the lookup key.
     *
     * @param input  The input ingredient, either an {@link ItemStack}, an item/block,
     *               or an ore dictionary string.
     * @param result The item produced by the Metal Press.
     * @param mold   The mold required for the recipe.
     * @param energy The energy cost required to process the recipe.
     */
    public static void addMetalPressRecipe(Object input, ItemStack result, ItemStack mold,
                                           int energy) {
        MetalPressRecipe recipe = new MetalPressRecipe(result, input, new ComparableItemStack(mold), energy);
        MetalPressRecipe.recipeList.put(new ComparableItemStack(mold), recipe);
    }

    /**
     * Registers a new Metal Press recipe with a specified input amount.
     *
     * This variant accepts a flexible input type—either an {@link ItemStack},
     * an item or block instance, or an ore dictionary string—and applies an
     * explicit input size to the recipe. The method constructs a
     * {@link MetalPressRecipe} using the provided input, output item, mold,
     * and energy cost, sets the required input amount, and registers the recipe
     * into {@link MetalPressRecipe#recipeList} using the mold as the lookup key.
     *
     * @param input       The input ingredient, either an {@link ItemStack}, an item/block,
     *                    or an ore dictionary string.
     * @param inputAmount The required amount of the input ingredient.
     * @param result      The item produced by the Metal Press.
     * @param mold        The mold required for the recipe.
     * @param energy      The energy cost required to process the recipe.
     */
    public static void addMetalPressRecipe(Object input, int inputAmount, ItemStack result, ItemStack mold,
                                           int energy) {
        MetalPressRecipe recipe = new MetalPressRecipe(result, input, new ComparableItemStack(mold), energy)
                .setInputSize(inputAmount);
        MetalPressRecipe.recipeList.put(new ComparableItemStack(mold), recipe);
    }

    /**
     * Removes all Arc Furnace recipes.
     * <p>
     * This clears the internal {@link ArcFurnaceRecipe#recipeList}, removing all
     * default and mod‑added Arc Furnace recipes. Use this when replacing or
     * rebuilding the entire Arc Furnace processing set.
     */
    public static void removeAllArcFurnaceRecipes() {
        ArcFurnaceRecipe.recipeList.clear();
    }

    /**
     * Registers a new Arc Furnace recipe.
     * <p>
     * Arc Furnace recipes define how an input ingredient is smelted or refined
     * using optional additive items. This method constructs an
     * {@link ArcFurnaceRecipe} using the provided input, additives, primary
     * output, slag output, processing time, and per‑tick energy cost, then
     * registers it into {@link ArcFurnaceRecipe#recipeList}.
     *
     * @param input          The main input ingredient, either an {@link ItemStack},
     *                       an item/block, or an ore dictionary string.
     * @param additives      Optional additive ingredients that modify the recipe.
     * @param result         The primary output item produced by the Arc Furnace.
     * @param slag           The slag byproduct produced by the recipe.
     * @param time           The total processing time in ticks.
     * @param energyPerTick  The energy consumed per tick while processing.
     */
    public static void addArcFurnaceRecipe(Object input, Object[] additives, ItemStack result,
                                           ItemStack slag, int time, int energyPerTick) {
        ArcFurnaceRecipe recipe = new ArcFurnaceRecipe(result, input, slag, time, energyPerTick, additives);
        ArcFurnaceRecipe.recipeList.add(recipe);
    }

}
