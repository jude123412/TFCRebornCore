package tfcreborncore.objects.items.enums;

import java.util.function.Function;

import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRC;

public enum ItemRCEnum {

    METAL_PRESS_SLEEVE,
    METAL_PRESS_RACKWHEEL_PIECE,
    METAL_PRESS_RACKWHEEL,
    METAL_PRESS_LONG_ROD,
    METAL_PRESS_BOLT,
    METAL_PRESS_SCREW,
    WOOD_SHEET,
    LATEX_COATED_WOOD_SHEET,
    ELECTRICAL_DOODAR_BASE_PLATE,
    ELECTRICAL_DOODAR_HOUSING,
    ELECTRICAL_THINGAMAJIG_STAGE_1,
    ELECTRICAL_THINGAMAJIG_STAGE_2,
    ELECTRICAL_THINGAMAJIG_STAGE_3,
    ELECTRICAL_DOODAR,
    ELECTRICAL_THINGAMAJIG,
    RF_CONTROL_CIRCUIT,
    HARDENED_GLASS_MIX,
    LIGNITE_COKE,
    BITUMINOUS_COAL_COKE,
    COAL_POWDER,
    SYNTHETIC_GRAPHITE_MIX,
    WOOD_POWDER,
    CERTUS_QUARTZ_POWDER,
    ENDERPEARL_POWDER,
    SLAG,
    RICH_SLAG,
    BRASS_PISTON;

    ItemRCEnum() {
        this(ItemRC::new);
    }

    private final Function<ItemRCEnum, ItemRC> factory;

    ItemRCEnum(Function<ItemRCEnum, ItemRC> factory) {
        this.factory = factory;
    }

    public static Item Create(ItemRCEnum type) {
        return type.factory.apply(type);
    }

    public ItemRC create() {
        return factory.apply(this);
    }
}
