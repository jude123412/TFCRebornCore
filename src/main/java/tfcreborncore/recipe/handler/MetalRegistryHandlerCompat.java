package tfcreborncore.recipe.handler;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
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
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class MetalRegistryHandlerCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_TECH.ID);
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
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            String name = metal.getRegistryName().getPath();

            // Remove Block Recipes if they exist
            for (ItemStack oreBlocks : OreDictionary
                    .getOres("block" + RCItems.toPascalCase(name))) {
                if (!oreBlocks.isEmpty()) MinecraftRecipeManager.removeRecipeByOutput(oreBlocks);
            }

            // Remove Ingot Recipes if they exist
            for (ItemStack ingots : OreDictionary
                    .getOres("ingot" + RCItems.toPascalCase(name))) {
                if (!ingots.isEmpty()) MinecraftRecipeManager.removeRecipeByOutput(ingots);
            }

            if (metal.isUsable()) {
                MinecraftRecipeManager.removeRecipeByOutput(RecipeHelper.getItemStack(Mods.TFC_TECH.ID,
                        "metal/" + name + "_strip"));
                MinecraftRecipeManager.removeRecipeByOutput(RecipeHelper.getItemStack(Mods.TFC_TECH.ID,
                        "metal/" + name + "_rod"));
                MinecraftRecipeManager.removeRecipeByOutput(RecipeHelper.getItemStack(Mods.TFC_TECH.ID,
                        "metal/" + name + "_bolt"));
                MinecraftRecipeManager.removeRecipeByOutput(RecipeHelper.getItemStack(Mods.TFC_TECH.ID,
                        "metal/" + name + "_screw"));
                MinecraftRecipeManager.removeRecipeByOutput(
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel"));
            }
        }
    }

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {
        // Metal Recipes
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            String name = metal.getRegistryName().getPath();

            // Find a block if it exists?
            List<ItemStack> maybeBlock = OreDictionary
                    .getOres("block" + RCItems.toPascalCase(name));

            // Add a block recipe
            if (!maybeBlock.isEmpty()) {
                // Metal Block
                MinecraftRecipeManager.addShapedDamageRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shaped/damage/metal/block/" + name),
                        8,
                        maybeBlock.get(0),
                        "III",
                        "IHI",
                        "III",
                        'I', "ingot" + RCItems.toPascalCase(name),
                        'H', "hammer");
            }

            // Tool Recipes
            if (metal.isToolMetal()) {
                // Excavator
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/skill/metal/excavator/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/excavator/" + name),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/excavator_head/" + name));

                // Mining Hammer
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shaped/skill/metal/mining_hammer" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/mining_hammer/" + name),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H',
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/mining_hammer_head/" + name));

                // Wire Cutter
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/metal/wire_cutter/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/wire_cutter/" + name),
                        "HS",
                        "S ",
                        'S', "stickWood",
                        'H',
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/wire_cutter_head/" + name));

                // Universal Weapon
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shaped/skill/metal/universal_weapon/" + metal),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/universal_weapon/" + name),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/universal_weapon_head/" + name));

                // Universal Weapon Head
                MinecraftRecipeManager.addShapelessSkillRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "crafting/shapeless/skill/metal/universal_weapon_head/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/universal_weapon_head/" + name),
                        "gemAmethyst",
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID,
                                "metal/unfinished_universal_weapon_head/" + name));
            }

            if (metal.isUsable()) {
                // Piston
                MinecraftRecipeManager.addShapedRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/metal/piston/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/piston/" + name),
                        " S ",
                        "RLR",
                        " C ",
                        'S', "sheet" + RCItems.toPascalCase(name),
                        'R', "ring" + RCItems.toPascalCase(name),
                        'L', "stickLong" + RCItems.toPascalCase(name),
                        'C', "cylinder" + RCItems.toPascalCase(name));

                // Strip
                MinecraftRecipeManager.addShapelessDamageRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/strip/" + name),
                        1,
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_strip"),
                        "chisel",
                        "ingot" + RCItems.toPascalCase(name));

                // Long Rod
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/long_rod/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_long_rod"),
                        "hammer",
                        "stick" + RCItems.toPascalCase(name),
                        "stick" + RCItems.toPascalCase(name));

                // Rod
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/rod/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rod"),
                        "saw",
                        "ingot" + RCItems.toPascalCase(name));

                // Bolt
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/bolt/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_bolt"),
                        "chisel",
                        "stick" + RCItems.toPascalCase(name));

                // Screw
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/screw/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_screw"),
                        "chisel",
                        "bolt" + RCItems.toPascalCase(name),
                        "bolt" + RCItems.toPascalCase(name));

                // Wire
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/wire/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_wire", 4, 2),
                        "wireCutter",
                        "sheet" + RCItems.toPascalCase(name));

                // Nugget
                MinecraftRecipeManager.addShapelessRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/metal/nugget/" + name),
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/nugget/" + name, 0, 5),
                        "hammer",
                        "ingot" + RCItems.toPascalCase(name));
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
            String name = metal.getRegistryName().getPath();
            if (metal.isUsable()) {
                // Pipe Frame
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/pipe_frame/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/double_ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/pipe_frame/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.DRAW_NOT_LAST,
                        ForgeRule.BEND_NOT_LAST,
                        ForgeRule.HIT_LAST);

                // Rackwheel Piece
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/rackwheel_piece/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel_piece"),
                        metal.getTier(),
                        null,
                        ForgeRule.UPSET_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST,
                        ForgeRule.UPSET_LAST);

                // Rod
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/rod/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rod"),
                        metal.getTier(),
                        null,
                        ForgeRule.HIT_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST,
                        ForgeRule.HIT_LAST);

                // Strip
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/strip/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_strip"),
                        metal.getTier(),
                        null,
                        ForgeRule.HIT_ANY,
                        ForgeRule.HIT_ANY,
                        ForgeRule.SHRINK_ANY);

                // Ring
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/ring/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_TECH.ID, "metal/" + name + "_rod"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/ring/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY,
                        ForgeRule.SHRINK_NOT_LAST);

                // Small Spring
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/small_spring/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_TECH.ID, "metal/" + name + "_rod"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/small_spring/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_NOT_LAST,
                        ForgeRule.SHRINK_ANY,
                        ForgeRule.DRAW_LAST);

                // Cylinder
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Tags.MODID, "anvil/working/cylinder/" + name),
                        RecipeHelper.getIIngredient("sheet" + RCItems.toPascalCase(name)),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/cylinder/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY);

                // Rackwheel Half
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/rackwheel_half/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel_piece"),
                        RecipeHelper.getIIngredient(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel_piece"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/rackwheel_half/" + name),
                        metal.getTier(),
                        null);

                // Rackwheel
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/rackwheel/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "metal/rackwheel_half/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "metal/rackwheel_half/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel"),
                        metal.getTier(),
                        null);

                // Spring
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/spring/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "metal/small_spring/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "metal/small_spring/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/spring/" + name),
                        metal.getTier(),
                        null);

                // Dust
                TerrafirmacraftRecipeManager.addQuernRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "quern/" + name + "/dust"),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/dust/" + name));
            }

            if (metal.isToolMetal()) {
                // Wire Cutter Head
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/wire_cutter_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/wire_cutter_head/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.HIT_SECOND_LAST,
                        ForgeRule.DRAW_ANY);

                // Unfinished Universal Weapon Head
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "anvil/working/unfinished_universal_weapon_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/double_ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID,
                                "metal/unfinished_universal_weapon_head/" + name),
                        metal.getTier(),
                        null,
                        ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY,
                        ForgeRule.DRAW_NOT_LAST);
            }

            if (metal.isUsable() && metal.isToolMetal()) {
                // Unfinished Mining Hammer Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/unfinished_mining_hammer_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/double_ingot/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/hammer_head/" + name),
                        RecipeHelper
                                .getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/unfinished_mining_hammer_head/" + name),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Mining Hammer Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/mining_hammer_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/double_ingot/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID,
                                "metal/unfinished_mining_hammer_head/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/mining_hammer_head/" + name),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Unfinished Excavator Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/unfinished_excavator_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/shovel_head/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/unfinished_excavator_head/" + name),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);

                // Excavator Head
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/excavator_head/" + name),
                        RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                        RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "metal/unfinished_excavator_head/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/excavator_head/" + name),
                        metal.getTier(),
                        SmithingSkill.Type.TOOLS);
            }

            // Electron Tube Base
            TerrafirmacraftRecipeManager.addAnvilRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/electron_tube_base/" + name),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + name),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "metal/electron_tube_base/" + name),
                    metal.getTier(),
                    null,
                    ForgeRule.DRAW_ANY,
                    ForgeRule.DRAW_ANY,
                    ForgeRule.HIT_NOT_LAST);
        }
    }

    @Override
    public void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {
        for (Metal metal : TFCRegistries.METALS) {
            String name = metal.getRegistryName().getPath();
            if (metal.isUsable()) {
                // Rackwheel Piece
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel_piece"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_rackwheel_piece"),
                        1000);

                // Rackwheel
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name), 4,
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rackwheel"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_rackwheel"),
                        4000);

                // Long Rod
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_long_rod"),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_long_rod"),
                        1000);

                // Bolt
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_bolt", 0, 4),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_bolt"),
                        1000);

                // Screw
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_screw", 0, 4),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_screw"),
                        1000);

                // Nugget
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/nugget/" + name, 0, 10),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_nugget"),
                        1000);

                // Double Sheet
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "sheet" + RCItems.toPascalCase(name), 2,
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/double_sheet/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_double_sheet"),
                        2000);

                // Strip
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_strip", 0, 2),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_strip"),
                        1000);

                // Double Ingot
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name), 2,
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/double_ingot/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_double_ingot"),
                        2000);

                // Sheet
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingotDouble" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/sheet/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_sheet"),
                        2000);

                // Rod
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_rod", 0, 2),
                        RecipeHelper.getItemStack(Mods.IMMERSIVE_ENGINEERING.ID, "mold", 2),
                        1000);

                // Wire
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/" + name + "_wire", 0, 2),
                        RecipeHelper.getItemStack(Mods.IMMERSIVE_ENGINEERING.ID, "mold", 2),
                        1000);

                // Cylinder
                ImmersiveEngineeringRecipeManager.addMetalPressRecipe(
                        "sheet" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID,
                                "metal/cylinder/" + name),
                        RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/metal_press_cylinder"),
                        2000);

                // Dust
                ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                        "ingot" + RCItems.toPascalCase(name),
                        RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/dust/" + name),
                        1600);
            }
        }
    }
}
