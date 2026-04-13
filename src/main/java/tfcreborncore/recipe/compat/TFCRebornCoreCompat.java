package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import crazypants.enderio.base.recipe.RecipeBonusType;
import crazypants.enderio.base.recipe.RecipeInput;
import crazypants.enderio.base.recipe.RecipeLevel;
import crazypants.enderio.base.recipe.RecipeOutput;
import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.EnderIORecipeManager;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

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
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.ENDER_IO.ID,
                Mods.THERMAL_FOUNDATION.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // Wrought Iron Block is Iron Block?
        for (ItemStack stack : OreDictionary.getOres("blockIron")) {
            OreDictionary.registerOre("blockWroughtIron", stack);
        }

        // Aluminum or Aluminium?
        // Why not both
        for (ItemStack stack : OreDictionary.getOres("blockAluminum")) {
            OreDictionary.registerOre("blockAluminium", stack);
        }
        for (ItemStack stack : OreDictionary.getOres("blockSheetmetalAluminum")) {
            OreDictionary.registerOre("blockSheetmetalAluminium", stack);
        }
        for (ItemStack stack : OreDictionary.getOres("ingotAluminum")) {
            OreDictionary.registerOre("ingotAluminium", stack);
        }
        for (ItemStack stack : OreDictionary.getOres("dustAluminum")) {
            OreDictionary.registerOre("dustAluminium", stack);
        }
        for (ItemStack stack : OreDictionary.getOres("fenceAluminum")) {
            OreDictionary.registerOre("fenceAluminium", stack);
        }
        for (ItemStack stack : OreDictionary.getOres("trapdoorAluminum")) {
            OreDictionary.registerOre("trapdoorAluminium", stack);
        }
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        for (Metal type : TFCRegistries.METALS.getValuesCollection()) {

            // Remove Block Recipes if they exist
            for (ItemStack oreBlocks : OreDictionary
                    .getOres("block" + RCItems.toPascalCase(type.getRegistryName().getPath()))) {
                if (!oreBlocks.isEmpty()) MinecraftRecipeManager.removeRecipeByOutput(oreBlocks);
            }

            // Remove Ingot Recipes if they exist
            for (ItemStack ingots : OreDictionary
                    .getOres("ingot" + RCItems.toPascalCase(type.getRegistryName().getPath()))) {
                if (!ingots.isEmpty()) MinecraftRecipeManager.removeRecipeByOutput(ingots);
            }
        }
    }

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

        // Simple code to remove and
        // add block recipes for metals
        for (Metal type : TFCRegistries.METALS.getValuesCollection()) {
            // Find a block if it exists?
            List<ItemStack> maybeBlock = OreDictionary
                    .getOres("block" + RCItems.toPascalCase(type.getRegistryName().getPath()));

            // Add a block &
            // uncraft recipe
            if (!maybeBlock.isEmpty()) {
                // Metal Block
                MinecraftRecipeManager.addShapedDamageRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shaped/damage/metal_block/" + type.getRegistryName().getPath()),
                        8,
                        maybeBlock.get(0),
                        "III",
                        "IHI",
                        "III",
                        'I', "ingot" + RCItems.toPascalCase(type.getRegistryName().getPath()),
                        'H', "hammer");
            }
        }
    }

    @Override
    public void registerItemMetal(FMLPostInitializationEvent r) {
        for (Metal type : TFCRegistries.METALS.getValuesCollection()) {

            // Find a block if it exists?
            List<ItemStack> maybeBlock = OreDictionary
                    .getOres("block" + RCItems.toPascalCase(type.getRegistryName().getPath()));

            // Add Item Metal to block
            for (ItemStack block : maybeBlock) {
                if (!block.isEmpty()) TerrafirmacraftRecipeManager.addItemMetal(block, type, 800, true);
            }
        }
    }

    @Override
    public void registerWeldingRecipes(IForgeRegistry<WeldingRecipe> r) {}

    @Override
    public void registerSieveRecipes(FMLPostInitializationEvent r) {
        // Remove all
        ExNihiloRecipeManager.removeAllSieveRecipes();
    }

    @Override
    public void registerSagMillRecipes(FMLPostInitializationEvent r) {
        // Remove All
        EnderIORecipeManager.removeAllSagMillRecipes();

        for (OreProcessingTypes type : OreProcessingTypes.values()) {

            // Small Ore Pulverizing
            EnderIORecipeManager.registerSagMillRecipe(
                    new RecipeInput(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName())),
                    new RecipeOutput[] {
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2)
                                    .copy(), 1.00F),
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0).copy(),
                                    0.10F)
                    },
                    2000,
                    RecipeBonusType.MULTIPLY_OUTPUT,
                    RecipeLevel.IGNORE);

            // Poor Ore Pulverizing
            EnderIORecipeManager.registerSagMillRecipe(
                    new RecipeInput(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1)),
                    new RecipeOutput[] {
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3)
                                    .copy(), 1.00F),
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0).copy(),
                                    0.15F)
                    },
                    3000,
                    RecipeBonusType.MULTIPLY_OUTPUT,
                    RecipeLevel.IGNORE);

            // Normal Ore Pulverizing
            EnderIORecipeManager.registerSagMillRecipe(
                    new RecipeInput(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0)),
                    new RecipeOutput[] {
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5)
                                    .copy(), 1.00F),
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0).copy(),
                                    0.25F)
                    },
                    5000,
                    RecipeBonusType.MULTIPLY_OUTPUT,
                    RecipeLevel.IGNORE);

            // Rich Ore Pulverizing
            EnderIORecipeManager.registerSagMillRecipe(
                    new RecipeInput(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2)),
                    new RecipeOutput[] {
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 7)
                                    .copy(), 1.00F),
                            new RecipeOutput(RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0).copy(),
                                    0.35F)
                    },
                    7000,
                    RecipeBonusType.MULTIPLY_OUTPUT,
                    RecipeLevel.IGNORE);

            // Leaves Recycling
            EnderIORecipeManager.registerSagMillRecipe(
                    "treeLeaves",
                    new RecipeOutput[] {
                            new RecipeOutput(
                                    RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816).copy(),
                                    1.00F),
                            new RecipeOutput(RecipeHelper.getItemStack(Mods.ENDER_IO.ID, "item_material", 46).copy(),
                                    0.20F),
                            new RecipeOutput(RecipeHelper.getItemStack(Mods.ENDER_IO.ID, "item_material", 46).copy(),
                                    0.10F)
                    },
                    1600,
                    RecipeBonusType.CHANCE_ONLY,
                    RecipeLevel.IGNORE);

            // Sapling Recycling
            EnderIORecipeManager.registerSagMillRecipe(
                    "treeSapling",
                    new RecipeOutput[] {
                            new RecipeOutput(
                                    RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816).copy(),
                                    1.00F),
                            new RecipeOutput(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stick").copy(), 0.25F),
                            new RecipeOutput(RecipeHelper.getItemStack(Mods.ENDER_IO.ID, "item_material", 47).copy(),
                                    0.10F)
                    },
                    1600,
                    RecipeBonusType.CHANCE_ONLY,
                    RecipeLevel.IGNORE);
        }
    }

    @Override
    public void registerCrusherRecipes(FMLPostInitializationEvent r) {
        // Remove All
        ImmersiveEngineeringRecipeManager.removeAllCrusherRecipes();
    }
}
