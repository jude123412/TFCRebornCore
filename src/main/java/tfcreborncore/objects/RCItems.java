package tfcreborncore.objects;

import java.util.Set;

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
import com.google.common.collect.ImmutableSet;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemRCOre;
import tfcreborncore.objects.items.ItemRCToolHead;
import tfcreborncore.objects.tools.ItemRCTool;

public class RCItems {

    private static ImmutableList<Item> allOreItems;
    private static ImmutableList<Item> allToolHeadItems;
    private static ImmutableList<Item> allToolItems;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerOreItems(event);
        registerToolHeadItems(event);
        registerToolItems(event);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        registerOreItemColor(event);
        registerToolHeadItemColor(event);
        registerToolItemColor(event);
    }

    public static void registerItemModels(ModelRegistryEvent event) {
        registerModels(event);
    }

    private static void registerOreItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> oreItems = ImmutableList.builder();
        for (Ore ore : TFCRegistries.ORES) {
            if (ore.isGraded() && shouldGenerateOre(ore)) {
                String base = "ore/" + ore.getRegistryName().getPath().toLowerCase();

                for (ItemRCOre.ItemType type : ItemRCOre.ItemType.values()) {
                    Item oreType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCOre.ItemType.Create(ore, type),
                            CreativeTabsTFC.CT_ROCK_ITEMS);

                    ItemRCOre ItemType = (ItemRCOre) oreType;
                    String path = ItemType.getOre().getMetal().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(type.toString().toLowerCase() + toPascalCase(path), ItemType);
                    oreItems.add(oreType);
                }
            }
        }

        allOreItems = oreItems.build();
    }

    private static void registerToolHeadItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            if (metal.isToolMetal()) {
                String base = "metal/tool/head/" + metal.getRegistryName().getPath().toLowerCase();

                for (ItemRCToolHead.ItemType type : ItemRCToolHead.ItemType.values()) {
                    Item metalType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCToolHead.ItemType.Create(metal, type),
                            CreativeTabsTFC.CT_METAL);

                    ItemRCToolHead metalItemType = (ItemRCToolHead) metalType;
                    String path = metalItemType.getMetal().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(toPascalCaseAlt(type.toString().toLowerCase()) + toPascalCase(path),
                            metalItemType);
                    metalItems.add(metalType);
                }
            }
        }

        allToolHeadItems = metalItems.build();
    }

    private static void registerToolItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            if (metal.isToolMetal()) {
                for (ItemRCTool.ItemType type : ItemRCTool.ItemType.values()) {
                    String base = "metal/tool/" + type + "/" + metal.getRegistryName().getPath().toLowerCase();

                    Item metalType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCTool.ItemType.Create(metal, type),
                            CreativeTabsTFC.CT_METAL);

                    ItemRCTool metalItemType = (ItemRCTool) metalType;
                    String path = metalItemType.getMetal().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(toPascalCaseAlt(type.toString().toLowerCase()) + toPascalCase(path),
                            metalItemType);
                    metalItems.add(metalType);
                }
            }
        }

        allToolItems = metalItems.build();
    }

    private static void registerOreItemColor(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> oreItems = getAllOreItems();
        if (oreItems == null || oreItems.isEmpty()) return;

        for (Item item : oreItems) {
            if (!(item instanceof ItemRCOre oreItem)) continue;

            int color = getOreColor(oreItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    private static void registerToolHeadItemColor(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> metalItems = getAllToolHeadItems();
        if (metalItems == null || metalItems.isEmpty()) return;

        for (Item item : metalItems) {
            if (!(item instanceof ItemRCToolHead metalItem)) continue;

            int color = getToolHeadColor(metalItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    private static void registerToolItemColor(final ColorHandlerEvent.Item event) {
        ImmutableList<Item> metalItems = getAllToolItems();
        if (metalItems == null || metalItems.isEmpty()) return;

        for (Item item : metalItems) {
            if (!(item instanceof ItemRCTool metalItem)) continue;

            int color = getToolColor(metalItem);

            IItemColor handler = (stack, tintIndex) -> {
                if (tintIndex != 0) return 0xFFFFFF;
                return color;
            };

            event.getItemColors().registerItemColorHandler(handler, item);
        }
    }

    private static void registerModels(ModelRegistryEvent event) {
        for (Item item : getAllOreItems()) {
            ItemRCOre oreItem = (ItemRCOre) item;
            ModelLoader.setCustomModelResourceLocation(oreItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "ore/" + oreItem.getType().name().toLowerCase()), "inventory"));
        }

        for (Item item : getAllToolHeadItems()) {
            ItemRCToolHead metalItem = (ItemRCToolHead) item;
            ModelLoader.setCustomModelResourceLocation(metalItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "metal/tool/" + metalItem.getType().name().toLowerCase()),
                    "inventory"));
        }

        for (Item item : getAllToolItems()) {
            ItemRCTool metalItem = (ItemRCTool) item;
            ModelLoader.setCustomModelResourceLocation(metalItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "metal/tool/" + metalItem.getType().name().toLowerCase()),
                    "inventory"));
        }
    }

    public static ImmutableList<Item> getAllOreItems() {
        return allOreItems;
    }

    public static ImmutableList<Item> getAllToolHeadItems() {
        return allToolHeadItems;
    }

    public static ImmutableList<Item> getAllToolItems() {
        return allToolItems;
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

    private static int getOreColor(ItemRCOre oreItem) {
        Ore ore = oreItem.getOre();
        return ore.getMetal().getColor() & 0xFFFFFF;
    }

    private static int getToolHeadColor(ItemRCToolHead metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    private static int getToolColor(ItemRCTool metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    private static final Set<String> BLOCKED_ORES = ImmutableSet.of(
            "tetrahedrite", "malachite", "magnetite", "limonite");

    public static boolean shouldGenerateOre(Ore ore) {
        String name = ore.getRegistryName().getPath().toLowerCase();

        // Blocked always wins
        for (String s : BLOCKED_ORES)
            if (name.contains(s))
                return false;

        return true;
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(Tags.MODID, name);
        item.setTranslationKey(Tags.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
