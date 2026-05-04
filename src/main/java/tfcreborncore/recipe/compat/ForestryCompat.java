package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.S;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfcreborncore.recipe.manager.builders.TFCFoodBuilder;

public class ForestryCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.FORESTRY.ID,
                Mods.TFC_METALLUM.ID,
                Mods.TFC_DECORATION.ID,
                Mods.TFC_TECH.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.FORESTRY.ID);
    }

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {
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
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit"),
                'P', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/brass_piston"));

        // Peat Brick
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/form/ingot/peat"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "peat"),
                "peat",
                "formIngot");

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
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseCopper");

        // Tin Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/tin"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 1),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseTin");

        // Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 2),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBronze");

        // Iron Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/iron"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 3),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseIron");

        // Gold Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/gold"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 4),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseGold");

        // Mithril Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/mithril"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 5),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseMithril");

        // Black Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/black_bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 6),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBlackBronze");

        // Rose Gold Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/rose_gold"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 7),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseRoseGold");

        // Bismuth Bronze Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/bismuth_bronze"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 9),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseBismuthBronze");

        // Cobalt Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/cobalt"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 10),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseCobalt");

        // HSLA Steel Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/hsla_steel"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 11),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseHslaSteel");

        // Enderpearl Electron Tube
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/electron_tube/ender"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "thermionic_tubes", 12),
                "H",
                "G",
                "B",
                'H', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/electron_tube_housing"),
                'G', "slimeball",
                'B', "electronTubeBaseEnder");

        // Ash Bricks
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/bricks/ash"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "ash_brick", 0, 4),
                "BMB",
                "MBM",
                "BMB",
                'B', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/ash_brick"),
                'M', "mortar");

        // Ash Brick Stairs
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/stairs/ash"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "ash_stairs", 0, 8),
                "B  ",
                "BB ",
                "BBB",
                'B', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "ash_brick", 0));

        // Genetic Filter
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/genetic_filter/propolis"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "genetic_filter"),
                "WGW",
                "PBP",
                "AGA",
                'W', "plankWood",
                'G', "gemNormal",
                'P', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "propolis"),
                'B', "blockGlass",
                'A', "gearAnyBronze");

        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/genetic_filter/fruit"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "genetic_filter"),
                "WGW",
                "PBP",
                "AGA",
                'W', "plankWood",
                'G', "gemNormal",
                'P', "fruitForestry",
                'B', "blockGlass",
                'A', "gearAnyBronze");

        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/genetic_filter/caterpillar"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "genetic_filter"),
                "WGW",
                "PBP",
                "AGA",
                'W', "plankWood",
                'G', "gemNormal",
                'P', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "caterpillar_ge"),
                'B', "blockGlass",
                'A', "gearAnyBronze");

        // Habitat Locator
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/habitat_locator"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "habitat_locator"),
                " S ",
                "SRS",
                " S ",
                'S', "sheetAnyBronze",
                'R', "dustRedstone");

        // Untreated Frame
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/frame/untreated"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "frame_untreated"),
                "SSS",
                "SCS",
                "SSS",
                'S', "stickWood",
                'C', "clothLowQuality");

        // Impregnated Frame
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/frame/impregnated"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "frame_impregnated"),
                "SSS",
                "SCS",
                "SSS",
                'S', "stickWood",
                'C', "clothHighQuality");
    }

    @Override
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Honeyed Slice
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honeyed_slice"))
                .hunger(4)
                .saturation(2)
                .decay(1)
                .grain(1)
                .register();

        // Cherry
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 0))
                .hunger(2)
                .water(5.0F)
                .saturation(0.2F)
                .decay(4.0F)
                .fruit(1.0F)
                .register();
        // Walnut
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 1))
                .hunger(2)
                .saturation(0.1F)
                .decay(0.4F)
                .grain(0.1F)
                .register();

        // Chestnut
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 2))
                .hunger(2)
                .saturation(0.1F)
                .decay(0.4F)
                .grain(0.1F)
                .register();

        // Lemon
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 3))
                .hunger(2)
                .water(5.0F)
                .saturation(0.2F)
                .decay(2.0F)
                .fruit(0.8F)
                .register();

        // Plum
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 4))
                .hunger(2)
                .water(5.0F)
                .saturation(0.4F)
                .decay(2.86f)
                .fruit(0.8F)
                .register();

        // Date
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 5))
                .hunger(2)
                .water(5.0F)
                .saturation(0.4F)
                .decay(2.1F)
                .fruit(0.4F)
                .register();

        // Papaya
        TFCFoodBuilder.create()
                .item(RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 6))
                .hunger(2)
                .water(5.0F)
                .saturation(0.4F)
                .decay(1.6F)
                .fruit(1.2F)
                .register();
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

        // Silk Cloth
        TerrafirmacraftRecipeManager.addLoomRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "loom/silky_wisp/silk_cloth"),
                RecipeHelper.getIIngredient(Mods.FORESTRY.ID, "crafting_material", 2, 16),
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "animal/product/silk_cloth"),
                16,
                new ResourceLocation(Mods.MINECRAFT.ID, "textures/blocks/wool_colored_white.png"));
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
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"),
                RecipeHelper.getFluidStack("creosote", 250),
                " S ",
                "SGS",
                "PSP",
                'S', "sheetAnyBronze",
                'G', "gearAnyBronze",
                'P', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/brass_piston"));

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
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit", 0));

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
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit", 0));

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
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit", 0));

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
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rf_control_circuit", 0));

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

        // Carton
        ForestryRecipeManager.addCarpenterRecipe(2 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "carton"),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                " P ",
                "P P",
                " P ",
                'P', "paper");

        // Impregnated Stick
        ForestryRecipeManager.addCarpenterRecipe(8 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "oak_stick", 0, 2),
                RecipeHelper.getFluidStack("seed.oil", 100),
                "L",
                "L",
                'L', "lumber");

        // Iodine Capsule
        ForestryRecipeManager.addCarpenterRecipe(15 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "iodine_capsule"),
                RecipeHelper.getFluidStack("fresh_water", 1000),
                "HPH",
                "PCP",
                "GPG",
                'H', "dropHoney",
                'P', "itemPollen",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "can"),
                'G', "dustGunpowder");

        // Analyzer
        ForestryRecipeManager.addCarpenterRecipe(
                15 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "analyzer", 0),
                RecipeHelper.getFluidStack("latex", 1000),
                "BAB",
                "SCS",
                "BSB",
                'B', "screwAnyBronze",
                'A', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "portable_alyzer"),
                'S', "sheetAluminium",
                'C', RecipeHelper.getItemStack(Mods.FORESTRY.ID, "sturdy_machine"));

        // Escritoire
        ForestryRecipeManager.addCarpenterRecipe(
                10 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "escritoire", 0),
                RecipeHelper.getFluidStack("seed.oil", 500),
                "P  ",
                "LLL",
                "S S",
                'P', "plankWood",
                'L', "lumber",
                'S', "stickWood");

        // Proven Frame
        ForestryRecipeManager.addCarpenterRecipe(
                2 * S,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "frame_impregnated"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "frame_proven"),
                RecipeHelper.getFluidStack("creosote", 1000),
                "LLL",
                "GCG",
                "LLL",
                'L', "lumberTreatedWood",
                'G', "nuggetGold",
                'C', "clothHighQuality");

        // Apiarist Hat
        ForestryRecipeManager.addCarpenterRecipe(
                5 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "apiarist_helmet"),
                RecipeHelper.getFluidStack("fresh_water", 1250),
                "SSS",
                "S S",
                'S', "clothHighQuality");

        // Apiarist Shirt
        ForestryRecipeManager.addCarpenterRecipe(
                8 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "apiarist_chest"),
                RecipeHelper.getFluidStack("fresh_water", 2000),
                "S S",
                "SSS",
                "SSS",
                'S', "clothHighQuality");

        // Apiarist Pants
        ForestryRecipeManager.addCarpenterRecipe(
                8 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "apiarist_legs"),
                RecipeHelper.getFluidStack("fresh_water", 1750),
                "SSS",
                "S S",
                "S S",
                'S', "clothHighQuality");

        // Apiarist Shoes
        ForestryRecipeManager.addCarpenterRecipe(
                8 * S,
                null,
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "apiarist_boots"),
                RecipeHelper.getFluidStack("fresh_water", 2000),
                "S S",
                "S S",
                'S', "clothHighQuality");

        // Mouldy Wheat
        ForestryRecipeManager.addMoistenerFuelRecipe(
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "food/wheat"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "mouldy_wheat"),
                1,
                600);

        // Decaying Wheat
        ForestryRecipeManager.addMoistenerFuelRecipe(
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "mouldy_wheat"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "decaying_wheat"),
                2,
                800);

        // Mulch Wheat
        ForestryRecipeManager.addMoistenerFuelRecipe(
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "decaying_wheat"),
                RecipeHelper.getItemStack(Mods.FORESTRY.ID, "mulch"),
                3,
                1000);

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
