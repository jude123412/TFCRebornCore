package tfcreborncore.recipe.compat;

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
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class ThermalExpansionCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_METALLUM.ID,
                Mods.TFC_TECH.ID,
                Mods.THERMAL_FOUNDATION.ID,
                Mods.THERMAL_DYNAMICS.ID,
                Mods.THERMAL_INNOVATION.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Recipe Removal
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "glass", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r, RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "wrench"));
        MinecraftRecipeManager.removeRecipeByOutput(r, RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "meter"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "upgrade", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "glass", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "security"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "diagram_redprint"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "tome_lexicon"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "tome_experience"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "fertilizer", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "material", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_FOUNDATION.ID, "security"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_0", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_16", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_32", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_48", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "duct_64", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "servo", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "filter", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "retriever", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r, RecipeHelper.getItemStack(Mods.THERMAL_DYNAMICS.ID, "relay"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "drill", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "saw", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "magnet", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "injector", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack(Mods.THERMAL_INNOVATION.ID, "quiver", OreDictionary.WILDCARD_VALUE));

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
    }

    @Override
    public void registerSagMillRecipes(FMLPostInitializationEvent r) {}
}
