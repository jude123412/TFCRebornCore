package tfcreborncore.recipe.compat;

import static tfcreborncore.recipe.RecipeHelper.getItemStack;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

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
                Mods.TFC_METALLUM.ID,
                Mods.EX_NIHILO_CREATIO.ID,
                Mods.IMMERSIVE_ENGINEERING.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack("minecraft", "ender_pearl"));
        // GemEnderEye
        OreDictionary.registerOre("gemEnderEye",
                RecipeHelper.getItemStack("minecraft", "ender_eye"));
        // DustGunpowder
        OreDictionary.registerOre("dustGunpowder",
                RecipeHelper.getItemStack("minecraft", "gunpowder"));
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByOutput(getItemStack(Mods.MINECRAFT.ID, "blaze_powder"));
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {}

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        // Redstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "redstone_ingot_recycling"),
                RecipeHelper.getIIngredient("ingotRedstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"));

        // Glowstone Ingot Recycling
        TerrafirmacraftRecipeManager.addQuernRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "glowstone_ingot_recycling"),
                RecipeHelper.getIIngredient("ingotGlowstone"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"));

        // Redstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/redstone"),
                RecipeHelper.getIIngredient("witchwater", 100),
                RecipeHelper.getIIngredient("dustCopper"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone"),
                8);

        // Glowstone Dust from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/glowstone"),
                RecipeHelper.getIIngredient("witchwater", 100),
                RecipeHelper.getIIngredient("dustGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "glowstone_dust"),
                8);

        // Enderpearl Powder from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/enderpearl"),
                RecipeHelper.getIIngredient("witchwater", 100),
                RecipeHelper.getIIngredient("dustBismuth"),
                null,
                RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "item/enderpearl_powder"),
                8);

        // Blaze Rod from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/blaze_rod"),
                RecipeHelper.getIIngredient("witchwater", 250),
                RecipeHelper.getIIngredient("stickRoseGold"),
                null,
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "blaze_rod"),
                8);

        // Obsidian from Witch Water
        TerrafirmacraftRecipeManager.addBarrelRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "barrel/transform/obsidian"),
                RecipeHelper.getIIngredient("witchwater", 250),
                RecipeHelper.getIIngredient("cobblestone"),
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
}
