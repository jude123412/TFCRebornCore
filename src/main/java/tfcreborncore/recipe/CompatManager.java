package tfcreborncore.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.compat.BaubleliciousCompat;
import tfcreborncore.recipe.compat.BinniesCompat;
import tfcreborncore.recipe.compat.ExNihiloCompat;
import tfcreborncore.recipe.compat.ForestryCompat;
import tfcreborncore.recipe.compat.MinecraftCompat;
import tfcreborncore.recipe.compat.OpenGliderCompat;
import tfcreborncore.recipe.compat.StorageDrawersCompat;
import tfcreborncore.recipe.compat.TFCRebornCoreCompat;
import tfcreborncore.recipe.compat.TFCTechCompat;
import tfcreborncore.recipe.compat.TerrafirmacraftCompat;
import tfcreborncore.recipe.compat.ThermalExpansionCompat;
import tfcreborncore.recipe.compat.WaterFlasksCompat;
import tfcreborncore.recipe.handler.MetalRegistryHandlerCompat;
import tfcreborncore.recipe.handler.OreRegistryHandlerCompat;

public final class CompatManager {

    private static final List<ICompatModule> modules = new ArrayList<>();

    public static void init() {
        modules.add(new TFCRebornCoreCompat());
        modules.add(new ForestryCompat());
        modules.add(new ExNihiloCompat());
        modules.add(new TFCTechCompat());
        modules.add(new MinecraftCompat());
        modules.add(new StorageDrawersCompat());
        modules.add(new BaubleliciousCompat());
        modules.add(new ThermalExpansionCompat());
        modules.add(new BinniesCompat());
        modules.add(new OpenGliderCompat());
        modules.add(new TerrafirmacraftCompat());
        modules.add(new WaterFlasksCompat());
        modules.add(new OreRegistryHandlerCompat());
        modules.add(new MetalRegistryHandlerCompat());
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

    public static void loadModuleRecipes(FMLPostInitializationEvent event) {
        for (ICompatModule module : modules) {
            if (module.areRecipesLoadable()) {
                module.registerRecipeRemoval(event);
                module.registerItemModification(event);
                module.registerTerrafirmacraftRecipes(event);
                module.registerExNihiloRecipes(event);
                module.registerImmersiveEngineeringRecipes(event);
                module.registerForestryRecipes(event);
                module.registerTFCTechRecipes(event);
            }
        }
    }
}
