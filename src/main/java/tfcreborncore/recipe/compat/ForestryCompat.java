package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class ForestryCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.FORESTRY.ID,
                Mods.TFC_METALLUM.ID,
                Mods.TFC_TECH.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_REBORN_CORE.ID,
                Mods.IMMERSIVE_ENGINEERING.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // Sweetener
        OreDictionary.registerOre("sweetener",
                RecipeHelper.getItemStack("forestry", "honey_drop"));
        OreDictionary.registerOre("sweetener",
                RecipeHelper.getItemStack("forestry", "honeydew"));
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.FORESTRY.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Minecart with Bee House
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/minecart/bee_house"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "cart.beehouse", 0),
                "B",
                "M",
                'B', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "bee_house"),
                'M', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "minecart"));

        // Minecart with Apiary
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/minecart/apiary"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "cart.beehouse", 1),
                "B",
                "M",
                'B', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "apiary"),
                'M', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "minecart"));

        // Fertilizer
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/fertilizer/ash"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_compound", 0, 9),
                "dustApatite",
                "dustAsh",
                "dustAsh",
                "dustAsh",
                "dustAsh",
                "dustAsh",
                "dustAsh",
                "dustAsh",
                "dustAsh");
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/fertilizer/sand"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_compound", 0, 3),
                "dustApatite",
                "sand",
                "sand");
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/fertilizer/dirt"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_compound", 0, 3),
                "dustApatite",
                "dirt",
                "dirt");

        // Wrench
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/wrench/forestry"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "wrench"),
                "S S",
                " G ",
                " S ",
                'S', "stickLongAnyBronze",
                'G', "gearAnyBronze");

        // Pipette
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/pipette"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "wrench"),
                "R ",
                " B",
                'R', "rubber",
                'B', "blowpipe");

        // Sturdy Casing (Expensive)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/casing/sturdy"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"),
                "GSG",
                "SCS",
                "PSP",
                'G', "gearAnyBronze",
                'S', "sheetAnyBronze",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"),
                'P', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/brass_piston"));
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        // Compost
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/composting/vegetable"),
                IIngredient.of(RecipeHelper.getFluidStack("tannin", 50)),
                IIngredient.of("categoryVegetable"),
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_bio"),
                72);
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/composting/fruit"),
                IIngredient.of(RecipeHelper.getFluidStack("tannin", 50)),
                IIngredient.of("categoryFruit"),
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_bio"),
                72);
    }

    @Override
    public void registerForestryRecipes(FMLLoadCompleteEvent r) {
        // Portable Beealyzer
        ForestryRecipeManager.addCarpenterRecipe(1200,
                ItemStack.EMPTY,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "portable_alyzer"),
                RecipeHelper.getFluidStack("latex", 2000),
                "SPS",
                "SPS",
                "IGI",
                'S', "sheetTin",
                'P', "paneGlass",
                'I', "ingotRedstone",
                'G', "gemNormal");

        // Sturdy Casing
        ForestryRecipeManager.addCarpenterRecipe(1200,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"),
                RecipeHelper.getFluidStack("creosote", 250),
                " S ",
                "SPS",
                " S ",
                'S', "sheetAnyBronze",
                'P', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/brass_piston"));
    }
}
