package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.Tags;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;
import tfcreborncore.types.DefaultMetals;

public class BaubleliciousCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.BAUBLELICIOUS.ID,
                Mods.BUBBLES.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_TECH.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BAUBLELICIOUS.ID);
    }

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {
        // Amulet
        MinecraftRecipeManager.addShapelessDamageRecipe(
                new ResourceLocation(Tags.MODID, "amulet"),
                1,
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                "knife",
                "leather");

        // Belt
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Tags.MODID, "belt"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "wiredraw/leather_belt"),
                "stripGold");

        // Speed Belt
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "belt/speed"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemspeedbelt"),
                " G ",
                "SBS",
                " I ",
                'G', "gemFlawedEmerald",
                'S', "sweetener",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                'I', "ingotAnyBronze");
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "belt/speed_alt"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemspeedbelt"),
                " G ",
                "SBS",
                " I ",
                'G', "gemFlawedJade",
                'S', "sweetener",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                'I', "ingotAnyBronze");

        // Ring of Flight
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "ring/flight"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemringofflight"),
                " G ",
                "SBS",
                " I ",
                'G', "gemExquisiteTourmaline",
                'S', "feather",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"),
                'I', "ingotBlackSteel");

        // Belt of Water Walking
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "belt/water_walking"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembeltwaterwalking"),
                " G ",
                "SBS",
                " I ",
                'G', "gemExquisiteBeryl",
                'S', "sheetArdite",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                'I', "ingotSteel");

        // Amulet of Diving
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "amulet/diving"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemdivingamulet"),
                " G ",
                "SBS",
                " I ",
                'G', "gemSapphire",
                'S', "stickRoseGold",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                'I', "ingotIron");

        // Falling Belt
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "belt/falling"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemfallingbelt"),
                " G ",
                "SBS",
                " I ",
                'G', "gemAmethyst",
                'S', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "feather"),
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                'I', "ingotIron");

        // Amulet of Nightvision
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "amulet/night_vision"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamuletnightvision"),
                " G ",
                "SBS",
                " I ",
                'G', "gemAmethyst",
                'S', RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "crop/seeds/carrot"),
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                'I', "ingotIron");

        // Amulet of the Fiery Core
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "amulet/fiery_core"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamuletfierycore"),
                " G ",
                "SBS",
                " I ",
                'G', "gemExquisiteTopaz",
                'S', "stickSignalum",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                'I', "ingotSteel");
    }

    @Override
    public void registerItemModification(FMLPostInitializationEvent r) {
        // Gold Ring = Gold
        TerrafirmacraftRecipeManager.addItemMetal(RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"),
                DefaultMetals.GOLD,
                100, true);
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Gold Ring
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/gold_ring"),
                RecipeHelper.getIIngredient("ingotGold"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"), Metal.Tier.TIER_I, null,
                ForgeRule.BEND_ANY, ForgeRule.BEND_ANY, ForgeRule.BEND_ANY);
    }
}
