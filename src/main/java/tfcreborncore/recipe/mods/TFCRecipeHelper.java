package tfcreborncore.recipe.mods;

import java.util.function.Supplier;

import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.MetalItemHandler;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFluidMixing;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;

public class TFCRecipeHelper {

    public static int H = 1000;

    @SuppressWarnings("unchecked")
    public static void addItemMetal(ItemStack inputStack, ResourceLocation metalLocation, int amount, boolean canMelt) {
        Metal metal = TFCRegistries.METALS.getValue(metalLocation);
        CapabilityMetalItem.CUSTOM_METAL_ITEMS.computeIfAbsent(IIngredient.of(inputStack),
                k -> (Supplier) () -> new MetalItemHandler(metal, amount, canMelt));
    }

    public static void addAnvilRecipe(IForgeRegistry<AnvilRecipe> r, String regName, IIngredient<ItemStack> inputStack,
                                      ItemStack outputStack, Metal.Tier minTier, SmithingSkill.Type skillType,
                                      ForgeRule... forgeRules) {
        r.register(new AnvilRecipe(new ResourceLocation(Tags.MODID, "anvil/" + regName.toLowerCase()), inputStack,
                outputStack, minTier, skillType, forgeRules));
    }

    public static void addBarrelRecipe(IForgeRegistry<BarrelRecipe> r, IIngredient<FluidStack> inputFluid,
                                       IIngredient<ItemStack> inputStack, FluidStack outputFluid, ItemStack outputStack,
                                       int duration, String regName) {
        r.register(new BarrelRecipe(inputFluid, inputStack, outputFluid, outputStack, duration)
                .setRegistryName(new ResourceLocation(Tags.MODID, "barrel/" + regName.toLowerCase())));
    }

    public static void addBarrelRecipeFluidMixin(IForgeRegistry<BarrelRecipe> r, IIngredient<FluidStack> inputFluid1,
                                                 FluidStack inputFluid2, FluidStack outputFluid, int duration,
                                                 String regName) {
        r.register(new BarrelRecipeFluidMixing(inputFluid1, new IngredientFluidItem(inputFluid2), outputFluid, duration)
                .setRegistryName(new ResourceLocation(Tags.MODID, "barrel/mixing/" + regName.toLowerCase())));
    }
}
