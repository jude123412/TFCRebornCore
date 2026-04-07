package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;

public class MinecraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.MINECRAFT.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> event) {
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack("minecraft", "ender_pearl"));
    }
}
