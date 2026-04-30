package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.Tags;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ExNihiloRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class ExNihiloCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.FORESTRY.ID,
                Mods.TERRAFIRMACRAFT.ID,
                Mods.FIRMALIFE.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.EX_NIHILO_CREATIO.ID);
    }

    @Override
    public void registerCraftingRecipe(FMLPostInitializationEvent r) {
        // String Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "string_mesh"),
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                "SSS",
                "SSS",
                "SSS",
                'S', "string");

        // Bronze Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "bronze_mesh"),
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                "WWW",
                "WMW",
                "WWW",
                'M', RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                'W', "wireAnyBronze");

        // Wrought Iron Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "wrought_iron_mesh"),
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                "WWW",
                "WMW",
                "WWW",
                'M', RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                'W', "wireIron");

        // Steel Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "steel_mesh"),
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 4),
                "WWW",
                "WMW",
                "WWW",
                'M', RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                'W', "wireSteel");

        // Artificial Hive
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "artificial_hive"),
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 0),
                "SSS",
                "SHS",
                "SSS",
                'H', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "hay_block"),
                'S', "string");
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Tags.MODID, "barrel/transform/witch_water"),
                RecipeHelper.getIIngredient("salt_water", 1000),
                RecipeHelper.getIIngredient(Mods.EX_NIHILO_CREATIO.ID, "item_material", 3),
                RecipeHelper.getFluidStack("witchwater", 1000), ItemStack.EMPTY, 8);
        TerrafirmacraftRecipeManager.addBarrelRecipeFluidMixin(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/mixing/witch_water"),
                RecipeHelper.getIIngredient("salt_water", 9000),
                RecipeHelper.getFluidStack("witchwater", 1000), RecipeHelper.getFluidStack("witchwater", 10000), 0);

        // Scented Hive
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Tags.MODID, "barrel/transform/scented_hive"),
                RecipeHelper.getIIngredient("seed.oil", 1000),
                RecipeHelper.getIIngredient(Mods.EX_NIHILO_CREATIO.ID, "hive", 0), null,
                RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 1), 32);
    }

    @Override
    public void registerExNihiloRecipes(FMLPostInitializationEvent e) {
        // Gravel Sieving
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/tetrahedrite"),
                0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/native_copper"),
                0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/malachite"),
                0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/sphalerite"),
                0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/bismuthinite"),
                0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/cassiterite"),
                0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/native_gold"),
                0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/small/native_silver"),
                0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/lapis_lazuli"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/cryolite"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("tfc", "ore/gypsum"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", RecipeHelper.getItemStack("minecraft", "flint"), 0.04F,
                0.04F);

        // Dirt Sieving
        ExNihiloRecipeManager.registerSieveRecipe("dirt",
                RecipeHelper.getItemStack("exnihilocreatio", "item_material", 3), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("firmalife", "crop/seeds/pumpkin"),
                0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("firmalife", "crop/seeds/melon"),
                0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/barley"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/maize"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/oat"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/rice"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/rye"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/wheat"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/beet"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/cabbage"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/carrot"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/garlic"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/green_bean"),
                0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/onion"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/potato"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/soybean"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/squash"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/sugarcane"),
                0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/tomato"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt",
                RecipeHelper.getItemStack("tfc", "crop/seeds/red_bell_pepper"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt",
                RecipeHelper.getItemStack("tfc", "crop/seeds/yellow_bell_pepper"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", RecipeHelper.getItemStack("tfc", "crop/seeds/jute"), 0.02F,
                0.02F);
    }
}
