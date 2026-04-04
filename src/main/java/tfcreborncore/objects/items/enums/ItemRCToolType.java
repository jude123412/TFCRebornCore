package tfcreborncore.objects.items.enums;

import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCTool;

public enum ItemRCToolType {

    MINING_HAMMER(500, 3, 0.875F, -3.0F, "shovel", Size.LARGE, Weight.VERY_HEAVY),
    EXCAVATOR(300, 3, 1.0F, -3.0F, "pickaxe", Size.LARGE, Weight.HEAVY),
    WIRE_CUTTER(100, 1, 0.475F, -2.0F, "IE_WIRECUTTER", Size.NORMAL, Weight.MEDIUM);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meltingAmount;
    private final int areaOfEffect;
    private final float attackDamage;
    private final float attackSpeed;
    private final String harvestType;
    private final Size size;
    private final Weight weight;

    private final BiFunction<Metal, ItemRCToolType, Item> supplier;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCToolType(int meltingAmount, int area, float damage, float speed, String type, Size size, Weight weight) {
        this(meltingAmount, area, damage, speed, type, size, weight, ItemRCTool::new);
    }

    ItemRCToolType(int meltingAmount, int area, float damage, float speed, String type, Size size, Weight weight,
                   @Nonnull BiFunction<Metal, ItemRCToolType, Item> supplier) {
        this.meltingAmount = meltingAmount;
        this.areaOfEffect = area;
        this.attackDamage = damage;
        this.attackSpeed = speed;
        this.harvestType = type;
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

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public String getHarvestType() {
        return harvestType;
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

    public static Item Create(Metal metal, ItemRCToolType type) {
        return type.supplier.apply(metal, type);
    }
}
