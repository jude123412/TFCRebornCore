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
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.ItemRCOre;

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

    static void registerMetalItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ImmutableList.Builder<Item> metalItems = ImmutableList.builder();
        for (Metal metal : TFCRegistries.METALS) {
            if (metal.isToolMetal() && shouldGenerateTool(metal)) {
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
            if (!(item instanceof ItemRCOre oreItem)) continue;

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

    private static int getOreColor(ItemRCOre oreItem) {
        Ore ore = oreItem.getOre();
        return ore.getMetal().getColor() & 0xFFFFFF;
    }

    private static int getMetalColor(ItemRCMetal metalItem) {
        return metalItem.getMetal().getColor() & 0xFFFFFF;
    }

    private static final Set<String> ALLOWED_TOOLS = ImmutableSet.of(
            "gold", "iron", "constantan", "bronze", "invar",
            "electrum", "steel", "platinum", "nickel",
            "aluminium", "lead", "silver", "tin", "copper",
            "fluxed_electrum", "nickel_silver", "black_steel", "blue_steel");

    private static final Set<String> BLOCKED_TOOLS = ImmutableSet.of(
            "red_steel", "hsla_steel", "tungsten_steel", "beryllium_copper");

    private static final Set<String> BLOCKED_ORES = ImmutableSet.of(
            "tetrahedrite", "malachite", "magnetite", "limonite");

    public static boolean shouldGenerateTool(Metal metal) {
        String name = metal.getRegistryName().getPath().toLowerCase();

        // Blocked always wins
        for (String s : BLOCKED_TOOLS)
            if (name.contains(s))
                return false;

        // Allowed metals
        for (String s : ALLOWED_TOOLS)
            if (name.contains(s))
                return true;

        return false;
    }

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
