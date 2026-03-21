package tfcreborncore.objects.items;

import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;

import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ItemMetal extends ItemTFC implements IMetalItem {

    private static final Map<Metal, EnumMap<ItemMetal.MetalItemType, ItemMetal>> ORE_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemMetal.MetalItemType type;

    public ItemMetal(Metal metal, ItemMetal.MetalItemType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!ORE_MAP.containsKey(metal)) {
            ORE_MAP.put(metal, new EnumMap<>(ItemMetal.MetalItemType.class));
        }
        ORE_MAP.get(metal).put(type, this);
        setNoRepair();
    }

    public ItemMetal.MetalItemType getType() {
        return type;
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case UNFINISHED_MINING_HAMMER_HEAD -> {
                return Size.VERY_LARGE;
            }
            case MINING_HAMMER_HEAD -> {
                return Size.HUGE;
            }
            default -> {
                return Size.VERY_SMALL;
            }
        }
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        switch (type) {
            case UNFINISHED_MINING_HAMMER_HEAD -> {
                return Weight.HEAVY;
            }
            case MINING_HAMMER_HEAD -> {
                return Weight.VERY_HEAVY;
            }
            default -> {
                return Weight.LIGHT;
            }
        }
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String metalName = (new TextComponentTranslation(
                "tfc.types.metal." + metal.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcreborncore.metal_item." + type.name().toLowerCase() + ".name",
                metalName)).getFormattedText();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ForgeableHeatableHandler(nbt, metal.getSpecificHeat(), metal.getMeltTemp());
    }

    @Nonnull
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getMeltingAmount();
    }

    public Metal getMetal() {
        return metal;
    }

    public enum MetalItemType {

        UNFINISHED_MINING_HAMMER_HEAD(300),
        MINING_HAMMER_HEAD(500);

        MetalItemType(int meltingAmount) {
            this(meltingAmount, ItemMetal::new);
        }

        MetalItemType(int meltingAmount, @Nonnull BiFunction<Metal, ItemMetal.MetalItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Metal, ItemMetal.MetalItemType, Item> supplier;

        public static Item Create(Metal metal, ItemMetal.MetalItemType type) {
            return type.supplier.apply(metal, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
