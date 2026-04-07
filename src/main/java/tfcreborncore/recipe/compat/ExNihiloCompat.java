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
                Mods.EX_NIHILO_CREATIO.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable) event.getRegistry();

        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_wood", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_stone", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_iron", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_diamond", 0));
        RecipeHelper.removeRecipeByOutput(registry, getItemStack(Mods.EX_NIHILO_CREATIO.ID, "hammer_gold", 0));
        RecipeHelper.removeRecipeByOutput(registry,
                getItemStack(Mods.EX_NIHILO_CREATIO.ID, "item_mesh", OreDictionary.WILDCARD_VALUE));

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
    }
}
