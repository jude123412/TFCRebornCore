package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;

public class StorageDrawersCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.STORAGE_DRAWERS.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> e) {
        OreDictionary.registerOre("drawerFramed",
                RecipeHelper.getItemStack("storagedrawers", "customdrawers", OreDictionary.WILDCARD_VALUE));
    }
}
