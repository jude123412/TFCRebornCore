package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class ForestryCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.FORESTRY.getName());
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // Sweetener
        OreDictionary.registerOre("sweetener",
                RecipeHelper.getItemStack("forestry", "honey_drop"));
        OreDictionary.registerOre("sweetener",
                RecipeHelper.getItemStack("forestry", "honeydew"));
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        MinecraftRecipeManager.removeRecipeByModGroup(r, Mods.FORESTRY.ID);
    }
}
