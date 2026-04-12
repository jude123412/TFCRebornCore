package tfcreborncore.recipe.manager.builders;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

import org.jetbrains.annotations.NotNull;

/*
 * Original code from Terrafirmacraft's ShapelessDamageRecipe (EUPL v1.2)
 * Modified to create a publicly accessible recipe class
 * Modified by xXjudeXx on 2026-04-12
 */
public class ShapedDamageRecipe extends ShapedOreRecipe {

    private final int damage;

    public ShapedDamageRecipe(ResourceLocation name, int damage, @Nonnull ItemStack result,
                              CraftingHelper.ShapedPrimer primer) {
        super(name, result, primer);
        this.damage = damage;
    }

    public NonNullList<ItemStack> getRemainingItemsDamaged(InventoryCrafting inv) {
        NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < remainingItems.size(); ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty() && itemstack.getItem().isDamageable()) {
                remainingItems.set(i, this.damageStack(itemstack));
            } else {
                remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
            }
        }

        return remainingItems;
    }

    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(@NotNull InventoryCrafting inventoryCrafting) {
        return this.getRemainingItemsDamaged(inventoryCrafting);
    }

    private ItemStack damageStack(ItemStack stack) {
        ItemStack damagedStack = stack.copy();
        EntityPlayer player = ForgeHooks.getCraftingPlayer();
        if (player != null) {
            damagedStack.damageItem(this.damage, player);
        }

        return damagedStack;
    }
}
