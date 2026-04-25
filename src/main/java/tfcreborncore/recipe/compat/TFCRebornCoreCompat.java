package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.S;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.knapping.KnappingType;
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
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TFCTechRecipeManager;
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

        // Terrafirmacraft
        // Quern
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/hematite"));

        // Limonite Powder
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/limonite"));

        // Malachite Powder
        TerrafirmacraftRecipeManager
                .removeQuernRecipe(RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/malachite"));

        // Ex Nihilo
        // Sieve
        ExNihiloRecipeManager.removeAllSieveRecipes();

        // Immersive Engineering
        // Crusher
        ImmersiveEngineeringRecipeManager.removeAllCrusherRecipes();

        // Forestry
        // Carpenter
        ForestryRecipeManager.removeAllCarpenterRecipes();
        // Thermionic Fabricator
        ForestryRecipeManager.removeAllFabricatorRecipes();
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Wood Sheet
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/wood_sheet"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/wood_sheet"),
                " W ",
                "WSW",
                " W ",
                'W', "dustWood",
                'S', "slimeball");

        // Ingot Mold
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/mold/ingot"),
                1,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/ingot_mold"),
                "SK",
                'S', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/latex_coated_wood_sheet"),
                'K', "knife");

        // Pellet Mold
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/mold/pellet"),
                1,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/pellet_mold"),
                "KS",
                'S', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/latex_coated_wood_sheet"),
                'K', "knife");

        // Redstone Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/redstone"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_electron_tube"),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseRedstone");

        // RF Control Circuit
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/rf_control_circuit"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"),
                "TBT",
                "RWR",
                "AAA",
                'T', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_electron_tube"),
                'B', "sheetAnyBronze",
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor"),
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/basic_circuit_board"),
                'A', "stripRedAlloy");

        // Hardened Glass Mix
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/hardened_glass_mix"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/hardened_glass_mix"),
                "dustFlux",
                "dustObsidian",
                "dustHematite");

        // Hardened Glass Mix Alt
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/hardened_glass_mix_alt"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/hardened_glass_mix"),
                "dustFlux",
                "dustObsidian",
                "dustLimonite");

        // Synthetic Graphite Mix
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/synthetic_graphite_mix"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/synthetic_graphite_mix"),
                "dustFlux",
                "dustCoal",
                "dustWood");

        // Brass Piston
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/brass_piston"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/brass_piston"),
                " B ",
                "ISI",
                "IMI",
                'B', "sheetBrass",
                'I', "ingotBrass",
                'S', "stickLongBrass",
                'M', RecipeHelper.getItemStack("tfc", "brass_mechanisms"));

        // Nickel-Plated Capacitor Cell
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/nickel_plated_capacitor_cell"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/nickel_plated_capacitor_cell"),
                "N",
                "C",
                'N', "sheetNickel",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/clay_sheet"));

        // Ceramic Capacitor Block
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/ceramic_capacitor_block"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/ceramic_capacitor_block"),
                "SCS",
                "NCN",
                "SCS",
                'S', "screwNickelSilver",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/nickel_plated_capacitor_cell"),
                'N', "sheetNickel");

        // Basic Circuit Board
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/basic_circuit_board"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/basic_circuit_board"),
                "SSS",
                "SWS",
                "SSS",
                'S', "stripCopper",
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/wood_sheet"));

        // Redstone Resistor
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_resistor"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor"),
                "SD ",
                "LCR",
                " DS",
                'S', "slimeball",
                'D', "dustCoal",
                'L', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_a"),
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_b"),
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_c"));

        // Ore Processing
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/small/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 1),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()));

            // Poor Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/poor/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 2),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1));

            // Normal Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/normal/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 3),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0));

            // Rich Ore Smashing
            MinecraftRecipeManager.addShapelessDamageRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/damage/smashing/ore/rich/" + type.getPrimaryName()),
                    2,
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 5),
                    "hammer",
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2));

            // Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/mold/pellet/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    "moldPellet",
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()));

            // Double Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/mold/pellet/double/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    "moldPellet",
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()));

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()));

            // Triple Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()));

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/triple/mix/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()));

            // Bar
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    "moldIngot");

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName(), 0, 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    "moldIngot");

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName(), 0, 3),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    "moldIngot");
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
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Hardened Glass Mix
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/hardened_glass_mix"), 0.2F, 1400, false);
        // Unfired Clay Sheet
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/unfired_clay_sheet"), 0.2F, 2000, false);
        // Unfired Ceramic Insulator
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/unfired_ceramic_insulator"), 0.2F, 2000,
                false);
        // Clay Sheet
        TerrafirmacraftRecipeManager.addItemHeat(RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/clay_sheet"),
                0.2F, 2000, false);

        // Lignite Coke
        TerrafirmacraftRecipeManager.addItemFuel(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/lignite_coke"), 4000, 1415.0F, true, false);
        // Bituminous Coal Coke
        TerrafirmacraftRecipeManager.addItemFuel(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/bituminous_coal_coke"), 4400, 1500.0F, true,
                false);

        // Synthetic Graphite
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/synthetic_graphite_mix"), 0.6F, 2000, false);

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
        // Knapping Recipes
        // Unfired Ceramic Sheet
        TerrafirmacraftRecipeManager.addKnappingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "knapping/clay/unfired_ceramic_sheet"),
                KnappingType.CLAY,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/unfired_clay_sheet", 0, 2),
                "     ",
                "XXXXX",
                "     ",
                "XXXXX",
                "     ");

        // Unfired Ceramic Insulator
        TerrafirmacraftRecipeManager.addKnappingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "knapping/clay/unfired_ceramic_insulator"),
                KnappingType.CLAY,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/unfired_ceramic_insulator", 0, 2),
                "XX XX",
                " XXX ",
                "     ",
                "XX XX",
                " XXX ");

        // Heating Recipes
        // Graphite Powder
        TerrafirmacraftRecipeManager.addHeatTransformRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "heat/transform/graphite"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/synthetic_graphite_mix"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/graphite"),
                1599);

        // Ceramic Sheet
        TerrafirmacraftRecipeManager.addHeatTransformRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "heat/transform/ceramic_sheet"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/unfired_clay_sheet"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/clay_sheet"),
                1599);

        // Ceramic Insulator
        TerrafirmacraftRecipeManager.addHeatTransformRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "heat/transform/ceramic_insulator"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/unfired_ceramic_insulator"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/ceramic_insulator"),
                1599);

        // Barrel Recipes
        // Treated Wood Lumber
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/treated_wood_lumber"),
                RecipeHelper.getIIngredient("creosote", 25),
                RecipeHelper.getIIngredient("lumber"),
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "wood/lumber/treated_wood"),
                8);

        // Latex Coated Wood Sheet
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/latex_coated_wood_sheet"),
                RecipeHelper.getIIngredient("latex", 50),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/wood_sheet"),
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/latex_coated_wood_sheet"),
                8);

        // Anvil Working Recipes
        // Redstone Resistor Part A
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(
                        Mods.TFC_REBORN_CORE.ID,
                        "anvil/working/redstone_resistor/part_a"),
                RecipeHelper.getIIngredient("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_a", 0, 10),
                Metal.Tier.TIER_II,
                null,
                ForgeRule.HIT_THIRD_LAST,
                ForgeRule.BEND_SECOND_LAST,
                ForgeRule.HIT_LAST);

        // Redstone Resistor Part B
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(
                        Mods.TFC_REBORN_CORE.ID,
                        "anvil/working/redstone_resistor/part_b"),
                RecipeHelper.getIIngredient("ingotGold"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_b", 0, 10),
                Metal.Tier.TIER_II,
                null,
                ForgeRule.HIT_THIRD_LAST,
                ForgeRule.BEND_SECOND_LAST,
                ForgeRule.HIT_LAST);

        // Redstone Resistor Part C
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(
                        Mods.TFC_REBORN_CORE.ID,
                        "anvil/working/redstone_resistor/part_c"),
                RecipeHelper.getIIngredient("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_c", 0, 10),
                Metal.Tier.TIER_II,
                null,
                ForgeRule.HIT_THIRD_LAST,
                ForgeRule.BEND_SECOND_LAST,
                ForgeRule.HIT_LAST);

        // Radiator Piping
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(
                        Mods.TFC_REBORN_CORE.ID,
                        "anvil/working/radiator_piping"),
                RecipeHelper.getIIngredient("ingotIron"),
                RecipeHelper.getItemStack(
                        Mods.TFC_REBORN_CORE.ID,
                        "item/radiator_piping",
                        0,
                        1),
                Metal.Tier.TIER_II,
                null,
                ForgeRule.BEND_THIRD_LAST,
                ForgeRule.UPSET_SECOND_LAST,
                ForgeRule.DRAW_LAST);

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

        // Welding Recipes

        // Radiator Matrix
        TerrafirmacraftRecipeManager.addWeldingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/radiator_matrix"),
                RecipeHelper.getIIngredient("ingotDoubleIron"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/radiator_piping", 0),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/radiator_matrix", 0),
                Metal.Tier.TIER_II,
                null);

        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
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

            if (metal.isUsable()) {
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
            }
        }

        // Quern Recipes
        // Wood Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "wood_powder"),
                RecipeHelper.getIIngredient("lumber"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/wood_powder"));

        // Coal Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder"),
                RecipeHelper.getIIngredient("gemLignite"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/coal_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder_alt"),
                RecipeHelper.getIIngredient("gemCoal"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/coal_powder", 0, 2));

        // Snow Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "snow_powder"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "snow")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/snow_powder", 0, 2));

        // Obsidian Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "obsidian_powder"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/obsidian_powder", 0, 2));

        // Enderpearl Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "enderpearl_powder"),
                RecipeHelper.getIIngredient("gemEnder"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/enderpearl_powder"));

        // Certus Quartz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder"),
                RecipeHelper.getIIngredient("crystalCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_1"),
                RecipeHelper.getIIngredient("crystalPureCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_2"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack("appliedenergistics2", "material", 1)),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/certus_quartz_powder"));

        // Hematite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "hematite_powder"),
                RecipeHelper.getIIngredient("crushedHematite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/hematite"));

        // Limonite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "limonite_powder"),
                RecipeHelper.getIIngredient("crushedLimonite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/limonite"));

        // Malachite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "malachite_powder"),
                RecipeHelper.getIIngredient("crushedMalachite"),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/malachite"));

        // Apatite Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "apatite_powder"),
                RecipeHelper.getIIngredient("gemApatite"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/apatite_powder"));

        // Bio pulp
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "bio_pulp"),
                RecipeHelper.getIIngredient("treeLeaves"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "bio_pulp_alt"),
                RecipeHelper.getIIngredient("treeSapling"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 816));

        // Ore Processing
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_2"),
                    RecipeHelper.getIIngredient(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName())),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 2));

            // Poor Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_3"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 3));

            // Normal Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_5"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 5));

            // Rich Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_7"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 7));
        }

        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                // Ingot to Dust
                TerrafirmacraftRecipeManager.addQuernRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "quern/" + metal.getRegistryName().getPath().toLowerCase() + "/dust"),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemMetal.get(metal, Metal.ItemType.DUST)));
            }
        }
    }

    @Override
    public void registerExNihiloRecipes(FMLPostInitializationEvent r) {}

    @Override
    public void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 2),
                    new Object[] {
                            RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.10F
                    },
                    2000);

            // Poor Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 3),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.15F
                    },
                    3000);

            // Normal Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 5),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.25F
                    },
                    5000);

            // Rich Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 7),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
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
        // RF Control Circuit 2x
        ForestryRecipeManager.addCarpenterRecipe(
                5 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit", 0, 2),
                RecipeHelper.getFluidStack("250", 1000),
                "TBT",
                "RWR",
                "AAA",
                'T', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_electron_tube"),
                'B', "sheetAnyBronze",
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor"),
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/good_circuit_board"),
                'A', "stripRedAlloy");

        // LV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                5 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/basic_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                " S ",
                "ACA",
                " S ",
                'S', "screwCopper",
                'A', "sheetCopper",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/ceramic_capacitor_block"));

        // MV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                10 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/advanced_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                "CWC",
                "SGS",
                "CWC",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/basic_capacitor_cell"),
                'W', "wireGold",
                'S', "screwGold",
                'G', "sheetGold");

        // HV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/hi_tech_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                "CWC",
                "SGS",
                "CWC",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/advanced_capacitor_cell"),
                'W', "wireSteel",
                'S', "screwSteel",
                'G', "sheetSteel");

        // Good Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/good_circuit_board", 0, 4),
                RecipeHelper.getFluidStack("creosote", 250),
                "SSS",
                "SWS",
                "SSS",
                'S', "stripGold",
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/latex_coated_wood_sheet"));

        // Redstone Resistor
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor", 0, 2),
                RecipeHelper.getFluidStack("latex", 50),
                " D ",
                "LCR",
                " D ",
                'D', "dustCoal",
                'L', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_a"),
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_b"),
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/redstone_resistor_part_c"));

        // Thermionic Fabricator Recipes
        // These need to be registered first
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

    @Override
    public void registerTFCTechRecipes(FMLPostInitializationEvent r) {
        TFCTechRecipeManager.addGlassWorkingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "glass/working/electron_tube_housing"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                " XXX ",
                "X   X",
                "X   X",
                "XX XX",
                " X X ");

        TFCTechRecipeManager.addGlassWorkingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "glass/working/glass_insulator"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/glass_insulator"),
                "XX XX",
                " XXX ",
                "XX XX",
                " XXX ",
                "XX XX");

        TFCTechRecipeManager.addSmelteryRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "smeltery/recycle/electron_tube_housing"),
                RecipeHelper.getFluidStack("glass", 250),
                800,
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"));

        TFCTechRecipeManager.addSmelteryRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "smeltery/recycle/glass_insulator"),
                RecipeHelper.getFluidStack("glass", 250),
                800,
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/glass_insulator"));
    }
}
