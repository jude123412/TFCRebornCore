package tfcreborncore.objects;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import com.google.common.collect.ImmutableList;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemOreProcessing;

public class RCItems {

    private static ImmutableList<Item> allOreItems;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerOreItems(event);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        registerOreItemColor(event);
    }

    public static void registerItemModels(ModelRegistryEvent event) {
        registerOreModels(event);
    }

    static void registerOreItems(RegistryEvent.Register<Item> event) {
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

    static void registerOreItemColor(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> oreItems = getAllOreItems();
        if (oreItems == null || oreItems.isEmpty()) return;

        for (Item item : oreItems) {
            if (!(item instanceof ItemOreProcessing oreItem)) continue;

            int color = getOreColor(oreItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    static void registerOreModels(ModelRegistryEvent event) {
        for (Item item : getAllOreItems()) {
            ItemOreProcessing oreItem = (ItemOreProcessing) item;
            ModelLoader.setCustomModelResourceLocation(oreItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "ore/" + oreItem.getType().name().toLowerCase()), "inventory"));
        }
    }

    public static ImmutableList<Item> getAllOreItems() {
        return allOreItems;
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

    private static int getOreColor(ItemOreProcessing oreItem) {
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

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(Tags.MODID, name);
        item.setTranslationKey(Tags.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
