package tfcreborncore.mixins.terrafirmacraft;

import net.dries007.tfc.objects.te.TEToolRack;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TEToolRack.class)
public class RCTEToolRackMixin {

    @Inject(
            method = "isItemEligible",
            at = @At(
                     value = "INVOKE",
                     target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            cancellable = true)
    private static void tfc$injectBeforeReturn(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack == null || stack.isEmpty()) return;

        // Custom ore dictionary logic
        if (OreDictionaryHelper.doesStackMatchOre(stack, "toolRackCapable")) {
            cir.setReturnValue(true);
        }
    }
}
