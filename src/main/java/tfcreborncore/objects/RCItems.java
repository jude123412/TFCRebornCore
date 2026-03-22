package tfcreborncore.objects;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
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
import tfcreborncore.objects.items.ItemRCMetal;

public class RCItems {

    private static ImmutableList<Item> allOreItems;
    private static ImmutableList<Item> allMetalItems;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerOreItems(event);
        registerMetalItems(event);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        registerOreItemColor(event);
        registerMetalItemColor(event);
    }

    public static void registerItemModels(ModelRegistryEvent event) {
        registerModels(event);
    }

    static void registerOreItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> oreItems = ImmutableList.builder();
        for (Ore ore : TFCRegistries.ORES) {
            if (ore.isGraded()) {
                String base = "ore/" + ore.getRegistryName().getPath().toLowerCase();

                for (ItemOreProcessing.ItemType type : ItemOreProcessing.ItemType.values()) {
                    Item oreType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemOreProcessing.ItemType.Create(ore, type),
                            CreativeTabsTFC.CT_ROCK_ITEMS);

                    ItemOreProcessing ItemType = (ItemOreProcessing) oreType;
                    String path = ItemType.getOre().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(type.toString().toLowerCase() + toPascalCase(path), ItemType);
                    oreItems.add(oreType);
                }
            }
        }

        allOreItems = oreItems.build();
    }

    static void registerMetalItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            if (metal.isToolMetal()) {
                String base = "metal/" + metal.getRegistryName().getPath().toLowerCase();

                for (ItemRCMetal.ItemType type : ItemRCMetal.ItemType.values()) {
                    Item metalType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCMetal.ItemType.Create(metal, type),
                            CreativeTabsTFC.CT_METAL);

                    ItemRCMetal metalItemType = (ItemRCMetal) metalType;
                    String path = metalItemType.getMetal().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(toPascalCaseAlt(type.toString().toLowerCase()) + toPascalCase(path),
                            metalItemType);
                    metalItems.add(metalType);
                }
            }
        }

        allMetalItems = metalItems.build();
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

    static void registerMetalItemColor(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> metalItems = getAllMetalItems();
        if (metalItems == null || metalItems.isEmpty()) return;

        for (Item item : metalItems) {
            if (!(item instanceof ItemRCMetal metalItem)) continue;

            int color = getMetalColor(metalItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    static void registerModels(ModelRegistryEvent event) {
        for (Item item : getAllOreItems()) {
            ItemOreProcessing oreItem = (ItemOreProcessing) item;
            ModelLoader.setCustomModelResourceLocation(oreItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "ore/" + oreItem.getType().name().toLowerCase()), "inventory"));
        }
        for (Item item : getAllMetalItems()) {
            ItemRCMetal metalItem = (ItemRCMetal) item;
            ModelLoader.setCustomModelResourceLocation(metalItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "metal/" + metalItem.getType().name().toLowerCase()),
                    "inventory"));
        }
    }

    public static ImmutableList<Item> getAllOreItems() {
        return allOreItems;
    }

    public static ImmutableList<Item> getAllMetalItems() {
        return allMetalItems;
    }

    public static String toPascalCase(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

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

    public static String toPascalCaseAlt(String input) {
        if (input == null || input.isEmpty()) return input;

        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                sb.append(capitalizeNext ? Character.toUpperCase(c) : c);
                capitalizeNext = false;
            }
        }

        if (sb.length() == 0) return sb.toString();
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
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

    private static int getMetalColor(ItemRCMetal metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(Tags.MODID, name);
        item.setTranslationKey(Tags.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
