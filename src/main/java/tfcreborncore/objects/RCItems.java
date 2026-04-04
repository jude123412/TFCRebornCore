package tfcreborncore.objects;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import com.google.common.collect.ImmutableList;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemRC;
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.ItemRCOre;
import tfcreborncore.objects.items.ItemRCTool;
import tfcreborncore.objects.items.enums.ItemRCOreEnum;
import tfcreborncore.objects.items.enums.ItemRCToolType;
import tfcreborncore.objects.items.enums.ItemRCType;

public class RCItems {

    private static ImmutableList<Item> allOreItems;
    private static ImmutableList<Item> allMetalItems;
    private static ImmutableList<Item> allToolItems;
    private static ImmutableList<Item> allRegularItems;

    public static void registerItems(RegistryEvent.Register<Item> event) {
        registerOreItems(event);
        registerMetalItems(event);
        registerToolItems(event);
        registerRegularItems(event);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        registerOreItemColor(event);
        registerMetalItemColor(event);
        registerToolItemColor(event);
    }

    public static void registerItemModels(ModelRegistryEvent event) {
        registerModels(event);
    }

    private static void registerOreItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> oreItems = ImmutableList.builder();
        for (Ore ore : TFCRegistries.ORES) {
            if (ore.isGraded()) {
                String base = "ore/" + ore.getRegistryName().getPath().toLowerCase();

                for (ItemRCOreEnum type : ItemRCOreEnum.values()) {
                    Item oreType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCOreEnum.Create(ore, type),
                            CreativeTabsRC.CT_ITEMS);

                    ItemRCOre ItemType = (ItemRCOre) oreType;
                    String path = ItemType.getOre().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(type.toString().toLowerCase() + toPascalCase(path), ItemType);
                    oreItems.add(oreType);
                }
            }
        }

        allOreItems = oreItems.build();
    }

    private static void registerMetalItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            String metalName = metal.getRegistryName().getPath().toLowerCase();
            String base = metal.isToolMetal() ? "metal/tool/head/" + metalName : "metal/" + metalName;

            for (ItemRCMetal.ItemType type : ItemRCMetal.ItemType.values()) {

                String name = base + "_" + type.toString().toLowerCase();

                boolean shouldRegister = (ItemRCMetal.isToolHead(type) && metal.isToolMetal()) ||
                        (!ItemRCMetal.isToolHead(type) && metal.isUsable());

                if (shouldRegister) {
                    Item metalType = register(registry, name,
                            ItemRCMetal.ItemType.Create(metal, type),
                            CreativeTabsRC.CT_ITEMS);

                    ItemRCMetal metalItemType = (ItemRCMetal) metalType;
                    String path = metalItemType.getMetal().getRegistryName().getPath().toLowerCase();

                    OreDictionary.registerOre(
                            toPascalCaseAlt(type.toString().toLowerCase()) + toPascalCase(path),
                            metalItemType);

                    metalItems.add(metalType);
                }
            }
        }

        allMetalItems = metalItems.build();
    }

    private static void registerToolItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            if (metal.isToolMetal()) {
                for (ItemRCToolType type : ItemRCToolType.values()) {
                    String base = "metal/tool/" + type + "/" + metal.getRegistryName().getPath().toLowerCase();

                    Item metalType = register(registry, base + "_" + type.toString().toLowerCase(),
                            ItemRCToolType.Create(metal, type),
                            CreativeTabsRC.CT_ITEMS);

                    ItemRCTool metalItemType = (ItemRCTool) metalType;
                    String path = metalItemType.getMetal().getRegistryName().getPath().toLowerCase();
                    OreDictionary.registerOre(toPascalCaseAlt(type.toString().toLowerCase()),
                            new ItemStack(metalItemType, 1, OreDictionary.WILDCARD_VALUE));
                    OreDictionary.registerOre(toPascalCaseAlt(type.toString().toLowerCase()) + toPascalCase(path),
                            metalItemType);
                    metalItems.add(metalType);
                }
            }
        }

        allToolItems = metalItems.build();
    }

    public static void registerRegularItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> regularItems = ImmutableList.builder();
        for (ItemRCType type : ItemRCType.values()) {
            String base = "item/" + type.toString().toLowerCase();

            Item regularItemType = register(registry, base, ItemRCType.Create(type), CreativeTabsRC.CT_ITEMS);
            if (type.getDictionary() != null) OreDictionary.registerOre(type.getDictionary(), regularItemType);
            regularItems.add(regularItemType);
        }

        allRegularItems = regularItems.build();
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

    private static void registerMetalItemColor(final ColorHandlerEvent.Item event) {
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

        for (Item item : getAllMetalItems()) {
            ItemRCMetal metalItem = (ItemRCMetal) item;
            ModelLoader.setCustomModelResourceLocation(metalItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "metal/" + metalItem.getType().name().toLowerCase()),
                    "inventory"));
        }

        for (Item item : getAllToolItems()) {
            ItemRCTool metalItem = (ItemRCTool) item;
            ModelLoader.setCustomModelResourceLocation(metalItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "metal/tool/" + metalItem.getType().name().toLowerCase()),
                    "inventory"));
        }

        for (Item item : getAllRegularItems()) {
            ItemRC regularItem = (ItemRC) item;
            ModelLoader.setCustomModelResourceLocation(regularItem, 0, new ModelResourceLocation(
                    new ResourceLocation(Tags.MODID, "regular/" + regularItem.getType().name().toLowerCase()),
                    "inventory"));
        }
    }

    public static ImmutableList<Item> getAllOreItems() {
        return allOreItems;
    }

    public static ImmutableList<Item> getAllMetalItems() {
        return allMetalItems;
    }

    public static ImmutableList<Item> getAllToolItems() {
        return allToolItems;
    }

    public static ImmutableList<Item> getAllRegularItems() {
        return allRegularItems;
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
        int color;
        if (ore.getRegistryName().getPath().contains("garnierite")) color = 0x5A664B;
        else if (ore.getRegistryName().getPath().contains("stibnite")) color = 0xA0A7D3;
        else if (ore.getRegistryName().getPath().contains("spodumene")) color = 0xECD3FF;
        else if (ore.getRegistryName().getPath().contains("bauxite")) color = 0xD7652F;
        else if (ore.getRegistryName().getPath().contains("rutile")) color = 0x915638;
        else if (ore.getRegistryName().getPath().contains("malachite")) color = 0x78C9AF;
        else if (ore.getRegistryName().getPath().contains("tetrahedrite")) color = 0x938CA5;
        else if (ore.getRegistryName().getPath().contains("magnetite")) color = 0x7F8E8A;
        else if (ore.getRegistryName().getPath().contains("hematite")) color = 0xC96266;
        else if (ore.getRegistryName().getPath().contains("limonite")) color = 0x4D2F27;
        else {
            color = ore.getMetal().getColor() & 0xFFFFFF;
        }
        return color;
    }

    private static int getMetalColor(ItemRCMetal metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    private static int getToolColor(ItemRCTool metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    protected static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(Tags.MODID, name);
        item.setTranslationKey(Tags.MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
