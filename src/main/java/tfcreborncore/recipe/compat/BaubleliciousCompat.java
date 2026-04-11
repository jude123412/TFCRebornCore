package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.types.DefaultMetals;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

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
        MinecraftRecipeManager.removeRecipeByOutput(r, RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamulet"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itembelt"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemspeedbelt"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemgrowthpendant"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemringofflight"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itembeltwaterwalking"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemdivingamulet"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemfallingbelt"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamuletnightvision"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "itemamuletfierycore"));
        MinecraftRecipeManager.removeRecipeByOutput(r,
                RecipeHelper.getItemStack("baublelicious", "item.itemmagiccore", OreDictionary.WILDCARD_VALUE));

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

        // Miner's Ring
        MinecraftRecipeManager.addShapedRecipe(
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
    public void registerItemMetal(FMLPostInitializationEvent r) {
        TerrafirmacraftRecipeManager.addItemMetal(RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"),
                DefaultMetals.GOLD,
                100, true);
    }

    @Override
    public void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {
        TerrafirmacraftRecipeManager.addAnvilRecipe(r, "gold_ring", IIngredient.of("ingotGold"),
                RecipeHelper.getItemStack(Mods.BAUBLELICIOUS.ID, "itemring"), Metal.Tier.TIER_I, null,
                ForgeRule.BEND_ANY, ForgeRule.BEND_ANY, ForgeRule.BEND_ANY);
    }
}
