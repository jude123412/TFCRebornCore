package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.Tags;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class TFCRebornCoreCompat implements ICompatModule {

    /*
     * Please register remove all here
     * as it is the first Module to get
     * loaded!
     */
    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.MINECRAFT.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_TECH.ID,
                Mods.TFC_METALLUM.ID,
                Mods.EX_NIHILO_CREATIO.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Tags.MODID,
                            "crafting/shapeless/damage/smashing/ore/small/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 1),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()));

            // Poor Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Tags.MODID,
                            "crafting/shapeless/damage/smashing/ore/poor/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1));

            // Normal Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Tags.MODID,
                            "crafting/shapeless/damage/smashing/ore/normal/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0));

            // Rich Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Tags.MODID,
                            "crafting/shapeless/damage/smashing/ore/rich/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2));

            // Pile Compressing
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Tags.MODID, "crafting/shapeless/compressing/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()));

            // Cube Compressing
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Tags.MODID, "crafting/shapeless/compressing/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()));
        }
    }

    @Override
    public void registerSieveRecipes(FMLPostInitializationEvent r) {
        // Remove all Sieve Recipes
        ExNihiloRecipeManager.removeAllSieveRecipes();
    }

    // TODO : Fix this :D
    // Small Ore Pulverizing
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0),
    // 10,
    // 2000);
    //
    // // Poor Ore Pulverizing
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA()),
    // 15,
    // 3000);
    //
    // // Normal Ore Pulverizing
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA()),
    // 25,
    // 5000);
    //
    // // Rich Ore Pulverizing
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 7),
    // RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA()),
    // 35,
    // 7000);
    // }
    //
    // // Leaves Recycling
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // "treeLeaves",
    // RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816),
    // 1600);
    //
    // // Sapling Recycling
    // ThermalExpansionRecipeManager.addPulverizerRecipe(
    // "treeSapling",
    // RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816),
    // RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stick"),
    // 25,
    // 1600);
}
