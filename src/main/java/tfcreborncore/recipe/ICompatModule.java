package tfcreborncore.recipe;

import java.util.List;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public interface ICompatModule {

    List<String> dependencies();

    default void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    default void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {}

    default void registerItemMetal(FMLPostInitializationEvent r) {}

    default void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {}

    default void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {}

    default void registerWeldingRecipes(IForgeRegistry<WeldingRecipe> r) {}

    default void registerQuernRecipes(IForgeRegistry<QuernRecipe> r) {}

    default void registerSieveRecipes(FMLPostInitializationEvent r) {}

    default void registerSagMillRecipes(FMLPostInitializationEvent r) {}

    default void registerCrusherRecipes(FMLPostInitializationEvent r) {}

    default boolean areRecipesLoadable() {
        for (String dep : dependencies()) {
            if (!Loader.isModLoaded(dep)) {
                return false;
            }
        }
        return true;
    }
}
