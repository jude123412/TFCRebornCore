package tfcreborncore.objects.items;

import java.util.HashMap;
import java.util.Map;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import tfcreborncore.objects.items.enums.ItemRCEnum;

public class ItemRC extends ItemTFC {

    public static final Map<String, ItemRCEnum> CROSS_MOD_MAP = new HashMap<>();

    private final ItemRCEnum type;

    public ItemRC(ItemRCEnum type) {
        super();
        this.type = type;
        if (!CROSS_MOD_MAP.containsKey(type.toString())) {
            CROSS_MOD_MAP.put(type.toString(), type);
        }
    }

    public ItemRCEnum getType() {
        return type;
    }

    @Nullable
    public static ItemRC get(String typeName) {
        return CROSS_MOD_MAP.get(typeName).create();
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        return switch (type) {
            // @formatter:off
            case ELECTRICAL_DOODAR_BASE_PLATE,
                 ELECTRICAL_DOODAR_HOUSING,
                 ELECTRICAL_THINGAMAJIG_STAGE_1,
                 ELECTRICAL_THINGAMAJIG_STAGE_2,
                 ELECTRICAL_THINGAMAJIG_STAGE_3,
                 LIGNITE_COKE,
                 BITUMINOUS_COAL_COKE -> Size.SMALL;
            case METAL_PRESS_SLEEVE,
                 METAL_PRESS_RACKWHEEL_PIECE,
                 METAL_PRESS_RACKWHEEL,
                 METAL_PRESS_LONG_ROD,
                 METAL_PRESS_BOLT,
                 METAL_PRESS_SCREW -> Size.NORMAL;
            case RF_CONTROL_CIRCUIT -> Size.LARGE;
            case BRASS_PISTON -> Size.VERY_LARGE;
            default -> Size.VERY_SMALL;
            // @formatter:on
        };
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        return switch (type) {
            // @formatter:off
            case ELECTRICAL_DOODAR_BASE_PLATE,
                 ELECTRICAL_DOODAR_HOUSING,
                 ELECTRICAL_THINGAMAJIG_STAGE_1,
                 ELECTRICAL_THINGAMAJIG_STAGE_2,
                 ELECTRICAL_THINGAMAJIG_STAGE_3 -> Weight.LIGHT;
            case METAL_PRESS_SLEEVE,
                 METAL_PRESS_RACKWHEEL_PIECE,
                 METAL_PRESS_RACKWHEEL,
                 METAL_PRESS_LONG_ROD,
                 METAL_PRESS_BOLT,
                 METAL_PRESS_SCREW,
                 WOOD_SHEET,
                 LATEX_COATED_WOOD_SHEET,
                 ELECTRICAL_DOODAR,
                 ELECTRICAL_THINGAMAJIG,
                 LIGNITE_COKE,
                 BITUMINOUS_COAL_COKE -> Weight.MEDIUM;
            case RF_CONTROL_CIRCUIT,
                 BRASS_PISTON -> Weight.HEAVY;
            default -> Weight.VERY_LIGHT;
            // @formatter:on
        };
    }

    @Override
    public boolean canStack(@NotNull ItemStack stack) {
        return switch (type) {
            // @formatter:off
            case METAL_PRESS_SLEEVE,
                 METAL_PRESS_RACKWHEEL_PIECE,
                 METAL_PRESS_RACKWHEEL,
                 METAL_PRESS_LONG_ROD,
                 METAL_PRESS_BOLT,
                 METAL_PRESS_SCREW -> false;
            default -> true;
            // @formatter:on
        };
    }

    @Override
    public int getItemBurnTime(@NotNull ItemStack itemStack) {
        return switch (type) {
            // @formatter:off
            case WOOD_SHEET,
                 LATEX_COATED_WOOD_SHEET -> 100;
            case LIGNITE_COKE -> 2400;
            case BITUMINOUS_COAL_COKE -> 3200;
            default -> -1;
            // @formatter:on
        };
    }
}
