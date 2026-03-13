package tfcreborncore.mixins.terrafirmacraft;

import gregtech.common.blocks.BlockOre;
import net.dries007.tfc.objects.items.metal.ItemProspectorPick;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static gregtech.common.blocks.MetaBlocks.ORES;

@Mixin(ItemProspectorPick.class)
public class RCItemProspectorPick {


    @Inject(
            method = "getOreStack",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private void onGetOreStack(World world, BlockPos pos, IBlockState state, boolean ignoreGrade, CallbackInfoReturnable<ItemStack> cir) {
        Block block = state.getBlock();
        if (block instanceof BlockOre) {
            for (BlockOre oreBlocks : ORES) {
                if (state.getBlock() == oreBlocks) {
                    if (!ignoreGrade) {
                        cir.setReturnValue(new ItemStack(oreBlocks));
                        return;
                    }

                    cir.setReturnValue(new ItemStack(oreBlocks));
                    return;
                }
            }
        }

        cir.setReturnValue(ItemStack.EMPTY);
    }
}
