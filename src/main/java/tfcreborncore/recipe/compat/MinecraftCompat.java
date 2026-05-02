package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.S;
import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.ForestryRecipeManager;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class MinecraftCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.IMMERSIVE_ENGINEERING.ID,
                Mods.FORESTRY.ID,
                Mods.TFC_METALLUM.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.MINECRAFT.ID, "blaze_powder"));
    }

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {}

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Redstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "quern/dust/redstone"),
                IIngredient.of("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"));

        // Glowstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "quern/dust/glowstone"),
                IIngredient.of("ingotGlowstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"));

        // Redstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/redstone"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustCopper"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"),
                8);

        // Glowstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/glowstone"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"),
                8);

        // Enderpearl Powder from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/enderpearl"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 100)),
                IIngredient.of("dustBismuth"),
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/enderpearl_powder"),
                8);

        // Blaze Rod from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/blaze_rod"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("stickRoseGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_rod"),
                8);

        // Obsidian from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/obsidian"),
                IIngredient.of(RecipeHelper.getFluidStack("witchwater", 250)),
                IIngredient.of("cobblestone"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "obsidian"),
                72);
    }

    @Override
    public void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {
        // Blaze Powder
        ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                "rodBlaze",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_powder", 0, 3),
                new Object[] {
                        RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_powder", 0), 0.50F
                },
                1600);
    }

    @Override
    public void registerForestryRecipes(FMLPostInitializationEvent r) {
        // Paper
        ForestryRecipeManager.addCarpenterRecipe(10 * S,
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "paper"),
                RecipeHelper.getFluidStack("fresh_water", 250),
                "W",
                "W",
                'W', "dustWood");
    }
}
