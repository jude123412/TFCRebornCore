package tfcreborncore.recipe;

import java.util.ArrayList;
import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;

import net.minecraftforge.registries.IForgeRegistryModifiable;
import tfcreborncore.recipe.compat.ExNihiloCompat;
import tfcreborncore.recipe.compat.ForestryCompat;
import tfcreborncore.recipe.compat.ThermalExpansionCompat;

public final class CompatManager {

    private static final List<ICompatModule> modules = new ArrayList<>();

    public static void init() {
        modules.add(new ForestryCompat());
        modules.add(new ThermalExpansionCompat());
        modules.add(new ExNihiloCompat());
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
}
