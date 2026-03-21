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
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;

public class RCItemMetal extends ItemTFC implements IMetalItem {

    private static final Map<Metal, EnumMap<RCItemMetal.RCMetalItemType, RCItemMetal>> METAL_MAP = new HashMap<>();

    private final Metal metal;
    private final RCItemMetal.RCMetalItemType type;

    public RCItemMetal(Metal metal, RCItemMetal.RCMetalItemType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!METAL_MAP.containsKey(metal)) {
            METAL_MAP.put(metal, new EnumMap<>(RCItemMetal.RCMetalItemType.class));
        }
        METAL_MAP.get(metal).put(type, this);
        setNoRepair();
    }

    public RCItemMetal.RCMetalItemType getType() {
        return type;
    }

    @Nullable
    public static RCItemMetal get(Metal metal, RCItemMetal.RCMetalItemType type) {
        return METAL_MAP.get(metal).get(type);
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case UNFINISHED_MINING_HAMMER_HEAD, EXCAVATOR_HEAD -> {
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
            case UNFINISHED_MINING_HAMMER_HEAD, EXCAVATOR_HEAD -> {
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

    public enum RCMetalItemType {

        UNFINISHED_MINING_HAMMER_HEAD(300),
        MINING_HAMMER_HEAD(500),
        EXCAVATOR_HEAD(300);

        RCMetalItemType(int meltingAmount) {
            this(meltingAmount, RCItemMetal::new);
        }

        RCMetalItemType(int meltingAmount, @Nonnull BiFunction<Metal, RCItemMetal.RCMetalItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Metal, RCItemMetal.RCMetalItemType, Item> supplier;

        public static Item Create(Metal metal, RCItemMetal.RCMetalItemType type) {
            return type.supplier.apply(metal, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
