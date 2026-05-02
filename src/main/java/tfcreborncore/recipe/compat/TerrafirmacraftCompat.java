package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfcreborncore.types.DefaultMetals;

public class TerrafirmacraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TERRAFIRMACRAFT.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {}

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {
        // Fire Brick
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/mold/brick/fire"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ceramics/unfired/fire_brick"),
                "fireClay",
                "brickMold");

        // Clay Brick
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/mold/brick/clay"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ceramics/unfired/clay_brick"),
                "clay",
                "brickMold");
    }

    @Override
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Brass Mechanisms
        TerrafirmacraftRecipeManager.addItemMetal(
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "brass_mechanisms"), DefaultMetals.BRASS,
                50, true);
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Redstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "redstone_ingot_recycling"),
                IIngredient.of("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"));

        // Glowstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "glowstone_ingot_recycling"),
                IIngredient.of("ingotGlowstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"));
    }
}
