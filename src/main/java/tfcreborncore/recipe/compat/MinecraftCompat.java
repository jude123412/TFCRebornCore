package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;

public class MinecraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.MINECRAFT.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack("minecraft", "ender_pearl"));
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        RecipeHelper.removeRecipeByOutput(r, getItemStack(Mods.MINECRAFT.ID, "blaze_powder"));
    }
}
