package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class ThermalExpansionCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_METALLUM.ID,
                Mods.TFC_TECH.ID,
                Mods.THERMAL_FOUNDATION.ID,
                Mods.THERMAL_DYNAMICS.ID,
                Mods.THERMAL_INNOVATION.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        // Recipe Removal
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.THERMAL_DYNAMICS.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.THERMAL_FOUNDATION.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.THERMAL_INNOVATION.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Crescent Hammer
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/crescent_hammer"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "wrench"),
                "S S",
                "DGD",
                "DSD",
                'S', "stickLongIron",
                'D', "dyeBlue",
                'G', "gearTin");

        // Multimeter
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/multimeter"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "meter"),
                "C C",
                "LRL",
                " G ",
                'C', "sheetCopper",
                'L', "sheetLead",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 515),
                'G', "gearGold");

        // Signalum Security Lock
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/signalum_security_lock"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "security"),
                " S ",
                "SRS",
                "PPP",
                'S', "stickSignalum",
                'R', "dustRedstone",
                'P', "sheetSignalum");

        // Redstone Servo
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_servo"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 512),
                "ISI",
                " S ",
                "ISI",
                'I', "ingotRedstone",
                'S', "stickLongIron");

        // Redstone Reception Coil
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_reception_coil"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 513),
                " RS",
                "RGR",
                "SR ",
                'R', "dustRedstone",
                'G', "ingotGold",
                'S', "stickLongGold");

        // Redstone Transmission Coil
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_transmission_coil"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 514),
                " RS",
                "RGR",
                "SR ",
                'R', "dustRedstone",
                'G', "ingotSilver",
                'S', "stickLongSilver");

        // Redstone Conductance Coil
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_conductance_coil"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 515),
                "SR ",
                "RGR",
                " RS",
                'S', "stickLongElectrum",
                'R', "dustRedstone",
                'G', "ingotElectrum");

        // Tool Casing
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/tool_casing"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 640),
                "RS ",
                "SCS",
                " ST",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 513),
                'S', "sheetGold",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"),
                'T', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 514));

        // Drill Head
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/drill_head"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 656),
                "IP ",
                "PIP",
                " PI",
                'I', "sheetIron",
                'P', "rackwheelPieceIron");

        // Saw Blade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/saw_blade"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 657),
                " P ",
                "PIP",
                " P ",
                'P', "rackwheelPieceIron",
                'I', "sheetIron");

        // Pulped Bioblend
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/pulped_bioblend"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 818, 4),
                "itemBiomass",
                "itemBiomass",
                "dustWood",
                "dustWood");

        // Pyrotheum Dust
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/pyrotheum_dust"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 1024, 2),
                "dustBlaze",
                "dustBlaze",
                "dustRedstone",
                "dustSulfur");

        // Cryotheum Dust
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/cryotheum_dust"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 1025, 2),
                "dustBlizz",
                "dustBlizz",
                "dustRedstone",
                "dustSnow");

        // Aerotheum Dust
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/aerotheum_dust"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 1026, 2),
                "dustBlitz",
                "dustBlitz",
                "dustRedstone",
                "dustSaltpeter");

        // Petrotheum Dust
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shapeless/petrotheum_dust"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 1027, 2),
                "dustBasalz",
                "dustBasalz",
                "dustRedstone",
                "dustObsidian");

        // Fluiduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/fluiduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_16", 0, 8),
                "CGC",
                'C', "sheetCopper",
                'G', "blockGlassHardened");

        // Hardened Fluiduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/hardened_fluiduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_16", 2, 8),
                "SGS",
                'S', "sheetSterlingSilver",
                'G', "blockGlassHardened");

        // Super-Laminar Fluiduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/super_laminar_fluiduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_16", 6),
                "GGG",
                "BDB",
                "GGG",
                'G', "blockGlassHardened",
                'B', "sheetAnyBronze",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_16", 2));

        // Itemduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/itemduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_32", 0, 8),
                "TGT",
                'T', "sheetTin",
                'G', "blockGlassHardened");

        // Structuralduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/structuralduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_48", 0, 16),
                "NSN",
                'N', "nuggetIron",
                'S', "sheetLead");

        // Long Range Viaduct
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/long_range_viaduct"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_64", 1, 32),
                "LGL",
                "G G",
                "LGL",
                'L', "sheetLead",
                'G', "blockGlassHardened");

        // Viaduct (Untreated)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/viaduct_untreated"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_64", 3, 16),
                "BGB",
                "G G",
                "BGB",
                'B', "sheetAnyBronze",
                'G', "blockGlassHardened");

        // Servo Basic
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/servo/basic"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 0),
                "PGP",
                "TRT",
                'P', "stripTin",
                'G', "blockGlassHardened",
                'T', "sheetTin",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 512));

        // Servo Hardened
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/servo/hardened"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 1),
                "PGP",
                "TRT",
                'P', "stripInvar",
                'G', "blockGlassHardened",
                'T', "sheetInvar",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 0));

        // Servo Reinforced
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/servo/reinforced"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 2),
                "PGP",
                "TRT",
                'P', "stripElectrum",
                'G', "blockGlassHardened",
                'T', "sheetElectrum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 1));

        // Servo Signalum
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/servo/signalum"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 3),
                "PGP",
                "TRT",
                'P', "stripSignalum",
                'G', "blockGlassHardened",
                'T', "sheetSignalum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 2));

        // Servo Enderium
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/servo/enderium"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 4),
                "PGP",
                "TRT",
                'P', "stripEnderium",
                'G', "blockGlassHardened",
                'T', "sheetEnderium",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", 3));

        // Filter Basic
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/filter/basic"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 0),
                "PGP",
                "TRT",
                'P', "stripTin",
                'G', "blockGlassHardened",
                'T', "sheetTin",
                'R', "paper");

        // Filter Hardened
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/filter/hardened"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 1),
                "PGP",
                "TRT",
                'P', "stripInvar",
                'G', "blockGlassHardened",
                'T', "sheetInvar",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 0));

        // Filter Reinforced
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/filter/reinforced"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 2),
                "PGP",
                "TRT",
                'P', "stripElectrum",
                'G', "blockGlassHardened",
                'T', "sheetElectrum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 1));

        // Filter Signalum
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/filter/signalum"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 3),
                "PGP",
                "TRT",
                'P', "stripSignalum",
                'G', "blockGlassHardened",
                'T', "sheetSignalum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 2));

        // Filter Enderium
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/filter/enderium"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 4),
                "PGP",
                "TRT",
                'P', "stripEnderium",
                'G', "blockGlassHardened",
                'T', "sheetEnderium",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", 3));

        // Retriever Basic
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/retriever/basic"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 0),
                "PGP",
                "TRT",
                'P', "stripTin",
                'G', "blockGlassHardened",
                'T', "sheetTin",
                'R', "gemEnderEye");

        // Retriever Hardened
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/retriever/hardened"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 1),
                "PGP",
                "TRT",
                'P', "stripInvar",
                'G', "blockGlassHardened",
                'T', "sheetInvar",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 0));

        // Retriever Reinforced
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/retriever/reinforced"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 2),
                "PGP",
                "TRT",
                'P', "stripElectrum",
                'G', "blockGlassHardened",
                'T', "sheetElectrum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 1));

        // Retriever Signalum
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/retriever/signalum"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 3),
                "PGP",
                "TRT",
                'P', "stripSignalum",
                'G', "blockGlassHardened",
                'T', "sheetSignalum",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 2));

        // Retriever Enderium
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/retriever/enderium"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 4),
                "PGP",
                "TRT",
                'P', "stripEnderium",
                'G', "blockGlassHardened",
                'T', "sheetEnderium",
                'R', RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", 3));

        // Redstone Relay
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_relay"),
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "relay", 0, 2),
                "SQS",
                "LRL",
                'S', "stripSignalum",
                'Q', "gemQuartz",
                'L', "sheetLead",
                'R', "dustRedstone");

        // Fluxbore Basic
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/fluxbore/basic"),
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 0),
                "DIG",
                "SCI",
                "BSR",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 656),
                'I', "ingotRedstone",
                'G', "gearIron",
                'S', "sheetSilver",
                'C', RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 640),
                'B', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stone_button"),
                'R', "stickLongIron");

        // Fluxbore Hardened
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/fluxbore/hardened"),
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 1),
                " I ",
                "SDS",
                "IGI",
                'I', "ingotAnyBronze",
                'S', "sheetInvar",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 0),
                'G', "gearInvar");

        // Fluxbore Reinforced
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/fluxbore/reinforced"),
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 2),
                " I ",
                "SDS",
                "IGI",
                'I', "ingotIron",
                'S', "sheetElectrum",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 1),
                'G', "gearElectrum");

        // Fluxbore Signalum
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/fluxbore/signalum"),
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 3),
                " I ",
                "SDS",
                "IGI",
                'I', "ingotSteel",
                'S', "sheetSignalum",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 2),
                'G', "gearSignalum");

        // Fluxbore Enderium
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                        "crafting/shaped/fluxbore/enderium"),
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 4),
                " I ",
                "SDS",
                "IGI",
                'I', "ingotBlackSteel",
                'S', "sheetEnderium",
                'D', RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", 3),
                'G', "gearEnderium");
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        // Blizz Rod
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/blizz_rod"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("stickCobalt"),
                null,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2048),
                8);

        // Blitz Rod
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/blitz_rod"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("stickGold"),
                null,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2050),
                8);

        // Basalz Rod
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/basalz_rod"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("stickMagnesiumDiboride"),
                null,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2052),
                8);
    }

    @Override
    public void registerQuernRecipes(IForgeRegistry<QuernRecipe> r) {
        // Blizz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "quern/powder/blizz_powder"),
                IIngredient.of("rodBlizz"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2049, 2));

        // Blitz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "quern/powder/blitz_powder"),
                IIngredient.of("rodBlitz"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2051, 2));

        // Basalz Powder
        TerrafirmacraftRecipeManager.addQuernRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "quern/powder/basalz_powder"),
                IIngredient.of("rodBasalz"),
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2053, 2));
    }

    @Override
    public void registerCrusherRecipes(FMLPostInitializationEvent r) {
        // Blizz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "rodBlizz",
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2049, 3),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2049), 0.50F
                },
                1600);

        // Blitz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "rodBlizz",
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2051, 3),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2051), 0.50F
                },
                1600);

        // Basalz Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "rodBlizz",
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2053, 3),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2053), 0.50F
                },
                1600);
    }
}
