package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerManagerAccessor;
import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerRecipeAccessor;

import cofh.core.inventory.ComparableItemStackValidated;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager.PulverizerRecipe;

public class ThermalExpansionRecipeManager {

    /**
     * Removes every Pulverizer recipe currently registered in Thermal Expansion.
     * <p>
     * This performs a hard wipe by clearing the underlying internal recipe map
     * accessed through {@link PulverizerManagerAccessor#getRecipeMap()}. All
     * vanilla, Thermal Foundation, and mod-added Pulverizer recipes are removed.
     * Use this when rebuilding the entire Pulverizer recipe set from scratch.
     */
    public static void removeAllPulverizerRecipes() {
        PulverizerManagerAccessor.getRecipeMap().clear();
    }

    /**
     * Adds a Pulverizer recipe for a specific input {@link ItemStack}.
     * <p>
     * A new {@link PulverizerRecipe} is created using the accessor constructor and
     * inserted directly into Thermal Expansion's internal recipe map. The input is
     * wrapped in a {@link ComparableItemStackValidated} to ensure proper matching
     * behavior within the Pulverizer.
     *
     * @param input           The exact input item for the recipe.
     * @param primaryOutput   The primary output produced by the Pulverizer.
     * @param secondaryOutput Optional secondary output (may be {@code null}).
     * @param secondaryChance Chance (0–100) for the secondary output to appear.
     * @param energy          Energy cost for processing the recipe.
     */
    public static void addPulverizerRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput,
                                           int secondaryChance, int energy) {
        PulverizerRecipe recipe = PulverizerRecipeAccessor.createPulverizerRecipe(input, primaryOutput, secondaryOutput,
                secondaryChance, energy);
        PulverizerManagerAccessor.getRecipeMap().put(new ComparableItemStackValidated(input), recipe);
    }

    /**
     * Adds Pulverizer recipes for all {@link ItemStack}s registered under the given
     * OreDictionary key. Each OreDictionary entry becomes a separate recipe with
     * the same primary output and energy cost.
     * <p>
     * This variant creates single-output recipes with no secondary output.
     *
     * @param oreInputs     The OreDictionary key (e.g. "oreCopper", "ingotTin").
     * @param primaryOutput The primary output for all generated recipes.
     * @param energy        Energy cost for processing each recipe.
     */
    public static void addPulverizerRecipe(String oreInputs, ItemStack primaryOutput, int energy) {
        for (ItemStack input : OreDictionary.getOres(oreInputs)) {
            PulverizerRecipe recipe = PulverizerRecipeAccessor.createPulverizerRecipe(input, primaryOutput,
                    null,
                    0, energy);
            PulverizerManagerAccessor.getRecipeMap().put(new ComparableItemStackValidated(input), recipe);
        }
    }

    /**
     * Adds Pulverizer recipes for all {@link ItemStack}s registered under the given
     * OreDictionary key, supporting both primary and secondary outputs.
     * <p>
     * Each OreDictionary entry becomes a separate recipe with identical output
     * configuration and energy cost.
     *
     * @param oreInputs       The OreDictionary key to expand into multiple inputs.
     * @param primaryOutput   The primary output for the recipe.
     * @param secondaryOutput Optional secondary output (may be {@code null}).
     * @param secondaryChance Chance (0–100) for the secondary output to appear.
     * @param energy          Energy cost for processing each recipe.
     */
    public static void addPulverizerRecipe(String oreInputs, ItemStack primaryOutput, ItemStack secondaryOutput,
                                           int secondaryChance, int energy) {
        for (ItemStack input : OreDictionary.getOres(oreInputs)) {
            PulverizerRecipe recipe = PulverizerRecipeAccessor.createPulverizerRecipe(input, primaryOutput,
                    secondaryOutput,
                    secondaryChance, energy);
            PulverizerManagerAccessor.getRecipeMap().put(new ComparableItemStackValidated(input), recipe);
        }
    }
}
