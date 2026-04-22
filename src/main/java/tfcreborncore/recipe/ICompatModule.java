package tfcreborncore.recipe;

import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public interface ICompatModule {

    List<String> dependencies();

    default void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    default void registerRecipeRemoval(FMLPostInitializationEvent r) {}

    default void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {}

    default void registerItemModification(FMLPostInitializationEvent r) {}

    default void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {}

    default void registerExNihiloRecipes(FMLPostInitializationEvent r) {}

    default void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {}

    default void registerForestryRecipes(FMLPostInitializationEvent r) {}

    default void registerTFCTechRecipes(FMLPostInitializationEvent r) {}

    default boolean areRecipesLoadable() {
        for (String dep : dependencies()) {
            if (!Loader.isModLoaded(dep)) {
                return false;
            }
        }
        return true;
    }
}
