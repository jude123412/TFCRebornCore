package tfcreborncore.proxy;

import java.text.DecimalFormat;
import java.util.List;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.fuel.FuelManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import tfcreborncore.api.capability.heat.ExtendedHeat;
import tfcreborncore.objects.RCItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ProxyClient extends Proxy {

    public void preLoad() {
        super.preLoad();
    }

    @SubscribeEvent
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event) {
        RCItems.registerItemColors(event);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        RCItems.registerItemModels(event);
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();

        // Melting temp tooltip
        if (stack.getItem() instanceof IMetalItem metalItem) {
            List<String> tooltip = event.getToolTip();
            ITooltipFlag flags = event.getFlags();

            // Always show metal info :D
            Metal metal = metalItem.getMetal(stack);
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

        // Fuel burn time tooltip
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
