package tfcreborncore.objects.items;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemRC extends ItemTFC {

    public static final Map<String, ItemRC.ItemType> CROSS_MOD_MAP = new HashMap<>();

    private final ItemRC.ItemType type;

    public ItemRC(ItemRC.ItemType type) {
        super();
        this.type = type;
        if (!CROSS_MOD_MAP.containsKey(type.toString())) {
            CROSS_MOD_MAP.put(type.toString(), type);
        }
    }

    public ItemRC.ItemType getType() {
        return type;
    }

    @Nullable
    public static ItemRC get(String typeName) {
        return CROSS_MOD_MAP.get(typeName).create();
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case METAL_PRESS_SLEEVE, METAL_PRESS_RACKWHEEL_PIECE, METAL_PRESS_RACKWHEEL, METAL_PRESS_LONG_ROD, METAL_PRESS_BOLT, METAL_PRESS_SCREW -> {
                return Size.NORMAL;
            }
            case RF_CONTROL_CIRCUIT -> {
                return Size.LARGE;
            }
            default -> {
                return Size.SMALL;
            }
        }
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        switch (type) {
            case METAL_PRESS_SLEEVE, METAL_PRESS_RACKWHEEL_PIECE, METAL_PRESS_RACKWHEEL, METAL_PRESS_LONG_ROD, METAL_PRESS_BOLT, METAL_PRESS_SCREW -> {
                return Weight.MEDIUM;
            }
            case RF_CONTROL_CIRCUIT -> {
                return Weight.HEAVY;
            }
            default -> {
                return Weight.VERY_LIGHT;
            }
        }
    }

    @Override
    public boolean canStack(@NotNull ItemStack stack) {
        switch (type) {
            case METAL_PRESS_SLEEVE, METAL_PRESS_RACKWHEEL_PIECE, METAL_PRESS_RACKWHEEL, METAL_PRESS_LONG_ROD, METAL_PRESS_BOLT, METAL_PRESS_SCREW -> {
                return false;
            }
            default -> {
                return true;
            }
        }
    }

    public enum ItemType {

        METAL_PRESS_SLEEVE,
        METAL_PRESS_RACKWHEEL_PIECE,
        METAL_PRESS_RACKWHEEL,
        METAL_PRESS_LONG_ROD,
        METAL_PRESS_BOLT,
        METAL_PRESS_SCREW,
        RF_CONTROL_CIRCUIT,
        HARDENED_GLASS_MIX;

        ItemType() {
            this(ItemRC::new);
        }

        private final Function<ItemType, ItemRC> factory;

        ItemType(Function<ItemType, ItemRC> factory) {
            this.factory = factory;
        }

        public static Item Create(ItemRC.ItemType type) {
            return type.factory.apply(type);
        }

        public ItemRC create() {
            return factory.apply(this);
        }
    }
}
