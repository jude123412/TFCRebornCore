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

import crazypants.enderio.base.recipe.RecipeBonusType;
import crazypants.enderio.base.recipe.RecipeLevel;
import crazypants.enderio.base.recipe.RecipeOutput;
import tfcreborncore.objects.RCItems;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.ThermalToolMaterialTypes;
import tfcreborncore.recipe.manager.EnderIORecipeManager;
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
                " G ",
                "RCR",
                " G ",
                'G', "sheetGold",
                'R', "ingotRedstone",
                'C', RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/rf_control_circuit"));

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

        for (ThermalToolMaterialTypes type : ThermalToolMaterialTypes.values()) {
            // Servo Recipes
            MinecraftRecipeManager.addShapedRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shaped/servo_" + type.getName()),
                    RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", type.getMeta(), 2),
                    "PGP",
                    "TRT",
                    'P', "strip" + RCItems.toPascalCase(type.getName()),
                    'G', "blockGlassHardened",
                    'T', "sheet" + RCItems.toPascalCase(type.getName()),
                    'R', "dustRedstone");
            // Filter Recipes
            MinecraftRecipeManager.addShapedRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shaped/filter_" + type.getName()),
                    RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", type.getMeta(), 2),
                    "PGP",
                    "TRT",
                    'P', "strip" + RCItems.toPascalCase(type.getName()),
                    'G', "blockGlassHardened",
                    'T', "sheet" + RCItems.toPascalCase(type.getName()),
                    'R', "paper");
            // Retriever Recipes
            MinecraftRecipeManager.addShapedRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shaped/retriever_" + type.getName()),
                    RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", type.getMeta(), 2),
                    "PGP",
                    "TRT",
                    'P', "strip" + RCItems.toPascalCase(type.getName()),
                    'G', "blockGlassHardened",
                    'T', "sheet" + RCItems.toPascalCase(type.getName()),
                    'R', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "ender_eye"));
        }
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
    public void registerSagMillRecipes(FMLPostInitializationEvent r) {
        // Blizz Powder
        EnderIORecipeManager.registerSagMillRecipe(
                "rodBlizz",
                new RecipeOutput[] {
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2049, 2)),
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2049), 0.20F)
                },
                1600,
                RecipeBonusType.CHANCE_ONLY,
                RecipeLevel.IGNORE);

        // Blitz Powder
        EnderIORecipeManager.registerSagMillRecipe(
                "rodBlitz",
                new RecipeOutput[] {
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2051, 2)),
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2051), 0.20F)
                },
                1600,
                RecipeBonusType.CHANCE_ONLY,
                RecipeLevel.IGNORE);

        // Basalz Powder
        EnderIORecipeManager.registerSagMillRecipe(
                "rodBasalz",
                new RecipeOutput[] {
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2053, 2)),
                        new RecipeOutput(RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", 2053), 0.20F)
                },
                1600,
                RecipeBonusType.CHANCE_ONLY,
                RecipeLevel.IGNORE);
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
