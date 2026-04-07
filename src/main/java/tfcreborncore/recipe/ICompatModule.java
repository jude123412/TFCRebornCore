package tfcreborncore.recipe;

import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;

public interface ICompatModule {

    List<String> dependencies();

    default void registerOreDictionaries(RegistryEvent.Register<IRecipe> event) {}

    default void registerCraftingRecipe(RegistryEvent.Register<IRecipe> event) {}

    default void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {}

    default void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {}

    default boolean areRecipesLoadable() {
        for (String dep : dependencies()) {
            if (!Loader.isModLoaded(dep)) {
                return false;
            }
        }
        return true;
    }
}
