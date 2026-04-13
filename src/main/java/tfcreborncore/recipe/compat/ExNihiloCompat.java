package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;
import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
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
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_wood"));
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_stone"));
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_iron"));
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_diamond"));
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_gold"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_material", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_crucible"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_barrel0"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_barrel1"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_waterwheel"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_axle_stone"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_auto_sifter"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_end_cake"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_crucible_wood"));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_doll", OreDictionary.WILDCARD_VALUE));
        MinecraftRecipeManager.removeRecipeByOutput(
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", OreDictionary.WILDCARD_VALUE));
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // String Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "string_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                "SSS",
                "SSS",
                "SSS",
                'S', "string");

        // Bronze Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "bronze_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                'W', "wireAnyBronze");

        // Wrought Iron Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "wrought_iron_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                'W', "wireIron");

        // Steel Mesh
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "steel_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 4),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                'W', "wireSteel");

        // Artificial Hive
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "artificial_hive"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 0),
                "SSS",
                "SHS",
                "SSS",
                'H', getItemStack(Mods.MINECRAFT.ID, "hay_block"),
                'S', "string");
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        // Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Tags.MODID, "barrel/transform/witch_water"),
                IIngredient.of(getFluidStack("salt_water", 1000)),
                IIngredient.of(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_material", 3)),
                getFluidStack("witchwater", 1000), ItemStack.EMPTY, 8);
        TerrafirmacraftRecipeManager.addBarrelRecipeFluidMixin(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/mixing/witch_water"),
                IIngredient.of(getFluidStack("salt_water", 9000)),
                getFluidStack("witchwater", 1000), getFluidStack("witchwater", 10000), 0);

        // Scented Hive
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Tags.MODID, "barrel/transform/scented_hive"),
                IIngredient.of(getFluidStack("seed.oil", 1000)),
                IIngredient.of(RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 0)), null,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 1), 32);
    }

    @Override
    public void registerSieveRecipes(FMLPostInitializationEvent e) {
        // Gravel Sieving
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/tetrahedrite"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/native_copper"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/malachite"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/sphalerite"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/bismuthinite"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/cassiterite"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/native_gold"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/small/native_silver"), 0.04F,
                0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/lapis_lazuli"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/cryolite"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("tfc", "ore/gypsum"), 0.04F, 0.04F);
        ExNihiloRecipeManager.registerSieveRecipe("gravel", getItemStack("minecraft", "flint"), 0.04F, 0.04F);

        // Dirt Sieving
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("exnihilocreatio", "item_material", 3), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("firmalife", "crop/seeds/pumpkin"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("firmalife", "crop/seeds/melon"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/barley"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/maize"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/oat"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/rice"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/rye"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/wheat"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/beet"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/cabbage"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/carrot"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/garlic"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/green_bean"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/onion"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/potato"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/soybean"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/squash"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/sugarcane"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/tomato"), 0.02F, 0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/red_bell_pepper"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/yellow_bell_pepper"), 0.02F,
                0.02F);
        ExNihiloRecipeManager.registerSieveRecipe("dirt", getItemStack("tfc", "crop/seeds/jute"), 0.02F, 0.02F);
    }
}
