package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class TFCTechCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_TECH.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {}

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent event) {
        // Metal Processing
        // Tin Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/tin"),
                RecipeHelper.getIIngredient("ingotTin"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/tin_sleeve"),
                Metal.Tier.TIER_I,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);

        // Brass Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/brass"),
                RecipeHelper.getIIngredient("ingotBrass"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/brass_sleeve"),
                Metal.Tier.TIER_I,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);

        // Steel Sleeve
        TerrafirmacraftRecipeManager.addAnvilRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "anvil/working/sleeve/steel"),
                RecipeHelper.getIIngredient("ingotSteel"),
                RecipeHelper.getItemStack(Mods.TFC_TECH.ID, "metal/steel_sleeve"),
                Metal.Tier.TIER_IV,
                null,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY,
                ForgeRule.BEND_ANY);
    }
}
