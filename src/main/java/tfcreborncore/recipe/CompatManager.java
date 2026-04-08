package tfcreborncore.recipe;

import java.util.ArrayList;
import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.compat.ExNihiloCompat;
import tfcreborncore.recipe.compat.ForestryCompat;
import tfcreborncore.recipe.compat.MinecraftCompat;
import tfcreborncore.recipe.compat.StorageDrawersCompat;
import tfcreborncore.recipe.compat.TFCTechCompat;
import tfcreborncore.recipe.compat.ThermalExpansionCompat;

public final class CompatManager {

    private static final List<ICompatModule> modules = new ArrayList<>();

    public static void init() {
        modules.add(new ForestryCompat());
        modules.add(new ThermalExpansionCompat());
        modules.add(new ExNihiloCompat());
        modules.add(new TFCTechCompat());
        modules.add(new MinecraftCompat());
        modules.add(new StorageDrawersCompat());
    }

    public static void loadOreDictionaries(RegistryEvent.Register<IRecipe> event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) module.registerOreDictionaries(event);
        }
    }

    public static void loadCraftingRecipes(RegistryEvent.Register<IRecipe> event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) module.registerCraftingRecipe(event);
        }
    }

    public static void loadBarrelRecipes(RegistryEvent.Register<BarrelRecipe> event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) module.registerBarrelRecipes(event.getRegistry());
        }
    }

    public static void loadAnvilRecipes(RegistryEvent.Register<AnvilRecipe> event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) module.registerAnvilRecipes(event.getRegistry());
        }
    }

    public static void loadSieveRecipes(FMLPostInitializationEvent event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) {
                module.registerSieveRecipes(event);
            }
        }
    }
}
