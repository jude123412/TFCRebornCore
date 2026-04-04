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
        return type.getSize();
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        return type.getWeight();
    }

    @Override
    public boolean canStack(@NotNull ItemStack stack) {
        return type.canStack();
    }
}
