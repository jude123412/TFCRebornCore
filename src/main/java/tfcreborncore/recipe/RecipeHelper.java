package tfcreborncore.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class RecipeHelper {

    // Returns an ItemStack with amount or ItemStack.EMPTY if item doesn't exist
    public static ItemStack getItemStack(String modId, String itemId, int metaId, int amount) {
        ResourceLocation location = new ResourceLocation(modId, itemId);
        Item item = ForgeRegistries.ITEMS.getValue(location);
        if (item == null || item == Items.AIR) return ItemStack.EMPTY;
        return new ItemStack(item, amount, metaId);
    }

    // Returns an ItemStack or ItemStack.EMPTY if item doesn't exist
    public static ItemStack getItemStack(String modId, String itemId, int metaId) {
        ResourceLocation location = new ResourceLocation(modId, itemId);
        Item item = ForgeRegistries.ITEMS.getValue(location);
        if (item == null || item == Items.AIR) return ItemStack.EMPTY;
        return new ItemStack(item, 1, metaId);
    }

    // Returns an ItemStack without meta value
    public static ItemStack getItemStack(String modId, String itemId) {
        return getItemStack(modId, itemId, 0);
    }

    // Returns a Fluid
    public static Fluid getFluid(String fluidName) {
        return FluidRegistry.getFluid(fluidName);
    }

    // Returns a FluidStack
    public static FluidStack getFluidStack(String fluidName, int amount) {
        return FluidRegistry.getFluidStack(fluidName, amount);
    }

    /*
     * Removes crafting recipes by output
     * Has Wildcard capability so it can remove
     * all items with meta values
     */
    public static void removeRecipeByOutput(RegistryEvent.Register<IRecipe> registry, ItemStack output) {
        IForgeRegistryModifiable<IRecipe> r = (IForgeRegistryModifiable) registry.getRegistry();
        List<ResourceLocation> toRemove = new ArrayList<>();
        for (IRecipe recipe : r.getValuesCollection()) {
            ItemStack recipeOutput = recipe.getRecipeOutput();
            boolean areItemsEqual = recipeOutput.getItem().equals(output.getItem());
            boolean isMetaEqual = recipeOutput.getItemDamage() == output.getItemDamage();
            boolean isWildcard = output.getItemDamage() == OreDictionary.WILDCARD_VALUE;
            if (areItemsEqual && (isMetaEqual || isWildcard)) {
                toRemove.add(recipe.getRegistryName());
            }
        }
        for (ResourceLocation rl : toRemove) {
            r.register(new DummyRecipe(rl.getNamespace(), rl.getPath()));
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
