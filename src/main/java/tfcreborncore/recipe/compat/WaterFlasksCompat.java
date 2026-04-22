package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class WaterFlasksCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TERRAFIRMACRAFT.ID,
                Mods.WATER_FLASKS.ID);
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Unfired Ceramic Sheet
        TerrafirmacraftRecipeManager.addKnappingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "knapping/leather/leather_bladder"),
                KnappingType.LEATHER,
                RecipeHelper.getItemStack(Mods.WATER_FLASKS.ID, "bladder"),
                " XXX ",
                "XXXXX",
                "XXXXX",
                " XXX ",
                "  X  ");
    }
}
