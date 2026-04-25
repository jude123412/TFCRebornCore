package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.S;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

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
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "pipette"),
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

        // Peat Brick
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/mold/ingot/peat"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "peat"),
                "peat",
                "moldIngot");

        // Bituminous Peat
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/peat/bituminous"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "bituminous_peat"),
                " A ",
                "BPB",
                " A ",
                'A', "dustAsh",
                'B', "brickPeat",
                'P', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "propolis"));

        // Bituminous Peat Alt
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/peat/bituminous/alt"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "bituminous_peat"),
                " A ",
                "BPB",
                " A ",
                'A', "dustAsh",
                'B', "brickPeat",
                'P', "gemCoal");

        // Copper Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/copper"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 0),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseCopper");

        // Tin Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/tin"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 1),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseTin");

        // Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 2),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBronze");

        // Iron Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/iron"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 3),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseIron");

        // Gold Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/gold"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 4),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseGold");

        // Mithril Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/mithril"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 5),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseMithril");

        // Black Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/black_bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 6),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBlackBronze");

        // Rose Gold Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/rose_gold"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 7),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseRoseGold");

        // Bismuth Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/bismuth_bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 9),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBismuthBronze");

        // Cobalt Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/cobalt"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 10),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseCobalt");

        // HSLA Steel Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/hsla_steel"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 11),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseHslaSteel");

        // Enderpearl Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/ender"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 12),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseEnder");
    }

    @Override
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Honeyed Slice
        TerrafirmacraftRecipeManager.addItemFoodStats(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honeyed_slice"), 4,
                0, 2, 1, 1, 0, 0, 0, 0);
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Compost
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/composting/vegetable"),
                RecipeHelper.getIIngredient("tannin", 50),
                RecipeHelper.getIIngredient("categoryVegetable"),
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_bio"),
                72);
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/composting/fruit"),
                RecipeHelper.getIIngredient("tannin", 50),
                RecipeHelper.getIIngredient("categoryFruit"),
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fertilizer_bio"),
                72);
    }

    @Override
    public void registerForestryRecipes(FMLPostInitializationEvent r) {
        // Portable Beealyzer
        ForestryRecipeManager.addCarpenterRecipe(1200,
                null,
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
        ForestryRecipeManager.addCarpenterRecipe(600,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"),
                RecipeHelper.getFluidStack("creosote", 250),
                " S ",
                "SGS",
                "PSP",
                'S', "sheetAnyBronze",
                'G', "gearAnyBronze",
                'P', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/brass_piston"));

        // Hardened Casing
        ForestryRecipeManager.addCarpenterRecipe(30 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "hardened_machine"),
                RecipeHelper.getFluidStack("fresh_water", 5000),
                "GGG",
                "GCG",
                "GGG",
                'G', "gemChipped",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));
        ForestryRecipeManager.addCarpenterRecipe(25 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "hardened_machine"),
                RecipeHelper.getFluidStack("fresh_water", 5000),
                "G G",
                "GCG",
                "G G",
                'G', "gemFlawed",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));
        ForestryRecipeManager.addCarpenterRecipe(20 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "hardened_machine"),
                RecipeHelper.getFluidStack("fresh_water", 5000),
                "G G",
                " C ",
                "G G",
                'G', "gemNormal",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));
        ForestryRecipeManager.addCarpenterRecipe(15 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "hardened_machine"),
                RecipeHelper.getFluidStack("fresh_water", 5000),
                "GCG",
                'G', "gemFlawless",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "hardened_machine"),
                RecipeHelper.getFluidStack("fresh_water", 5000),
                "C",
                "G",
                'G', "gemExquisite",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));

        // Impregnated Casing
        ForestryRecipeManager.addCarpenterRecipe(20 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "impregnated_casing"),
                RecipeHelper.getFluidStack("seed.oil", 250),
                "LLL",
                "L L",
                "LLL",
                'L', "lumber");

        // Woven Silk
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "crafting_material", 3),
                RecipeHelper.getFluidStack("fresh_water", 500),
                "SSS",
                "SSS",
                "SSS",
                'S', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "crafting_material", 2));

        // Dissipation Charge
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "crafting_material", 4),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                "HRH",
                "RCR",
                "GRG",
                'H', "dropHoneydew",
                'R', "dropRoyalJelly",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "can"),
                'G', "dustGunpowder");

        // Scented Paneling
        ForestryRecipeManager.addCarpenterRecipe(4 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "crafting_material", 6),
                RecipeHelper.getFluidStack("for.honey", 500),
                " R ",
                "WWW",
                "BPB",
                'R', "dropRoyalJelly",
                'W', "lumber",
                'B', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "beeswax"),
                'P', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "pollen", 0));

        // Spectacles
        ForestryRecipeManager.addCarpenterRecipe(15 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "naturalist_helmet"),
                RecipeHelper.getFluidStack("fresh_water", 250),
                "RRR",
                "GRG",
                "RRR",
                'R', "stickIron",
                'G', "paneGlassColorless");

        // Basic Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "chipsets", 0),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                " S ",
                "RCR",
                " S ",
                'S', "sheetTin",
                'R', "dustRedstone",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit", 0));

        // Enhanced Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(20 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "chipsets", 1),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                " S ",
                "RCR",
                " S ",
                'S', "sheetAnyBronze",
                'R', "dustRedstone",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit", 0));

        // Refined Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(30 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "chipsets", 2),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                " S ",
                "RCR",
                " S ",
                'S', "sheetIron",
                'R', "dustRedstone",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit", 0));

        // Intricate Circuit Board
        ForestryRecipeManager.addCarpenterRecipe(40 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "chipsets", 3),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                " S ",
                "RCR",
                " S ",
                'S', "sheetLumium",
                'R', "dustRedstone",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit", 0));

        // Soldering Iron
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "soldering_iron"),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                "L  ",
                " L ",
                "  S",
                'L', "stickLongIron",
                'S', "stickWood");

        // Flexible Casing
        ForestryRecipeManager.addFabricatorRecipe(null,
                RecipeHelper.getFluidStack("glass", 500),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "flexible_casing"),
                "SGS",
                "B B",
                "SGS",
                'S', "sheetAnyBronze",
                'G', "gemNormal",
                'B', "slimeball");
    }
}
