package tfcreborncore.objects.items.enums;

import java.util.function.Function;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRC;

public enum ItemRCType {

    // Metal Press Components (non-stackable)
    METAL_PRESS_SLEEVE(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_RACKWHEEL_PIECE(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_RACKWHEEL(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_LONG_ROD(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_BOLT(Size.NORMAL, Weight.MEDIUM, false),
    METAL_PRESS_SCREW(Size.NORMAL, Weight.MEDIUM, false),

    // Wood sheets
    WOOD_SHEET(Size.SMALL, Weight.MEDIUM, true),
    LATEX_COATED_WOOD_SHEET(Size.SMALL, Weight.MEDIUM, true),

    // Electrical components (light)
    ELECTRICAL_DOODAR_BASE_PLATE(Size.SMALL, Weight.LIGHT, true),
    ELECTRICAL_DOODAR_HOUSING(Size.SMALL, Weight.LIGHT, true),
    ELECTRICAL_THINGAMAJIG_STAGE_1(Size.SMALL, Weight.LIGHT, true),
    ELECTRICAL_THINGAMAJIG_STAGE_2(Size.SMALL, Weight.LIGHT, true),
    ELECTRICAL_THINGAMAJIG_STAGE_3(Size.SMALL, Weight.LIGHT, true),

    // Finished electrical items
    ELECTRICAL_DOODAR(Size.VERY_SMALL, Weight.MEDIUM, true),
    ELECTRICAL_THINGAMAJIG(Size.VERY_SMALL, Weight.MEDIUM, true),

    // Heavy components
    RF_CONTROL_CIRCUIT(Size.LARGE, Weight.HEAVY, true),
    BRASS_PISTON(Size.VERY_LARGE, Weight.HEAVY, true),

    // Fuels
    LIGNITE_COKE(Size.SMALL, Weight.MEDIUM, "gemLigniteCoke", true),
    BITUMINOUS_COAL_COKE(Size.SMALL, Weight.MEDIUM, "gemBituminousCokeCoke", true),

    // Dust
    HARDENED_GLASS_MIX(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustHardenedGlass", true),
    COAL_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustCoal", true),
    SYNTHETIC_GRAPHITE_MIX(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustSyntheticGraphite", true),
    WOOD_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustWood", true),
    CERTUS_QUARTZ_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustCertusQuartz", true),
    ENDERPEARL_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustEnder", true),
    OBSIDIAN_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustObsidian", true),
    SNOW_POWDER(Size.VERY_SMALL, Weight.VERY_LIGHT, "dustSnow", true),

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
    RADIATOR_PIPING(Size.SMALL, Weight.MEDIUM, true),
    RADIATOR_MATRIX(Size.NORMAL, Weight.MEDIUM, true),
    SLAG(Size.VERY_SMALL, Weight.VERY_LIGHT, "crystalSlag", true),
    RICH_SLAG(Size.VERY_SMALL, Weight.VERY_LIGHT, "crystalSlagRich", true);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final Size size;
    private final Weight weight;
    private final boolean canStack;
    private final String dictionary;

    private final Function<ItemRCType, ItemRC> factory;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCType(Size size, Weight weight, boolean canStack) {
        this(size, weight, canStack, null, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, String ore, boolean canStack) {
        this(size, weight, canStack, ore, ItemRC::new);
    }

    ItemRCType(Size size, Weight weight, boolean canStack, String ore,
               Function<ItemRCType, ItemRC> factory) {
        this.size = size;
        this.weight = weight;
        this.canStack = canStack;
        this.dictionary = ore;
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

    public boolean canStack() {
        return canStack;
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
