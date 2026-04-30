package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.S;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TFCTechRecipeManager;
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
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.IMMERSIVE_ENGINEERING.ID,
                Mods.TFC_METALLUM.ID,
                Mods.FORESTRY.ID,
                Mods.APPLIED_ENERGISTICS_2.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
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
        // Moistener
        ForestryRecipeManager.removeAllMoistenerRecipes();
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Wood Sheet
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/wood_sheet"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/wood_sheet"),
                " W ",
                "WSW",
                " W ",
                'W', "dustWood",
                'S', "slimeball");

        // Ingot Form
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/form/ingot"),
                1,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ingot_form"),
                "SK",
                'S', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/latex_coated_wood_sheet"),
                'K', "knife");

        // Pellet Form
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/form/pellet"),
                1,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/pellet_form"),
                "KS",
                'S', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/latex_coated_wood_sheet"),
                'K', "knife");

        // Redstone Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/redstone"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_electron_tube"),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseRedstone");

        // RF Control Circuit
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/rf_control_circuit"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit"),
                "TBT",
                "RWR",
                "AAA",
                'T', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_electron_tube"),
                'B', "sheetAnyBronze",
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor"),
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/basic_circuit_board"),
                'A', "stripRedAlloy");

        // Hardened Glass Mix
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/hardened_glass_mix"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/hardened_glass_mix"),
                "dustFlux",
                "dustObsidian",
                "dustHematite");

        // Hardened Glass Mix Alt
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/hardened_glass_mix_alt"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/hardened_glass_mix"),
                "dustFlux",
                "dustObsidian",
                "dustLimonite");

        // Synthetic Graphite Mix
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/synthetic_graphite_mix"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/synthetic_graphite_mix"),
                "dustFlux",
                "dustCoal",
                "dustWood");

        // Brass Piston
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/brass_piston"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/brass_piston"),
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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/nickel_plated_capacitor_cell"),
                "N",
                "C",
                'N', "sheetNickel",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/clay_sheet"));

        // Ceramic Capacitor Block
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/ceramic_capacitor_block"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ceramic_capacitor_block"),
                "SCS",
                "NCN",
                "SCS",
                'S', "screwNickelSilver",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/nickel_plated_capacitor_cell"),
                'N', "sheetNickel");

        // Basic Circuit Board
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/basic_circuit_board"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/basic_circuit_board"),
                "SSS",
                "SWS",
                "SSS",
                'S', "stripCopper",
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/wood_sheet"));

        // Redstone Resistor
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_resistor"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor"),
                "SD ",
                "LCR",
                " DS",
                'S', "slimeball",
                'D', "dustCoal",
                'L', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_a"),
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_b"),
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_c"));

        // Ash Brick
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/form/ingot/ash"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ash_brick"),
                "dustAsh",
                "formIngot");
    }

    @Override
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Hardened Glass Mix
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/hardened_glass_mix"), 0.2F, 1400, false);
        // Unfired Clay Sheet
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/unfired_clay_sheet"), 0.2F, 2000, false);
        // Unfired Ceramic Insulator
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/unfired_ceramic_insulator"), 0.2F, 2000,
                false);
        // Clay Sheet
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/clay_sheet"),
                0.2F, 2000, false);

        // Lignite Coke
        TerrafirmacraftRecipeManager.addItemFuel(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/lignite_coke"), 4000, 1415.0F, true, false);
        // Bituminous Coal Coke
        TerrafirmacraftRecipeManager.addItemFuel(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/bituminous_coal_coke"), 4400, 1500.0F, true,
                false);

        // Synthetic Graphite
        TerrafirmacraftRecipeManager.addItemHeat(
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/synthetic_graphite_mix"), 0.6F, 2000,
                false);
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Unfired Ceramic Sheet
        TerrafirmacraftRecipeManager.addKnappingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "knapping/clay/unfired_ceramic_sheet"),
                KnappingType.CLAY,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/unfired_clay_sheet", 0, 2),
                "     ",
                "XXXXX",
                "     ",
                "XXXXX",
                "     ");

        // Unfired Ceramic Insulator
        TerrafirmacraftRecipeManager.addKnappingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "knapping/clay/unfired_ceramic_insulator"),
                KnappingType.CLAY,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/unfired_ceramic_insulator", 0, 2),
                "XX XX",
                " XXX ",
                "     ",
                "XX XX",
                " XXX ");

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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/clay_sheet"),
                1599);

        // Ceramic Insulator
        TerrafirmacraftRecipeManager.addHeatTransformRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "heat/transform/ceramic_insulator"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/unfired_ceramic_insulator"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ceramic_insulator"),
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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/latex_coated_wood_sheet"),
                8);

        // Redstone Resistor Part A
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(
                        Mods.TFC_REBORN_CORE.ID,
                        "anvil/working/redstone_resistor/part_a"),
                RecipeHelper.getIIngredient("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_a", 0, 10),
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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_b", 0, 10),
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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_c", 0, 10),
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

        // Radiator Matrix
        TerrafirmacraftRecipeManager.addWeldingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/welding/radiator_matrix"),
                RecipeHelper.getIIngredient("ingotDoubleIron"),
                RecipeHelper.getIIngredient(Mods.TFC_REBORN_CORE.ID, "item/radiator_piping", 0),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/radiator_matrix", 0),
                Metal.Tier.TIER_II,
                null);

        // Wood Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "wood_powder"),
                RecipeHelper.getIIngredient("lumber"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/wood_powder"));

        // Coal Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder"),
                RecipeHelper.getIIngredient("gemLignite"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/coal_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "coal_powder_alt"),
                RecipeHelper.getIIngredient("gemCoal"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/coal_powder", 0, 2));

        // Snow Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "snow_powder"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "snow")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/snow_powder", 0, 2));

        // Obsidian Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "obsidian_powder"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian")),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/obsidian_powder", 0, 2));

        // Enderpearl Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "enderpearl_powder"),
                RecipeHelper.getIIngredient("gemEnder"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/enderpearl_powder"));

        // Certus Quartz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder"),
                RecipeHelper.getIIngredient("crystalCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_1"),
                RecipeHelper.getIIngredient("crystalPureCertusQuartz"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"));
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "certus_quartz_powder_alt_2"),
                RecipeHelper.getIIngredient(RecipeHelper.getItemStack("appliedenergistics2", "material", 1)),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"));

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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/apatite_powder"));
    }

    @Override
    public void registerExNihiloRecipes(FMLPostInitializationEvent r) {}

    @Override
    public void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {
        // Wood Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "lumber",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/wood_powder"),
                null,
                1600);

        // Coal Powder (Lignite)
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "gemLignite",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/coal_powder"),
                null,
                1600);

        // Coal Powder (Coal)
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "gemCoal",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/coal_powder", 0, 2),
                null,
                1600);

        // Snow Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "snow"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/snow_powder", 0, 2),
                null,
                1600);

        // Obsidian Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/obsidian_powder", 0, 2),
                null,
                3200);

        // Enderpearl Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "gemEnder",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/enderpearl_powder"),
                null,
                1600);

        // Certus Quartz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "crystalCertusQuartz",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"),
                null,
                1600);

        // Pure Certus Quartz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "crystalPureCertusQuartz",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"),
                null,
                1600);

        // Charged Certus Quartz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                RecipeHelper.getItemStack("appliedenergistics2", "material", 1),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/certus_quartz_powder"),
                null,
                1600);

        // Hematite Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "crushedHematite",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/hematite"),
                null,
                1600);

        // Limonite Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "crushedLimonite",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/limonite"),
                null,
                1600);

        // Malachite Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "crushedMalachite",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "powder/malachite"),
                null,
                1600);

        // Apatite Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "gemApatite",
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/apatite_powder"),
                null,
                1600);
    }

    @Override
    public void registerForestryRecipes(FMLPostInitializationEvent r) {
        // RF Control Circuit 2x
        ForestryRecipeManager.addCarpenterRecipe(
                5 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit", 0, 2),
                RecipeHelper.getFluidStack("250", 1000),
                "TBT",
                "RWR",
                "AAA",
                'T', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_electron_tube"),
                'B', "sheetAnyBronze",
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor"),
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/good_circuit_board"),
                'A', "stripRedAlloy");

        // LV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                5 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/basic_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                " S ",
                "ACA",
                " S ",
                'S', "screwCopper",
                'A', "sheetCopper",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ceramic_capacitor_block"));

        // MV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                10 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/advanced_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                "CWC",
                "SGS",
                "CWC",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/basic_capacitor_cell"),
                'W', "wireGold",
                'S', "screwGold",
                'G', "sheetGold");

        // HV Capacitor Cell
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/hi_tech_capacitor_cell"),
                RecipeHelper.getFluidStack("creosote", 1000),
                "CWC",
                "SGS",
                "CWC",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/advanced_capacitor_cell"),
                'W', "wireSteel",
                'S', "screwSteel",
                'G', "sheetSteel");

        // Good Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/good_circuit_board", 0, 4),
                RecipeHelper.getFluidStack("creosote", 250),
                "SSS",
                "SWS",
                "SSS",
                'S', "stripGold",
                'W', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/latex_coated_wood_sheet"));

        // Redstone Resistor
        ForestryRecipeManager.addCarpenterRecipe(
                4 * S,
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor", 0, 2),
                RecipeHelper.getFluidStack("latex", 50),
                " D ",
                "LCR",
                " D ",
                'D', "dustCoal",
                'L', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_a"),
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_b"),
                'R', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/redstone_resistor_part_c"));

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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                " XXX ",
                "X   X",
                "X   X",
                "XX XX",
                " X X ");

        TFCTechRecipeManager.addGlassWorkingRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "glass/working/glass_insulator"),
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/glass_insulator"),
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
