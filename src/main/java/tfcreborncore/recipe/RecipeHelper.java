package tfcreborncore.recipe;

import net.dries007.tfc.objects.recipes.RecipeUtils;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tfcreborncore.TFCRebornCore;

import java.util.ArrayList;
import java.util.List;

public class RecipeHelper {

    // Returns an item or ItemStack.EMPTY if item doesn't exist
    public static ItemStack getItemStack(String modId, String itemId, int metaId) {
        if (modId == null || itemId == null) return ItemStack.EMPTY;
        ResourceLocation location = new ResourceLocation(modId, itemId);
        Item item = ForgeRegistries.ITEMS.getValue(location);
        if (item == null || item == Items.AIR) return ItemStack.EMPTY;
        return new ItemStack(item, 1, metaId);
    }

    // Returns a fluid
    public static Fluid getFluid(String fluidName) {
        return FluidRegistry.getFluid(fluidName);
    }

    // Returns a fluidStack
    public static FluidStack getFluidStack(String fluidName, int amount) {
        return FluidRegistry.getFluidStack(fluidName, amount);
    }

    public static void removeRecipeByOutput(IForgeRegistry<IRecipe> registry, ItemStack output) {
        List<ResourceLocation> toRemove = new ArrayList<>();

        for (IRecipe recipe : registry.getValuesCollection()) {
            ItemStack recipeOutput = recipe.getRecipeOutput();
            boolean areItemsEqual = recipeOutput.getItem().equals(output.getItem());
            boolean isMetaEqual = recipeOutput.getItemDamage() == output.getItemDamage();
            boolean isWildcard = output.getItemDamage() == OreDictionary.WILDCARD_VALUE;
            if (areItemsEqual && (isMetaEqual || isWildcard)) {
                toRemove.add(recipe.getRegistryName());
            }
        }

        for (ResourceLocation rl : toRemove) {
            registry.register(new DummyRecipe(rl.getNamespace(), rl.getPath()));
        }
    }

    /*
     * Original code from Terrafirmacraft's RecipeUtils.DummyRecipe (EUPL v1.2)
     * Modified to create DummyRecipe
     * Modified by xXjudeXx on 2026-04-07
     */
    private static class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
        private DummyRecipe(String domain, String id) {
            this.setRegistryName(domain, id);
        }

        public boolean matches(InventoryCrafting inventoryCrafting, World world) {
            return false;
        }

        public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
            return ItemStack.EMPTY;
        }

        public boolean canFit(int i, int i1) {
            return false;
        }

        public ItemStack getRecipeOutput() {
            return ItemStack.EMPTY;
        }
    }
}
