package tfcreborncore.objects.items.enums;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Ore;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCOre;

public enum ItemRCOreEnum {

    PILE(5, Size.VERY_SMALL, Weight.VERY_LIGHT),
    CUBE(25, Size.NORMAL, Weight.LIGHT),
    BAR(100, Size.LARGE, Weight.MEDIUM);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meltingAmount;
    private final Size size;
    private final Weight weight;

    private final BiFunction<Ore, ItemRCOreEnum, Item> supplier;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCOreEnum(int meltingAmount, Size size, Weight weight) {
        this(meltingAmount, size, weight, ItemRCOre::new);
    }

    ItemRCOreEnum(int meltingAmount, Size size, Weight weight, @Nonnull BiFunction<Ore, ItemRCOreEnum, Item> supplier) {
        this.meltingAmount = meltingAmount;
        this.size = size;
        this.weight = weight;
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

    // ---------------------------------------------------------------------
    // Factory
    // ---------------------------------------------------------------------

    public static Item Create(Ore ore, ItemRCOreEnum type) {
        return type.supplier.apply(ore, type);
    }
}
