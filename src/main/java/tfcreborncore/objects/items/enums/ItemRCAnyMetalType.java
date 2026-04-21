package tfcreborncore.objects.items.enums;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCAnyMetal;

public enum ItemRCAnyMetalType {

    ELECTRON_TUBE_BASE(100, Size.LARGE, Weight.LIGHT, false),
    PIPE_FRAME(200, Size.LARGE, Weight.LIGHT, true);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meltingAmount;
    private final Size size;
    private final Weight weight;
    private final boolean usableMetalsOnly;

    private final BiFunction<Metal, ItemRCAnyMetalType, Item> supplier;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCAnyMetalType(int meltingAmount, Size size, Weight weight, boolean usableMetalsOnly) {
        this(meltingAmount, size, weight, usableMetalsOnly, ItemRCAnyMetal::new);
    }

    ItemRCAnyMetalType(int meltingAmount, Size size, Weight weight, boolean usableMetalsOnly,
                       @Nonnull BiFunction<Metal, ItemRCAnyMetalType, Item> supplier) {
        this.meltingAmount = meltingAmount;
        this.size = size;
        this.weight = weight;
        this.usableMetalsOnly = usableMetalsOnly;
        this.supplier = supplier;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public int getMeltingAmount() {
        return meltingAmount;
    }

    public Size getSize() {
        return size;
    }

    public Weight getWeight() {
        return weight;
    }

    public boolean isUsableMetalsOnly() {
        return usableMetalsOnly;
    }

    // ---------------------------------------------------------------------
    // Factory
    // ---------------------------------------------------------------------

    public static Item Create(Metal metal, ItemRCAnyMetalType type) {
        return type.supplier.apply(metal, type);
    }
}
