package tfcreborncore.proxy;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
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
                Item tiny = register(registry, base + "_pile",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.PILE),
                        CreativeTabsTFC.CT_ROCK_ITEMS);
                Item cube = register(registry, base + "_cube",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.CUBE),
                        CreativeTabsTFC.CT_ROCK_ITEMS);
                Item bar = register(registry, base + "_bar",
                        ItemOreProcessing.OreItemType.Create(ore, ItemOreProcessing.OreItemType.BAR),
                        CreativeTabsTFC.CT_ROCK_ITEMS);

                ItemOreProcessing oreTiny = (ItemOreProcessing) tiny;
                ItemOreProcessing oreCube = (ItemOreProcessing) cube;
                ItemOreProcessing oreBar = (ItemOreProcessing) bar;

                String pathPile = oreTiny.getOre().getRegistryName().getPath().toLowerCase();
                String pathCube = oreCube.getOre().getRegistryName().getPath().toLowerCase();
                String pathBar = oreBar.getOre().getRegistryName().getPath().toLowerCase();

                OreDictionary.registerOre("pile" + toPascalCase(pathPile), oreTiny);
                OreDictionary.registerOre("cube" + toPascalCase(pathCube), oreCube);
                OreDictionary.registerOre("bar" + toPascalCase(pathBar), oreBar);

                oreItems.add(tiny);
                oreItems.add(cube);
                oreItems.add(bar);
            }
        }

        allOreItems = oreItems.build();
    }

    public static String toPascalCase(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true; // capitalize first char and any char after '_'

        for (char c : input.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                sb.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }

        return sb.toString();
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
