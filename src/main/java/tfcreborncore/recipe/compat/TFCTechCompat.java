package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.enums.ItemRCMetalType;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfctech.objects.items.metal.ItemTechMetal;

public class TFCTechCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_TECH.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_REBORN_CORE.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
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

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        // Metal Processing
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                // Rackwheel Recipe Removal
                MinecraftRecipeManager.removeRecipeByOutput(
                        RecipeHelper.getItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)));
            }
        }
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent event) {
        // Metal Processing
        // Tin Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/tin"),
                RecipeHelper.getIIngredient("ingotTin"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/tin_sleeve"),
                Metal.Tier.TIER_I,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);

        // Brass Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/brass"),
                RecipeHelper.getIIngredient("ingotBrass"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/brass_sleeve"),
                Metal.Tier.TIER_I,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);

        // Steel Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/steel"),
                RecipeHelper.getIIngredient("ingotSteel"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/steel_sleeve"),
                Metal.Tier.TIER_IV,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);

        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                // Rackwheel Piece
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(
                                Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.RACKWHEEL_PIECE +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)),
                        metal.getTier(),
                        null,
                        ForgeRule.UPSET_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST,
                        ForgeRule.UPSET_LAST);

                // Rod
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(
                                Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.ROD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.ROD)),
                        metal.getTier(),
                        null,
                        ForgeRule.HIT_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST,
                        ForgeRule.HIT_LAST);

                // Strip
                TerrafirmacraftRecipeManager.addAnvilRecipe(
                        new ResourceLocation(
                                Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.STRIP +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemMetal.get(metal, Metal.ItemType.INGOT)),
                        RecipeHelper.getItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.STRIP)),
                        metal.getTier(),
                        null,
                        ForgeRule.HIT_ANY,
                        ForgeRule.HIT_ANY,
                        ForgeRule.SHRINK_ANY);

                // Rackwheel
                TerrafirmacraftRecipeManager.addWeldingRecipe(
                        new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                                "anvil/welding/" + ItemTechMetal.ItemType.RACKWHEEL + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        RecipeHelper.getIIngredient(ItemRCMetal.get(metal, ItemRCMetalType.RACKWHEEL_HALF)),
                        RecipeHelper.getIIngredient(ItemRCMetal.get(metal, ItemRCMetalType.RACKWHEEL_HALF)),
                        RecipeHelper.getItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)),
                        metal.getTier(),
                        null);
            }
        }
    }
}
