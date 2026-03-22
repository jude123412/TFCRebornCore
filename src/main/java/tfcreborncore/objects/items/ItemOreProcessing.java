package tfcreborncore.objects.items;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;

public class ItemOreProcessing extends ItemTFC implements IMetalItem {

    private static final Map<Ore, EnumMap<ItemType, ItemOreProcessing>> ORE_MAP = new HashMap<>();

    private final Ore ore;
    private final ItemType type;

    public ItemOreProcessing(Ore ore, ItemType type) {
        super();
        this.ore = ore;
        this.type = type;
        if (!ORE_MAP.containsKey(ore)) {
            ORE_MAP.put(ore, new EnumMap<>(ItemType.class));
        }
        ORE_MAP.get(ore).put(type, this);
        setNoRepair();
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case CUBE -> {
                return Size.NORMAL;
            }
            case BAR -> {
                return Size.LARGE;
            }
            default -> {
                return Size.VERY_SMALL;
            }
        }
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        switch (type) {
            case CUBE -> {
                return Weight.LIGHT;
            }
            case BAR -> {
                return Weight.MEDIUM;
            }
            default -> {
                return Weight.VERY_LIGHT;
            }
        }
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String metalName = (new TextComponentTranslation(
                "item.tfc.ore." + ore.getRegistryName().getPath().toLowerCase() + ".name")).getFormattedText();
        return (new TextComponentTranslation("item.tfcreborncore.ore_item." + type.name().toLowerCase() + ".name",
                metalName)).getFormattedText();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ForgeableHeatableHandler(nbt, ore.getMetal().getSpecificHeat(), ore.getMetal().getMeltTemp());
    }

    @Nonnull
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return ore.getMetal();
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getMeltingAmount();
    }

    @Override
    public boolean canMelt(ItemStack stack) {
        return ore.canMelt();
    }

    public Ore getOre() {
        return ore;
    }

    public enum ItemType {

        PILE(5),
        CUBE(25),
        BAR(100);

        ItemType(int meltingAmount) {
            this(meltingAmount, ItemOreProcessing::new);
        }

        ItemType(int meltingAmount, @Nonnull BiFunction<Ore, ItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Ore, ItemType, Item> supplier;

        public static Item Create(Ore ore, ItemType type) {
            return type.supplier.apply(ore, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
