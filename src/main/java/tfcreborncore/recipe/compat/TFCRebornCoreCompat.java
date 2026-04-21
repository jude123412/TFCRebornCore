package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.ItemRCTool;
import tfcreborncore.objects.items.ItemRCUniversalWeapon;
import tfcreborncore.objects.items.enums.ItemRCMetalType;
import tfcreborncore.objects.items.enums.ItemRCToolType;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfctech.objects.items.metal.ItemTechMetal;

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
        // Brick Mold
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/mold/brick"),
                1,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/brick_mold"),
                "SK",
                'S', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/latex_coated_wood_sheet"),
                'K', "knife");

        // Ore Processing
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/small/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 1),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()));

            // Poor Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/poor/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 2),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1));

            // Normal Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/normal/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 3),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0));

            // Rich Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/rich/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName(), 0, 5),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2));

            // Pile Compressing
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pile/" + type.getPrimaryName()));

            // Cube Compressing
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/cube/" + type.getPrimaryName()));
        }

        // Metal Recipes
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            // Find a block if it exists?
            List<ItemStack> maybeBlock = OreDictionary
                    .getOres("block" + RCItems.toPascalCase(metal.getRegistryName().getPath()));

            // Add a block &
            // uncraft recipe
            if (!maybeBlock.isEmpty()) {
                // Metal Block
                MinecraftRecipeManager.addShapedDamageRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shaped/damage/metal_block/" + metal.getRegistryName().getPath()),
                        8,
                        maybeBlock.get(0),
                        "III",
                        "IHI",
                        "III",
                        'I', "ingot" + RCItems.toPascalCase(metal.getRegistryName().getPath()),
                        'H', "hammer");
            }

            // Tool Recipes
            if (metal.isToolMetal()) {
                // Excavator
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.EXCAVATOR + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.EXCAVATOR).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', ItemRCMetal.get(metal, ItemRCMetalType.EXCAVATOR_HEAD).getDefaultInstance());

                // Mining Hammer
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.MINING_HAMMER + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.MINING_HAMMER).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H',
                        ItemRCMetal.get(metal, ItemRCMetalType.MINING_HAMMER_HEAD).getDefaultInstance());

                // Wire Cutter
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.WIRE_CUTTER + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.WIRE_CUTTER).getDefaultInstance(),
                        "HS",
                        "S ",
                        'S', "stickWood",
                        'H',
                        ItemRCMetal.get(metal, ItemRCMetalType.WIRE_CUTTER_HEAD).getDefaultInstance());

                // Universal Weapon
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID, "crafting/shaped/skill/universal_weapon/" + metal),
                        ItemRCUniversalWeapon.get(metal).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', ItemRCMetal.get(metal, ItemRCMetalType.UNIVERSAL_WEAPON_HEAD).getDefaultInstance());

                // Universal Weapon Head
                MinecraftRecipeManager.addShapelessSkillRecipe(
                        new ResourceLocation(Tags.MODID, "crafting/shapeless/skill/universal_weapon_head/" + metal),
                        ItemRCMetal.get(metal, ItemRCMetalType.UNIVERSAL_WEAPON_HEAD).getDefaultInstance(),
                        "gemAmethyst",
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_UNIVERSAL_WEAPON_HEAD).getDefaultInstance());
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
    public void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {
        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                // General inputs
                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                // Rackwheel Piece
                ItemStack rackwheelPiece = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE));

                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.RACKWHEEL_PIECE +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, rackwheelPiece, metal.getTier(), null, ForgeRule.UPSET_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST, ForgeRule.UPSET_LAST));

                // Rod
                ItemStack rod = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.ROD), 2);

                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.ROD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, rod, metal.getTier(), null, ForgeRule.HIT_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST, ForgeRule.HIT_LAST));

                // Strip
                ItemStack strip = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.STRIP), 2);

                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.STRIP +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, strip, metal.getTier(), null, ForgeRule.HIT_ANY,
                        ForgeRule.HIT_ANY, ForgeRule.SHRINK_ANY));

            }
            if (metal.isToolMetal()) {
                // General inputs
                IIngredient<ItemStack> ingredientIngotDouble = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)));

                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                // Wire Cutter Head
                ItemStack wireCutterHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.WIRE_CUTTER_HEAD));

                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemRCMetalType.WIRE_CUTTER_HEAD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, wireCutterHead, metal.getTier(), null, ForgeRule.BEND_ANY,
                        ForgeRule.HIT_SECOND_LAST, ForgeRule.DRAW_ANY));

                // Unfinished Universal Weapon Head
                ItemStack unfinishedUniversalWeaponHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_UNIVERSAL_WEAPON_HEAD));

                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + "unfinished_universal_weapon_head" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngotDouble, unfinishedUniversalWeaponHead, metal.getTier(), null, ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY, ForgeRule.DRAW_ANY));
            }
        }
    }

    @Override
    public void registerWeldingRecipes(IForgeRegistry<WeldingRecipe> r) {
        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable() && metal.isToolMetal()) {
                IIngredient<ItemStack> ingredientIngotDouble = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)));

                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                // Unfinished Mining Hammer Head
                ItemStack miningHammerHeadUnfinished = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD));
                if (!miningHammerHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.HAMMER_HEAD))),
                            miningHammerHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Mining Hammer Head
                ItemStack miningHammerHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.MINING_HAMMER_HEAD));
                if (!miningHammerHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.MINING_HAMMER_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(
                                    ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD))),
                            miningHammerHead, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Unfinished Excavator Head
                ItemStack excavatorHeadUnfinished = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD));
                if (!excavatorHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHOVEL_HEAD))),
                            excavatorHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Excavator Head
                ItemStack excavatorHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.EXCAVATOR_HEAD));
                if (!excavatorHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.EXCAVATOR_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(
                                    ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD))),
                            excavatorHead, metal.getTier(), SmithingSkill.Type.TOOLS));
            }

            if (metal.isUsable()) {
                IIngredient<ItemStack> ingredientRackwheelPiece = IIngredient
                        .of(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)));

                ItemStack rackwheelHalf = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.RACKWHEEL_HALF));

                IIngredient<ItemStack> ingredientRackwheelHalf = IIngredient
                        .of(rackwheelHalf);

                ItemStack rackwheel = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL));

                // Rackwheel Half
                r.register(new WeldingRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/welding/" + ItemRCMetalType.RACKWHEEL_HALF + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientRackwheelPiece,
                        ingredientRackwheelPiece,
                        rackwheelHalf, metal.getTier(), null));

                // Rackwheel
                r.register(new WeldingRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/welding/" + ItemTechMetal.ItemType.RACKWHEEL + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientRackwheelHalf,
                        ingredientRackwheelHalf,
                        rackwheel, metal.getTier(), null));
            }
        }
    }

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

        // Ore Processing
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

        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {

                // Ingot to Dust
                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                ItemStack dust = new ItemStack(
                        ItemMetal.get(metal, Metal.ItemType.DUST));
                r.register(new QuernRecipe(ingredientIngot, dust).setRegistryName(Tags.MODID,
                        "quern/" + metal.getRegistryName().getPath().toLowerCase() + "/dust"));
            }
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
    public void registerForestryRecipes(FMLPostInitializationEvent r) {
        // Forestry Recipe Removal
        ForestryRecipeManager.removeAllCarpenterRecipes();
        ForestryRecipeManager.removeAllFabricatorRecipes();

        // Thermionic Fabricator Recipes
        // Sand to Molten Glass
        for (ItemStack stack : OreDictionary.getOres("sand")) {
            ForestryRecipeManager.addFabricatorSmeltingRecipe(stack,
                    RecipeHelper.getFluidStack("glass", 1000), 1500);
        }

        // Glass to Molten Glass
        for (ItemStack stack : OreDictionary.getOres("blockGlassColorless")) {
            ForestryRecipeManager.addFabricatorSmeltingRecipe(stack,
                    RecipeHelper.getFluidStack("glass", 1000), 1500);
        }

        // Glass pane to Molten Glass
        for (ItemStack stack : OreDictionary.getOres("paneGlassColorless")) {
            ForestryRecipeManager.addFabricatorSmeltingRecipe(stack,
                    RecipeHelper.getFluidStack("glass", 375), 1500);
        }
    }
}
