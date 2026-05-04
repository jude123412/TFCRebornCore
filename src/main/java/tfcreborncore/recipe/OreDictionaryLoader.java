package tfcreborncore.recipe;

import net.dries007.tfc.ConfigTFC;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.enums.Mods;

public class OreDictionaryLoader {

    /**
     * These are global ore dictionaries
     * as stated these are needed everywhere
     * so they have to be registered before everything
     */
    public static void register() {
        // Minecraft
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "ender_pearl"));
        // GemEnderEye
        OreDictionary.registerOre("gemEnderEye",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "ender_eye"));
        // DustGunpowder
        OreDictionary.registerOre("dustGunpowder",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "gunpowder"));

        // Terrafirmacraft
        // ClothLowQuality
        OreDictionary.registerOre("clothLowQuality",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "crop/product/burlap_cloth"));
        // ClothMediumQuality
        OreDictionary.registerOre("clothMediumQuality",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "animal/product/wool_cloth"));
        // ClothAnyMediumQuality
        OreDictionary.registerOre("clothAnyMediumQuality",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "animal/product/wool_cloth"));
        OreDictionary.registerOre("clothAnyMediumQuality",
                RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "animal/product/silk_cloth"));

        if (Mods.FORESTRY.isModLoaded()) {
            // Sweetener
            OreDictionary.registerOre("sweetener",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honey_drop"));
            OreDictionary.registerOre("sweetener",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honeydew"));
            // Pollen
            OreDictionary.registerOre("itemPollen",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "pollen", 1));
            // Category Fruit
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 0));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 3));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 4));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 5));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 6));
            // Category Grain
            OreDictionary.registerOre("categoryGrain", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 1));
            OreDictionary.registerOre("categoryGrain", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 2));
            // Pollen
            OreDictionary.registerOre("stickImpregnated",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "oak_stick"));
        }

        if (Mods.TFC_TECH.isModLoaded()) {
            // Any Bronze Strip
            OreDictionary.registerOre("stripAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_strip"));
            OreDictionary.registerOre("stripAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_strip"));
            OreDictionary.registerOre("stripAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_strip"));

            // Any Bronze Rackwheel Piece
            OreDictionary.registerOre("rackwheelPieceAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_rackwheel_piece"));
            OreDictionary.registerOre("rackwheelPieceAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_rackwheel_piece"));
            OreDictionary.registerOre("rackwheelPieceAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_rackwheel_piece"));

            // Any Bronze Rackwheel
            OreDictionary.registerOre("rackwheelAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_rackwheel"));
            OreDictionary.registerOre("rackwheelAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_rackwheel"));
            OreDictionary.registerOre("rackwheelAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_rackwheel"));

            // Any Bronze Gear
            OreDictionary.registerOre("gearAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_gear"));
            OreDictionary.registerOre("gearAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_gear"));
            OreDictionary.registerOre("gearAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_gear"));

            // Any Bronze Wire
            OreDictionary.registerOre("wireAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_wire"));
            OreDictionary.registerOre("wireAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_wire"));
            OreDictionary.registerOre("wireAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_wire"));

            // Any Bronze Long Rod
            OreDictionary.registerOre("stickLongAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_long_rod"));
            OreDictionary.registerOre("stickLongAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_long_rod"));
            OreDictionary.registerOre("stickLongAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_long_rod"));

            // Any Bronze Rod
            OreDictionary.registerOre("stickAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_rod"));
            OreDictionary.registerOre("stickAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_rod"));
            OreDictionary.registerOre("stickAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_rod"));

            // Any Bronze Bolt
            OreDictionary.registerOre("boltAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_bolt"));
            OreDictionary.registerOre("boltAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_bolt"));
            OreDictionary.registerOre("boltAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_bolt"));

            // Any Bronze Screw
            OreDictionary.registerOre("screwAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/black_bronze_screw"));
            OreDictionary.registerOre("screwAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bismuth_bronze_screw"));
            OreDictionary.registerOre("screwAnyBronze",
                    RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/bronze_screw"));

            if (ConfigTFC.General.MISC.dictionaryIron) {
                // Iron → Wrought Iron Parts
                OreDictionary.registerOre("groveIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/iron_groove"));
                OreDictionary.registerOre("bowlMountIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/iron_bowl_mount"));
                OreDictionary.registerOre("drawPlateIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/iron_draw_plate"));
                OreDictionary.registerOre("stripIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_strip"));
                OreDictionary.registerOre("rackwheelPieceIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_rackwheel_piece"));
                OreDictionary.registerOre("rackwheelIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_rackwheel"));
                OreDictionary.registerOre("gearIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_gear"));
                OreDictionary.registerOre("wireIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_wire"));
                OreDictionary.registerOre("stickLongIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_long_rod"));
                OreDictionary.registerOre("stickIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_rod"));
                OreDictionary.registerOre("boltIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_bolt"));
                OreDictionary.registerOre("screwIron",
                        RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/wrought_iron_screw"));
            }
        }
    }
}
