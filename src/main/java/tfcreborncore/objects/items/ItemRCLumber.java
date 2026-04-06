package tfcreborncore.objects.items;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

import org.jetbrains.annotations.NotNull;

import tfcreborncore.objects.RCItems;
import tfcreborncore.objects.items.enums.ItemRCLumberType;

public class ItemRCLumber extends ItemTFC {

    public static final Map<String, ItemRCLumberType> WOOD_MAP = new HashMap<>();

    private final ItemRCLumberType type;

    public ItemRCLumber(ItemRCLumberType type) {
        super();
        this.type = type;
        if (!WOOD_MAP.containsKey(type.toString())) {
            WOOD_MAP.put(type.toString(), type);
        }
    }

    public ItemRCLumberType getType() {
        return type;
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        return Size.NORMAL;
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        return Weight.MEDIUM;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String lumberName = (new TextComponentTranslation(getType().toString().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcreborncore.wood.lumber.name", RCItems.toTitleCase(lumberName)))
                .getFormattedText();
    }
}
