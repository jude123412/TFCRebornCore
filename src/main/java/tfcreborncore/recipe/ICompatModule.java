package tfcreborncore.recipe;

import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;

public interface ICompatModule {

    List<String> dependancies();

    default void registerCraftingRecipe(RegistryEvent.Register<IRecipe> event) {}

    default void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {}

    default void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {}

    default boolean areRecipesLoadable() {
        for (String dep : dependancies()) {
            if (!Loader.isModLoaded(dep)) {
                return false;
            }
        }
        return true;
    }
}
