package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;

public class TFCTechCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_TECH.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> e) {
        // TFC Tech
        // Any Bronze Strip
        OreDictionary.registerOre("stripAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_strip"));
        OreDictionary.registerOre("stripAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_strip"));
        OreDictionary.registerOre("stripAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_strip"));

        // Any Bronze Rackwheel Piece
        OreDictionary.registerOre("rackwheelPieceAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_rackwheel_piece"));
        OreDictionary.registerOre("rackwheelPieceAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_rackwheel_piece"));
        OreDictionary.registerOre("rackwheelPieceAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_rackwheel_piece"));

        // Any Bronze Rackwheel
        OreDictionary.registerOre("rackwheelAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_rackwheel"));
        OreDictionary.registerOre("rackwheelAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_rackwheel"));
        OreDictionary.registerOre("rackwheelAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_rackwheel"));

        // Any Bronze Gear
        OreDictionary.registerOre("gearAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_gear"));
        OreDictionary.registerOre("gearAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_gear"));
        OreDictionary.registerOre("gearAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_gear"));

        // Any Bronze Wire
        OreDictionary.registerOre("wireAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_wire"));
        OreDictionary.registerOre("wireAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_wire"));
        OreDictionary.registerOre("wireAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_wire"));

        // Any Bronze Long Rod
        OreDictionary.registerOre("stickLongAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_long_rod"));
        OreDictionary.registerOre("stickLongAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_long_rod"));
        OreDictionary.registerOre("stickLongAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_long_rod"));

        // Any Bronze Rod
        OreDictionary.registerOre("stickAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_rod"));
        OreDictionary.registerOre("stickAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_rod"));
        OreDictionary.registerOre("stickAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_rod"));

        // Any Bronze Bolt
        OreDictionary.registerOre("boltAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_bolt"));
        OreDictionary.registerOre("boltAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_bolt"));
        OreDictionary.registerOre("boltAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_bolt"));

        // Any Bronze Screw
        OreDictionary.registerOre("screwAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/black_bronze_screw"));
        OreDictionary.registerOre("screwAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bismuth_bronze_screw"));
        OreDictionary.registerOre("screwAnyBronze",
                RecipeHelper.getItemStack("tfctech", "metal/bronze_screw"));

        // Iron → Wrought Iron Parts
        OreDictionary.registerOre("groveIron",
                RecipeHelper.getItemStack("tfctech", "metal/iron_groove"));
        OreDictionary.registerOre("bowlMountIron",
                RecipeHelper.getItemStack("tfctech", "metal/iron_bowl_mount"));
        OreDictionary.registerOre("drawPlateIron",
                RecipeHelper.getItemStack("tfctech", "metal/iron_draw_plate"));
        OreDictionary.registerOre("stripIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_strip"));
        OreDictionary.registerOre("rackwheelPieceIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_rackwheel_piece"));
        OreDictionary.registerOre("rackwheelIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_rackwheel"));
        OreDictionary.registerOre("gearIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_gear"));
        OreDictionary.registerOre("wireIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_wire"));
        OreDictionary.registerOre("stickLongIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_long_rod"));
        OreDictionary.registerOre("stickIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_rod"));
        OreDictionary.registerOre("boltIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_bolt"));
        OreDictionary.registerOre("screwIron",
                RecipeHelper.getItemStack("tfctech", "metal/wrought_iron_screw"));
    }
}
