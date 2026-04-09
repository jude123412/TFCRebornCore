package tfcreborncore.objects.items.enums;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCMetal;

public enum ItemRCMetalType {

    UNFINISHED_MINING_HAMMER_HEAD(300, Size.LARGE, Weight.HEAVY, false, true),
    MINING_HAMMER_HEAD(500, Size.LARGE, Weight.VERY_HEAVY, true, true),
    UNFINISHED_EXCAVATOR_HEAD(200, Size.LARGE, Weight.MEDIUM, false, true),
    EXCAVATOR_HEAD(300, Size.LARGE, Weight.HEAVY, true, true),
    WIRE_CUTTER_HEAD(100, Size.NORMAL, Weight.MEDIUM, true, true),
    UNFINISHED_UNIVERSAL_WEAPON_HEAD(200, Size.LARGE, Weight.MEDIUM, false, true),
    UNIVERSAL_WEAPON_HEAD(200, Size.LARGE, Weight.MEDIUM, true, true),
    RACKWHEEL_HALF(200, Size.NORMAL, Weight.MEDIUM, true, false);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meltingAmount;
    private final Size size;
    private final Weight weight;
    private final boolean canStack;
    private final boolean isToolHead;

    private final BiFunction<Metal, ItemRCMetalType, Item> supplier;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCMetalType(int meltingAmount, Size size, Weight weight, boolean canStack, boolean isToolHead) {
        this(meltingAmount, size, weight, canStack, isToolHead, ItemRCMetal::new);
    }

    ItemRCMetalType(int meltingAmount, Size size, Weight weight, boolean canStack, boolean isToolHead,
                    @Nonnull BiFunction<Metal, ItemRCMetalType, Item> supplier) {
        this.meltingAmount = meltingAmount;
        this.size = size;
        this.weight = weight;
        this.canStack = canStack;
        this.isToolHead = isToolHead;
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

    public boolean canStack() {
        return canStack;
    }

    public boolean isToolHead() {
        return isToolHead;
    }

    // ---------------------------------------------------------------------
    // Factory
    // ---------------------------------------------------------------------

    public static Item Create(Metal metal, ItemRCMetalType type) {
        return type.supplier.apply(metal, type);
    }
}
