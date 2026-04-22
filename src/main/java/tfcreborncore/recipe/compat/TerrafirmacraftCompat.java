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
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfcreborncore.types.DefaultMetals;

public class TerrafirmacraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_REBORN_CORE.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {}

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
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
}
