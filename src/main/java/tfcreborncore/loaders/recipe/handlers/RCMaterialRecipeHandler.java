package tfcreborncore.loaders.recipe.handlers;

import static gregtech.api.GTValues.L;
import static gregtech.api.GTValues.ULV;
import static gregtech.api.GTValues.VA;
import static tfcreborncore.api.util.RCUtility.S;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

public class RCMaterialRecipeHandler {

    public static void register() {
        OrePrefix.dust.addProcessingHandler(PropertyKey.DUST, RCMaterialRecipeHandler::processMaterialRecipes);
    }

    /*
     * Recipes don't get removed in dev environment
     * Always test outside dev environment
     */
    public static void processMaterialRecipes(OrePrefix ingotPrefix, Material material, DustProperty property) {
        if (OrePrefix.plate.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_PLATE.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                    .fluidInputs(material.getFluid(100))
                    .output(OrePrefix.plate, material, 1)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.gear.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_GEAR.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L * 4)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_GEAR)
                    .fluidInputs(material.getFluid(400))
                    .output(OrePrefix.gear, material, 1)
                    .duration((int) material.getMass() * 4)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.ingot.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_INGOT.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                    .fluidInputs(material.getFluid(100))
                    .output(OrePrefix.ingot, material, 1)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (!OreDictUnifier.get(OrePrefix.block, material).isEmpty() && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_BLOCK.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L * 9)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                    .fluidInputs(material.getFluid(900))
                    .output(OrePrefix.block, material, 1)
                    .duration((int) material.getMass() * 9)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.nugget.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_NUGGET.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_NUGGET)
                    .fluidInputs(material.getFluid(100))
                    .output(OrePrefix.nugget, material, 10)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.gearSmall.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_GEAR_SMALL.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_GEAR_SMALL)
                    .fluidInputs(material.getFluid(100))
                    .output(OrePrefix.gearSmall, material, 1)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.rotor.doGenerateItem(material) && material.hasProperty(PropertyKey.FLUID)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.FLUID_SOLIDFICATION_RECIPES,
                    new ItemStack[] {
                            MetaItems.SHAPE_MOLD_ROTOR.getStackForm(1)
                    },
                    new FluidStack[] {
                            material.getFluid(L * 4)
                    });

            RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_ROTOR)
                    .fluidInputs(material.getFluid(400))
                    .output(OrePrefix.rotor, material, 1)
                    .duration((int) material.getMass() * 4)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }

        if (OrePrefix.nugget.doGenerateItem(material) && OrePrefix.ingot.doGenerateItem(material)) {
            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ALLOY_SMELTER_RECIPES,
                    MetaItems.SHAPE_MOLD_INGOT.getStackForm(1),
                    OreDictUnifier.get(OrePrefix.nugget, material, 9));

            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.ALLOY_SMELTER_RECIPES,
                    MetaItems.SHAPE_MOLD_NUGGET.getStackForm(1),
                    OreDictUnifier.get(OrePrefix.ingot, material, 1));

            GTRecipeHandler.removeRecipesByInputs(RecipeMaps.COMPRESSOR_RECIPES,
                    OreDictUnifier.get(OrePrefix.nugget, material, 9));

            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_NUGGET)
                    .input(OrePrefix.ingot, material)
                    .output(OrePrefix.nugget, material, 10)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();

            RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder()
                    .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                    .input(OrePrefix.nugget, material, 10)
                    .output(OrePrefix.ingot, material)
                    .duration((int) material.getMass())
                    .EUt(VA[ULV])
                    .buildAndRegister();

            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder()
                    .input(OrePrefix.nugget, material, 10)
                    .output(OrePrefix.ingot, material, 1)
                    .duration(S * 15)
                    .EUt(VA[ULV])
                    .buildAndRegister();
        }
    }
}
