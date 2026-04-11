package tfcreborncore.recipe;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
}
