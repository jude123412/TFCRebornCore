package tfcreborncore.proxy;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import com.google.common.collect.ImmutableList;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemOreProcessing;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class Proxy {

    public void preLoad() {}

    private static ImmutableList<Item> allOreItems;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> oreItems = ImmutableList.builder();
        for (Ore ore : TFCRegistries.ORES) {
            if (ore.isGraded()) {
                String base = "ore/" + ore.getRegistryName().getPath().toLowerCase();
                Item tiny = register(registry, base + "_tiny_pile",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.TINY_PILE),
                        CreativeTabsTFC.CT_ROCK_ITEMS);
                Item cube = register(registry, base + "_cube",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.CUBE),
                        CreativeTabsTFC.CT_ROCK_ITEMS);
                Item bar = register(registry, base + "_bar",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.BAR),
                        CreativeTabsTFC.CT_ROCK_ITEMS);

                oreItems.add(tiny);
                oreItems.add(cube);
                oreItems.add(bar);
            }
        }

        allOreItems = oreItems.build();
    }

    public static ImmutableList<Item> getAllOreItems() {
        return allOreItems;
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(Tags.MODID, name);
        item.setTranslationKey(Tags.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
