package tfcreborncore.objects.items.enums;

import java.util.function.Function;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import tfcreborncore.objects.items.ItemRC;
import tfcreborncore.types.DefaultMetals;

public enum ItemRCType {

    // Metal Press Components (non-stackable)
    METAL_PRESS_SLEEVE(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_RACKWHEEL_PIECE(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_RACKWHEEL(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_LONG_ROD(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_BOLT(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_SCREW(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_NUGGET(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_DOUBLE_SHEET(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_STRIP(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_DOUBLE_INGOT(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_SHEET(Size.NORMAL, Weight.MEDIUM, false),

    // Non-Metal Material Items
    WOOD_SHEET(Size.LARGE, Weight.LIGHT, "sheetWood", true),
    LATEX_COATED_WOOD_SHEET(Size.LARGE, Weight.LIGHT, "sheetLatexWood", true),
    BEESWAX_SHEET(Size.LARGE, Weight.LIGHT, "sheetBeeswax", true),

    // Circuit Board components
    BASIC_CIRCUIT_BOARD(Size.SMALL, Weight.HEAVY, true),
    GOOD_CIRCUIT_BOARD(Size.SMALL, Weight.HEAVY, true),

    // Electrical components
    ELECTRON_TUBE_HOUSING(Size.SMALL, Weight.LIGHT, true),
    REDSTONE_ELECTRON_TUBE(Size.VERY_SMALL, Weight.MEDIUM, true),
    RF_CONTROL_CIRCUIT(Size.LARGE, Weight.HEAVY, true),

    // Fuels
    LIGNITE_COKE(Size.SMALL, Weight.MEDIUM, "gemLigniteCoke", true),
    BITUMINOUS_COAL_COKE(Size.SMALL, Weight.MEDIUM, "gemBituminousCokeCoke", true),

    // Dust
    HARDENED_GLASS_MIX(Size.SMALL, Weight.VERY_LIGHT, "dustHardenedGlass", true),
    COAL_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustCoal", true),
    SYNTHETIC_GRAPHITE_MIX(Size.SMALL, Weight.VERY_LIGHT, "dustSyntheticGraphite", true),
    WOOD_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustWood", true),
    CERTUS_QUARTZ_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustCertusQuartz", true),
    ENDERPEARL_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustEnder", true),
    OBSIDIAN_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustObsidian", true),
    SNOW_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustSnow", true),
    APATITE_POWDER(Size.SMALL, Weight.VERY_LIGHT, "dustApatite", true),

    // Metal
    REDSTONE_RESISTOR_PART_A(Size.SMALL, Weight.LIGHT, DefaultMetals.REDSTONE, 10, true, true),
    REDSTONE_RESISTOR_PART_B(Size.SMALL, Weight.LIGHT, DefaultMetals.REDSTONE, 10, true, true),
    REDSTONE_RESISTOR_PART_C(Size.SMALL, Weight.LIGHT, DefaultMetals.REDSTONE, 10, true, true),
    REDSTONE_RESISTOR(Size.VERY_SMALL, Weight.MEDIUM, DefaultMetals.REDSTONE, 30, true, true),
    BRASS_PISTON(Size.VERY_LARGE, Weight.HEAVY, DefaultMetals.BRASS, 750, true, true),
    RADIATOR_PIPING(Size.SMALL, Weight.MEDIUM, DefaultMetals.WROUGHT_IRON, 100, true, true),
    RADIATOR_MATRIX(Size.NORMAL, Weight.MEDIUM, DefaultMetals.WROUGHT_IRON, 300, true, true),

    // Wood Forms
    INGOT_FORM(Size.SMALL, Weight.MEDIUM, "formIngot", false, true),
    PELLET_FORM(Size.SMALL, Weight.MEDIUM, "formPellet", false, true),
    SHEET_FORM(Size.SMALL, Weight.MEDIUM, "formSheet", false, true),

    // Misc
    UNFIRED_CLAY_SHEET(Size.NORMAL, Weight.LIGHT, true),
    CLAY_SHEET(Size.NORMAL, Weight.LIGHT, true),
    NICKEL_PLATED_CAPACITOR_CELL(Size.LARGE, Weight.LIGHT, true),
    CERAMIC_CAPACITOR_BLOCK(Size.LARGE, Weight.MEDIUM, true),
    BASIC_CAPACITOR_CELL(Size.LARGE, Weight.MEDIUM, true),
    ADVANCED_CAPACITOR_CELL(Size.LARGE, Weight.HEAVY, true),
    HI_TECH_CAPACITOR_CELL(Size.LARGE, Weight.VERY_HEAVY, true),
    UNFIRED_CERAMIC_INSULATOR(Size.NORMAL, Weight.LIGHT, true),
    CERAMIC_INSULATOR(Size.NORMAL, Weight.LIGHT, true),
    GLASS_INSULATOR(Size.NORMAL, Weight.LIGHT, true),
    ASH_BRICK(Size.SMALL, Weight.LIGHT, true),
    WINDUP_TRINKET(Size.NORMAL, Weight.MEDIUM, false),
    HONEYCOMB_FRAME(Size.LARGE, Weight.MEDIUM, true),
    SLAG(Size.VERY_SMALL, Weight.VERY_LIGHT, "crystalSlag", true),
    RICH_SLAG(Size.VERY_SMALL, Weight.VERY_LIGHT, "crystalSlagRich", true);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final Size size;
    private final Weight weight;
    private final String dictionary;
    private final Metal metal;
    private final int meltingAmount;
    private final boolean canMelt;
    private final boolean canStack;
    private final boolean hasContainerItem;

    private final Function<ItemRCType, ItemRC> factory;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCType(Size size, Weight weight, boolean canStack) {
        this(size, weight, null, null, 0, false, canStack, false, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, ResourceLocation metal, int meltingAmount, boolean canMelt, boolean canStack) {
        this(size, weight, null, metal, meltingAmount, canMelt, canStack, false, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, boolean canStack, boolean hasContainerItem) {
        this(size, weight, null, null, 0, false, canStack, hasContainerItem, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, String ore, boolean canStack) {
        this(size, weight, ore, null, 0, false, canStack, false, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, String ore, boolean canStack, boolean hasContainerItem) {
        this(size, weight, ore, null, 0, false, canStack, hasContainerItem, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, String ore, ResourceLocation metal, int meltingAmount, boolean canMelt,
               boolean canStack, boolean hasContainerItem,
               Function<ItemRCType, ItemRC> factory) {
        this.size = size;
        this.weight = weight;
        this.dictionary = ore;
        this.metal = TFCRegistries.METALS.getValue(metal);
        this.meltingAmount = meltingAmount;
        this.canMelt = canMelt;
        this.canStack = canStack;
        this.hasContainerItem = hasContainerItem;
        this.factory = factory;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public Size getSize() {
        return size;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getDictionary() {
        return dictionary;
    }

    public Metal getMetal() {
        return metal;
    }

    public int getMeltingAmount() {
        return meltingAmount;
    }

    public boolean canMelt() {
        return canMelt;
    }

    public boolean canStack() {
        return canStack;
    }

    public boolean hasContainerItem() {
        return hasContainerItem;
    }

    // ---------------------------------------------------------------------
    // Factory
    // ---------------------------------------------------------------------

    public static Item Create(ItemRCType type) {
        return type.factory.apply(type);
    }

    public ItemRC create() {
        return factory.apply(this);
    }
}
