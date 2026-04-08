package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.objects.recipe.CraftingRecipeManager;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.mods.TFCRecipeHelper;

public class BaubleliciousCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.BAUBLELICIOUS.ID,
                Mods.BAUBLES.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.TFC_TECH.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        RecipeHelper.removeRecipeByOutput(r, RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamulet"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itembelt"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemspeedbelt"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemgrowthpendant"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemringofflight"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itembeltwaterwalking"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemdivingamulet"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemfallingbelt"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamuletnightvision"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamuletfierycore"));
        RecipeHelper.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "item.itemmagiccore", OreDictionary.WILDCARD_VALUE));

        // Amulet
        CraftingRecipeManager.addShapelessDamageRecipe(
                new ResourceLocation(Tags.MODID, "amulet"),
                1,
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                "knife",
                "leather");

        // Belt
        CraftingRecipeManager.addShapelessRecipe(
                new ResourceLocation(Tags.MODID, "belt"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "wiredraw/leather_belt"),
                "stripGold");

        // Speed Belt
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "belt/speed"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemspeedbelt"),
                " G ",
                "SBS",
                " I ",
                'G', "gemFlawedEmerald",
                'S', "sweetener",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itembelt"),
                'I', "ingotAnyBronze");
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
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
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "amulet/fiery_core"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamuletfierycore"),
                " G ",
                "SBS",
                " I ",
                'G', "gemExquisiteTopaz",
                'S', "stickSignalum",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemamulet"),
                'I', "ingotSteel");

        // Miner's Ring
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "ring/miners"),
                RecipeHelper.getItemStack(Mods.BAUBLES.ID, "ring"),
                " G ",
                "SBS",
                " I ",
                'G', "gemFlawedOpal",
                'S', "stickSterlingSilver",
                'B', RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"),
                'I', "ingotAnyBronze");
    }

    @Override
    public void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {
        TFCRecipeHelper.addAnvilRecipe(r, "gold_ring", IIngredient.of("ingotGold"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"), Metal.Tier.TIER_I, null,
                ForgeRule.BEND_ANY, ForgeRule.BEND_ANY, ForgeRule.BEND_ANY);
    }
}
