package tfcreborncore.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.RCItems;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class Proxy {

    public void preLoad() {}

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RCItems.registerItems(event);
    }
}
