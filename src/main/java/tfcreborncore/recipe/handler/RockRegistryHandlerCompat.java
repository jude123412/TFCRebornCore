package tfcreborncore.recipe.handler;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ForestryRecipeManager;

public class RockRegistryHandlerCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_DECORATION.ID,
                Mods.FORESTRY.ID);
    }

    @Override
    public void registerForestryRecipes(FMLPostInitializationEvent r) {
        for (Rock rock : TFCRegistries.ROCKS) {
            ForestryRecipeManager.addMoistenerRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID,
                            "bricks/" + rock.getRegistryName().getPath().toLowerCase()),
                    RecipeHelper.getItemStack(Mods.TFC_DECORATION.ID,
                            "mossy_bricks/" + rock.getRegistryName().getPath().toLowerCase()),
                    8);
        }
    }
}
