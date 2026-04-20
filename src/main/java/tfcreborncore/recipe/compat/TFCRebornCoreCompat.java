package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
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
                Mods.IMMERSIVE_ENGINEERING.ID,
                Mods.THERMAL_FOUNDATION.ID,
                Mods.FORESTRY.ID);
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
    public void registerQuernRecipes(IForgeRegistry<QuernRecipe> r) {
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/hematite"));
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/limonite"));
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/malachite"));

        // Wood Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "wood_powder"),
                IIngredient.of("lumber"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/wood_powder"));

        // Coal Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder"),
                IIngredient.of("gemLignite"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/coal_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder_alt"),
                IIngredient.of("gemCoal"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/coal_powder", 0, 2));

        // Snow Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "snow_powder"),
                IIngredient.of(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "snow")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/snow_powder", 0, 2));

        // Obsidian Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "obsidian_powder"),
                IIngredient.of(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/obsidian_powder", 0, 2));

        // Enderpearl Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "enderpearl_powder"),
                IIngredient.of("gemEnder"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/enderpearl_powder"));

        // Certus Quartz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder"),
                IIngredient.of("crystalCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_1"),
                IIngredient.of("crystalPureCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_2"),
                IIngredient.of(RecipeHelper.getItemStack("appliedenergistics2", "material", 1)),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));

        // Hematite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "hematite_powder"),
                IIngredient.of("pileHematite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/hematite"));

        // Limonite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "limonite_powder"),
                IIngredient.of("pileLimonite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/limonite"));

        // Malachite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "malachite_powder"),
                IIngredient.of("pileMalachite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/malachite"));

        // Apatite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "apatite_powder"),
                IIngredient.of("gemApatite"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/apatite_powder"));

        // Bio pulp
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "bio_pulp"),
                IIngredient.of("treeLeaves"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816));
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "bio_pulp_alt"),
                IIngredient.of("treeSapling"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816));

        // Enderpearl Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "enderpearl_ingot_recycling"),
                IIngredient.of("ingotEnder"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/enderpearl_powder"));

        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(r,
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName() + "_2"),
                    IIngredient.of(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName())),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2));

            // Poor Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(r,
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName() + "_3"),
                    IIngredient
                            .of(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1)),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3));

            // Normal Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(r,
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName() + "_5"),
                    IIngredient
                            .of(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0)),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5));

            // Rich Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(r,
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName() + "_7"),
                    IIngredient
                            .of(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2)),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 7));
        }
    }

    @Override
    public void registerSieveRecipes(FMLPostInitializationEvent r) {
        // Remove all
        ExNihiloRecipeManager.removeAllSieveRecipes();
    }

    @Override
    public void registerCrusherRecipes(FMLPostInitializationEvent r) {
        // Remove All
        ImmersiveEngineeringRecipeManager.removeAllCrusherRecipes();

        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Pulverizin
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2),
                    new Object[] {
                            RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0),
                            0.10F
                    },
                    2000);

            // Poor Ore Pulverizing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0),
                            0.15F
                    },
                    3000);

            // Normal Ore Pulverizing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0),
                            0.25F
                    },
                    5000);

            // Rich Ore Pulverizing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 7),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getProductA(), 0),
                            0.35F
                    },
                    7000);
        }

        // Leaves Recycling
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "treeLeaves",
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stick"), 0.25F
                },
                1600);

        // Sapling Recycling
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "treeSapling",
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stick"), 0.25F
                },
                1600);
    }

    @Override
    public void registerForestryRecipes(FMLLoadCompleteEvent r) {
        // Forestry Recipe Removal
        ForestryRecipeManager.removeAllCarpenterRecipes();
    }
}
