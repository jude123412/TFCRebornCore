package tfcreborncore.recipe.handler;

import java.util.Arrays;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.enums.OreProcessingTypes;
import tfcreborncore.recipe.manager.ImmersiveEngineeringRecipeManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
import tfcreborncore.recipe.manager.TerrafirmacraftRecipeManager;

public class OreRegistryHandlerCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.TFC_METALLUM.ID);
    }

    @Override
    public void registerMinecraftRecipe(FMLPostInitializationEvent r) {
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/form/pellet/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    "formPellet",
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName()));

            // Double Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/form/pellet/double/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    "formPellet",
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/purified/" + type.getPrimaryName()));

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()));

            // Triple Pellet
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()));

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/pellet/triple/mix/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()));

            // Bar
            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet/" + type.getPrimaryName()),
                    "formIngot");

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName(), 0, 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_double/" + type.getPrimaryName()),
                    "formIngot");

            MinecraftRecipeManager.addShapelessRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID,
                            "crafting/shapeless/compressing/bar/pellet/triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName(), 0, 3),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/pellet_triple/" + type.getPrimaryName()),
                    "formIngot");
        }
    }

    @Override
    public void registerTerrafirmacraftRecipes(FMLPostInitializationEvent r) {
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_2"),
                    RecipeHelper.getIIngredient(
                            RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName())),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 2));

            // Poor Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_3"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 3));

            // Normal Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_5"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 5));

            // Rich Ore Quern
            TerrafirmacraftRecipeManager.addQuernRecipe(
                    new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName() + "_7"),
                    RecipeHelper.getIIngredient(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 7));
        }
    }

    @Override
    public void registerImmersiveEngineeringRecipes(FMLPostInitializationEvent r) {
        for (OreProcessingTypes type : OreProcessingTypes.values()) {
            // Small Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/small/" + type.getPrimaryName()),
                    RecipeHelper
                            .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 2),
                    new Object[] {
                            RecipeHelper
                                    .getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.10F
                    },
                    2000);

            // Poor Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 1),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 3),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.15F
                    },
                    3000);

            // Normal Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 0),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 5),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.25F
                    },
                    5000);

            // Rich Ore Crushing
            ImmersiveEngineeringRecipeManager.addCrusherRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "ore/" + type.getPrimaryName(), 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getPrimaryName(), 0, 7),
                    new Object[] {
                            RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/crushed/" + type.getProductA(), 0),
                            0.35F
                    },
                    7000);

            // Bar Arc Smelting
            ImmersiveEngineeringRecipeManager.addArcFurnaceRecipe(
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    new Object[] {
                            "dustFlux"
                    },
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + type.getProductB(), 0, 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/rich_slag"),
                    80,
                    1600);
            ImmersiveEngineeringRecipeManager.addArcFurnaceRecipe(
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    new Object[] {
                            "crystalSlagRich"
                    },
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + type.getProductB(), 0, 2),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/slag"),
                    80,
                    1600);
            ImmersiveEngineeringRecipeManager.addArcFurnaceRecipe(
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "ore/bar/" + type.getPrimaryName()),
                    new Object[] {
                            "gemCinnabar"
                    },
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + type.getProductB(), 0, 2),
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + type.getProductC()),
                    80,
                    1600);

            // Dust Arc Smelting
            ImmersiveEngineeringRecipeManager.addArcFurnaceRecipe(
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/dust/" + type.getProductB()),
                    new Object[] {
                            "dustFlux"
                    },
                    RecipeHelper.getItemStack(Mods.TERRAFIRMACRAFT.ID, "metal/ingot/" + type.getProductB()),
                    RecipeHelper.getItemStack(Mods.TFC_REBORN_CORE.ID, "regular/slag"),
                    80,
                    1600);
        }
    }
}
