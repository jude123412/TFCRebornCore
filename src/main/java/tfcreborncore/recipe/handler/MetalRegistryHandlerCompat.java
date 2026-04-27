package tfcreborncore.recipe.handler;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.objects.items.ItemRCAnyMetal;
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.ItemRCTool;
import tfcreborncore.objects.items.ItemRCUniversalWeapon;
import tfcreborncore.objects.items.enums.ItemRCAnyMetalType;
import tfcreborncore.objects.items.enums.ItemRCMetalType;
import tfcreborncore.objects.items.enums.ItemRCToolType;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfctech.objects.items.metal.ItemTechMetal;

public class MetalRegistryHandlerCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_METALLUM.ID);
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
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Metal Processing
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
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                // Pipe Frame
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "anvil/working/pipe_frame/" + metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)),
                        RecipeHelper.getItemStack(ItemRCAnyMetal.get(metal, ItemRCAnyMetalType.PIPE_FRAME)),
                        metal.getTier(),
                        null,
                        ForgeRule.DRAW_NOT_LAST,
                        ForgeRule.BEND_NOT_LAST,
                        ForgeRule.HIT_LAST);

                // Rackwheel Half
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(
                                Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemRCMetalType.RACKWHEEL_HALF + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)),
                        RecipeHelper.getIIngredient(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)),
                        RecipeHelper.getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.RACKWHEEL_HALF)),
                        metal.getTier(),
                        null);

                // Ingot to Dust
                TerrafirmacraftRecipeManager.addQuernRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "quern/" + metal.getRegistryName().getPath().toLowerCase() + "/dust"),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemMetal.get(metal, Metal.ItemType.DUST)));
            }

            if (metal.isToolMetal()) {
                // Wire Cutter Head
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(
                                Tags.MODID,
                                "anvil/working/" + ItemRCMetalType.WIRE_CUTTER_HEAD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.WIRE_CUTTER_HEAD)),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.HIT_SECOND_LAST,
                        ForgeRule.DRAW_ANY);

                // Unfinished Universal Weapon Head
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(
                                Tags.MODID,
                                "anvil/working/unfinished_universal_weapon_head" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)),
                        RecipeHelper
                                .getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_UNIVERSAL_WEAPON_HEAD)),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY,
                        ForgeRule.DRAW_NOT_LAST);
            }

            if (metal.isUsable() && metal.isToolMetal()) {
                // Unfinished Mining Hammer Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(
                                Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.HAMMER_HEAD)),
                        RecipeHelper
                                .getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD)),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Mining Hammer Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(
                                Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemRCMetalType.MINING_HAMMER_HEAD + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)),
                        RecipeHelper
                                .getIIngredient(ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD)),
                        RecipeHelper.getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.MINING_HAMMER_HEAD)),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Unfinished Excavator Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(
                                Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.SHOVEL_HEAD)),
                        RecipeHelper.getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD)),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Excavator Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(
                                Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemRCMetalType.EXCAVATOR_HEAD + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getIIngredient(ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD)),
                        RecipeHelper.getItemStack(ItemRCMetal.get(metal, ItemRCMetalType.EXCAVATOR_HEAD)),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);
            }

            // Electron Tube Base
            TerrafirmacraftRecipeManager.addAnvilRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "anvil/working/electron_tube_base/" + metal.getRegistryName().getPath().toLowerCase()),
                    RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                    RecipeHelper.getItemStack(ItemRCAnyMetal.get(metal, ItemRCAnyMetalType.ELECTRON_TUBE_BASE)),
                    metal.getTier(),
                    null,
                    ForgeRule.DRAW_ANY,
                    ForgeRule.DRAW_ANY,
                    ForgeRule.HIT_NOT_LAST);
        }
    }
}
