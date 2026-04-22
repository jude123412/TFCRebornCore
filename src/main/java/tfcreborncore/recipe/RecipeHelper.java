package tfcreborncore.recipe;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class RecipeHelper {

    public static int S = 20;

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

    // Returns an ItemStack from an Item
    public static ItemStack getItemStack(Item item) {
        return new ItemStack(item);
    }

    // Returns an IIngredient from and Item
    public static IIngredient<ItemStack> getIIngredient(Item item) {
        return IIngredient.of(item);
    }

    // Returns an IIngredient from and Item
    public static IIngredient<ItemStack> getIIngredient(ItemStack item) {
        return IIngredient.of(item);
    }

    // Returns an IIngredient from and Ore Dictionary
    public static IIngredient<ItemStack> getIIngredient(String ore) {
        return IIngredient.of(ore);
    }

    // Returns an IIngredient from ModID and ItemID
    public static IIngredient<ItemStack> getIIngredient(String modId, String itemId) {
        return IIngredient.of(getItemStack(modId, itemId));
    }

    // Returns an IIngredient from ModID, ItemID and Meta
    public static IIngredient<ItemStack> getIIngredient(String modId, String itemId, int meta) {
        return IIngredient.of(getItemStack(modId, itemId, meta));
    }

    // Returns an IIngredient from ModID, ItemID
    // and Meta while allowing Stack Size
    public static IIngredient<ItemStack> getIIngredient(String modId, String itemId, int meta, int amount) {
        return IIngredient.of(getItemStack(modId, itemId, meta, amount));
    }

    // Returns an IIngredient from Fluid Name and Amount
    public static IIngredient<FluidStack> getIIngredient(String name, int amount) {
        return IIngredient.of(getFluidStack(name, amount));
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
