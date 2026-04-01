package tfcreborncore.proxy;

import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import tfcreborncore.objects.RCItems;
import tfcreborncore.objects.tooltips.ItemToolTips;

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
        ItemToolTips.registerMetalInfoTooltips(event);
        ItemToolTips.registerFuelTimeTooltips(event);
    }
}
