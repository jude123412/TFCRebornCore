package tfcreborncore.objects.items.enums;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCOre;
import tfcreborncore.recipe.enums.OreProcessingTypes;

public enum ItemRCOreType {

    CRUSHED(5, Size.VERY_SMALL, Weight.VERY_LIGHT),
    PURIFIED(10, Size.VERY_SMALL, Weight.VERY_LIGHT),
    PELLET(25, Size.NORMAL, Weight.LIGHT),
    PELLET_DOUBLE(50, Size.LARGE, Weight.MEDIUM),
    PELLET_TRIPLE(75, Size.LARGE, Weight.HEAVY),
    BAR(100, Size.LARGE, Weight.MEDIUM);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meltingAmount;
    private final Size size;
    private final Weight weight;

    private final BiFunction<OreProcessingTypes, ItemRCOreType, Item> supplier;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCOreType(int meltingAmount, Size size, Weight weight) {
        this(meltingAmount, size, weight, ItemRCOre::new);
    }

    ItemRCOreType(int meltingAmount, Size size, Weight weight,
                  @Nonnull BiFunction<OreProcessingTypes, ItemRCOreType, Item> supplier) {
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

    public static Item Create(OreProcessingTypes processingType, ItemRCOreType type) {
        return type.supplier.apply(processingType, type);
    }
}
