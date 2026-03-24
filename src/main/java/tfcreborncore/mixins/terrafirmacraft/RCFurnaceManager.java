package tfcreborncore.mixins.terrafirmacraft;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import cofh.core.util.helpers.ItemHelper;
import cofh.thermalexpansion.block.machine.TileFurnace;

@Mixin(TileFurnace.class)
public abstract class RCFurnaceManager {

    @Redirect(
              method = "processFinish",
              at = @At(
                       value = "INVOKE",
                       target = "Lcofh/core/util/helpers/ItemHelper;cloneStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;",
                       remap = false),
              remap = false)
    private ItemStack redirectCloneStack(ItemStack output) {
        TileFurnace self = (TileFurnace) (Object)this;

        // Always clone the output first
        ItemStack result = ItemHelper.cloneStack(output);

        // If already processed, do nothing
        if (result.hasTagCompound() && result.getTagCompound().getBoolean("TFC_PROCESSED")) {
            return result;
        }

        // Only apply TFC logic when creating a NEW output stack
        if (self.inventory[1].isEmpty()) {
            ItemStack input = self.inventory[0];
            ItemStack updated = CapabilityFood.updateFoodFromPrevious(input, result);

            updated.getTagCompound().setBoolean("TFC_PROCESSED", true);
            return updated;
        }

        return result;
    }
}
