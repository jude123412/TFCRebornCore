package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getItemStack;
import static tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager.H;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class MinecraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_TECH.ID,
                Mods.TFC_METALLUM.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack("minecraft", "ender_pearl"));
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        MinecraftRecipeManager.removeRecipeByOutput(r, getItemStack(Mods.MINECRAFT.ID, "blaze_powder"));
    }

    @Override
    public void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {
        // Redstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/redstone"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustCopper"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"),
                8 * H);

        // Glowstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/glowstone"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"),
                8 * H);

        // Enderpearl Powder from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/enderpearl"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustBismuth"),
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/enderpearl_powder"),
                8 * H);

        // Blaze Rod from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/blaze_rod"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("stickRoseGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_rod"),
                8 * H);

        // Obsidian from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(r,
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/obsidian"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("cobblestone"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian"),
                72 * H);
    }

    @Override
    public void registerCrusherRecipes(FMLPostInitializationEvent r) {
        // Blaze Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "rodBlaze",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_powder", 0, 3),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_powder", 0), 0.50F
                },
                1600);
    }
}
