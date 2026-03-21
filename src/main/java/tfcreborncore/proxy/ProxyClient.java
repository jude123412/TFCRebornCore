package tfcreborncore.proxy;

import net.dries007.tfc.api.types.Ore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.google.common.collect.ImmutableList;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemOreProcessing;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ProxyClient extends Proxy {

    public void preLoad() {
        super.preLoad();
    }

    @SubscribeEvent
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> oreItems = Proxy.getAllOreItems();
        if (oreItems == null || oreItems.isEmpty()) return;

        for (Item item : oreItems) {
            if (!(item instanceof ItemOreProcessing oreItem)) continue;

            int color = getColor(oreItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    private static int getColor(ItemOreProcessing oreItem) {
        Ore ore = oreItem.getOre();
        int color;
        if (ore.getRegistryName().getPath().contains("malachite")) color = 0x53ff92;
        else if (ore.getRegistryName().getPath().contains("tetrahedrite")) color = 0x848484;
        else if (ore.getRegistryName().getPath().contains("hematite")) color = 0x994F51;
        else if (ore.getRegistryName().getPath().contains("limonite")) color = 0x4D2F27;
        else if (ore.getRegistryName().getPath().contains("garnierite")) color = 0x5A664B;
        else if (ore.getRegistryName().getPath().contains("stibnite")) color = 0x5C607A;
        else if (ore.getRegistryName().getPath().contains("spodumene")) color = 0x5F447D;
        else if (ore.getRegistryName().getPath().contains("bauxite")) color = 0xD7652F;
        else if (ore.getRegistryName().getPath().contains("rutile")) color = 0x50301F;
        else {
            color = ore.getMetal().getColor() & 0xFFFFFF;
        }
        return color;
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        for (Item item : Proxy.getAllOreItems()) {
            ItemOreProcessing oreItem = (ItemOreProcessing) item;
            ModelLoader.setCustomModelResourceLocation(oreItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "ore/" + oreItem.getType().name().toLowerCase()), "inventory"));
        }
    }
}
