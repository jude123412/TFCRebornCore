package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getFluidStack;
import static tfcreborncore.recipe.RecipeHelper.getItemStack;
import static tfcreborncore.recipe.mods.TFCRecipeHelper.H;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import tfcreborncore.Tags;
import tfcreborncore.objects.recipe.CraftingRecipeManager;
import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.mods.TFCRecipeHelper;

public class ExNihiloCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.FORESTRY.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable) event.getRegistry();

        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_wood"));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_stone"));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_iron"));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_diamond"));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_gold"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", OreDictionary.WILDCARD_VALUE));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_material", OreDictionary.WILDCARD_VALUE));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_crucible"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_barrel0"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_barrel1"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_waterwheel"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_axle_stone"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_auto_sifter"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_end_cake"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "block_crucible_wood"));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_doll", OreDictionary.WILDCARD_VALUE));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", OreDictionary.WILDCARD_VALUE));


        // String Mesh
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "string_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                "SSS",
                "SSS",
                "SSS",
                'S', "string");

        // Bronze Mesh
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "bronze_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 1),
                'W', "wireAnyBronze");

        // Wrought Iron Mesh
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "wrought_iron_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 2),
                'W', "wireIron");

        // Steel Mesh
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "steel_mesh"),
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 4),
                "WWW",
                "WMW",
                "WWW",
                'M', getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", 3),
                'W', "wireSteel");

        // Artificial Hive
        CraftingRecipeManager.addShapedRecipe(
                new ResourceLocation(Tags.MODID, "steel_mesh"),
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
        TFCRecipeHelper.addBarrelRecipe(r, IIngredient.of(getFluidStack("salt_water", 1000)),
                IIngredient.of(getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_material", 3)),
                getFluidStack("witchwater", 1000), ItemStack.EMPTY, 0, "witch_water_from_ancient_spores");
        TFCRecipeHelper.addBarrelRecipeFluidMixin(r, IIngredient.of(getFluidStack("salt_water", 9000)),
                getFluidStack("witchwater", 1000), getFluidStack("witchwater", 10000), 0,
                "witch_water_from_witch_water");

        // Scented Hive
        TFCRecipeHelper.addBarrelRecipe(r, IIngredient.of(getFluidStack("seed.oil", 1000)),
                IIngredient.of(RecipeHelper.getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 0)), null, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hive", 1), 32 * H,
                "scented_hive");
    }
}
