package tfcreborncore.objects;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import tfcreborncore.TFCRebornCore;

/*
 * Original code from Terrafirmacraft's CreativeTabTFC (EUPL v1.2)
 * Modified to create CreativeTabsRC
 * Created by xXjudeXx on 2026-03-25
 */
public class CreativeTabsRC {

    public static final CreativeTabs CT_ITEMS = new CreativeTabsRC.CreativeTabRC("items", "tfc:metal/ingot/red_steel");

    private static class CreativeTabRC extends CreativeTabs {

        private final ResourceLocation iconResourceLocation;

        private CreativeTabRC(String label, String icon) {
            super("tfcreborncore." + label);
            this.iconResourceLocation = new ResourceLocation(icon);
        }

        @SideOnly(Side.CLIENT)
        @Nonnull
        public ItemStack createIcon() {
            ItemStack stack = new ItemStack((Item) ForgeRegistries.ITEMS.getValue(this.iconResourceLocation));
            if (!stack.isEmpty()) {
                CapabilityFood.setStackNonDecaying(stack);
                return stack;
            } else {
                TFCRebornCore.LOGGER.error("[Please inform developers] No icon stack for creative tab {}",
                        this.getTabLabel());
                return new ItemStack(Items.STICK);
            }
        }
    }
}
