package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerManagerAccessor;
import com.cleanroommc.groovyscript.core.mixin.thermalexpansion.PulverizerRecipeAccessor;

import cofh.core.inventory.ComparableItemStackValidated;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager.PulverizerRecipe;

public class ThermalExpansionRecipeManager {

    public static void removeAllPulverizerRecipes() {
        PulverizerManagerAccessor.getRecipeMap().clear();
    }

    public static void addPulverizerRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput,
                                           int secondaryChance, int energy) {
        PulverizerRecipe recipe = PulverizerRecipeAccessor.createPulverizerRecipe(input, primaryOutput, secondaryOutput,
                secondaryChance, energy);
        PulverizerManagerAccessor.getRecipeMap().put(new ComparableItemStackValidated(input), recipe);
    }

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
