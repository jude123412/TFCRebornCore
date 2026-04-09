package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;

import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerManagerAccessor;
import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerRecipeAccessor;

import cofh.core.inventory.ComparableItemStackValidated;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager.PulverizerRecipe;

public class ThermalExpansionRecipeManager {

    // Remove all PulverizerRecipes
    public static void removeAllPulverizerRecipes() {
        PulverizerManagerAccessor.getRecipeMap().clear();
    }

    // Add Pulverizer Recipe
    public static void addPulverizerRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput,
                                           int secondaryChance, int energy) {
        PulverizerRecipe recipe = PulverizerRecipeAccessor.createPulverizerRecipe(input, primaryOutput, secondaryOutput,
                secondaryChance, energy);
        PulverizerManagerAccessor.getRecipeMap().put(new ComparableItemStackValidated(input), recipe);
    }
}
