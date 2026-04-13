package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class OpenGliderCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.OPEN_GLIDER.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_TECH.ID,
                Mods.MINECRAFT.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.OPEN_GLIDER.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Glider Wing (Left)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/glider/wing_left"),
                RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 0),
                " BL",
                "BLL",
                "LLL",
                'B', "stickAluminium",
                'L', "leather");

        // Glider Wing (Right)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/glider/wing_right"),
                RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 1),
                "LS ",
                "LLS",
                "LLL",
                'S', "stickAluminium",
                'L', "leather");

        // Scaffolding
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/glider/scaffolding"),
                RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 2),
                " S ",
                "S S",
                "SSS",
                'S', "stickAluminium");

        // Basic Hang Glider
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/glider/basic"),
                RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_basic"),
                " B ",
                "LSR",
                " B ",
                'B', "screwAluminium",
                'L', RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 0),
                'S', RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 2),
                'R', RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_part", 1));

        // Advanced Hang Glider
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/glider/advnaced"),
                RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_advanced"),
                "BHB",
                "LSL",
                "BEB",
                'B', "screwAluminium",
                'H', "sheetAluminium",
                'L', "stickLongAluminium",
                'S', RecipeHelper.getItemStack(Mods.OPEN_GLIDER.ID, "hang_glider_basic"),
                'E', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "elytra"));
    }
}
