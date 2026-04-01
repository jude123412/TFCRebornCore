package tfcreborncore.objects.tooltips;

import java.text.DecimalFormat;
import java.util.List;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.fuel.FuelManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import tfcreborncore.api.capability.heat.ExtendedHeat;

public class ItemToolTips {

    public static void registerMetalInfoTooltips(ItemTooltipEvent event) {
        // Metal Info ToolTip
        ItemStack stack = event.getItemStack();
        List<String> tooltip = event.getToolTip();
        ITooltipFlag flags = event.getFlags();

        IMetalItem metalCapability = CapabilityMetalItem.getMetalItem(stack);
        if (metalCapability != null) {
            Metal metal = metalCapability.getMetal(stack);
            if (metal != null) {
                int melttemp = (int) metal.getMeltTemp();

                if (!flags.isAdvanced()) {
                    tooltip.add("");
                    tooltip.add(I18n.format("tfcreborncore.tooltip.melting_temp", ExtendedHeat.getTooltip(melttemp)));
                    tooltip.add(I18n.format("tfcreborncore.tooltip.metal_tier",
                            I18n.format(Helpers.getEnumName(metal.getTier()))));
                    tooltip.add("");
                }
            }
        }
    }

    public static void registerFuelTimeTooltips(ItemTooltipEvent event) {
        // Fuel burn time tooltip
        ItemStack stack = event.getItemStack();

        if (FuelManager.isItemFuel(stack)) {
            List<String> tooltip = event.getToolTip();
            int time = FuelManager.getFuel(stack).getAmount();
            float seconds = (float) time / 20;
            float minutes = seconds / 60;
            float hours = (float) time / 1000;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            float temp = FuelManager.getFuel(stack).getTemperature();

            tooltip.add("");
            tooltip.add(I18n.format("tfcreborncore.tooltip.burn_temp", ExtendedHeat.getTooltip(temp)));
            switch (ConfigTFC.Client.TOOLTIP.timeTooltipMode) {
                case NONE -> tooltip.add(I18n.format("tfcreborncore.tooltip.burn_time.none", time));
                case TICKS -> tooltip.add(I18n.format("tfcreborncore.tooltip.burn_time.ticks", (float) time));
                case MINECRAFT_HOURS -> tooltip.add(
                        I18n.format("tfcreborncore.tooltip.burn_time.minecraft_hours", decimalFormat.format(minutes)));
                case REAL_MINUTES -> tooltip
                        .add(I18n.format("tfcreborncore.tooltip.burn_time.real_minutes", decimalFormat.format(hours)));
            }
            tooltip.add("");
        }
    }
}
