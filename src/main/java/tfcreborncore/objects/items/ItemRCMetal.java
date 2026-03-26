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

/*
 * Original code from TFC Tech's ItemTechMetal (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by xXjudeXx on 2026-03-21
 */
public class ItemRCMetal extends ItemTFC implements IMetalItem {

    private static final Map<Metal, EnumMap<ItemRCMetal.ItemType, ItemRCMetal>> METAL_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCMetal.ItemType type;

    public ItemRCMetal(Metal metal, ItemRCMetal.ItemType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!METAL_MAP.containsKey(metal)) {
            METAL_MAP.put(metal, new EnumMap<>(ItemRCMetal.ItemType.class));
        }
        METAL_MAP.get(metal).put(type, this);
        setNoRepair();
    }

    public ItemRCMetal.ItemType getType() {
        return type;
    }

    @Nullable
    public static ItemRCMetal get(Metal metal, ItemRCMetal.ItemType type) {
        return METAL_MAP.get(metal).get(type);
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case UNFINISHED_MINING_HAMMER_HEAD, EXCAVATOR_HEAD, MINING_HAMMER_HEAD, UNFINISHED_EXCAVATOR_HEAD -> {
                return Size.LARGE;
            }
            case RACKWHEEL_HALF -> {
                return Size.NORMAL;
            }
            default -> {
                return Size.VERY_SMALL;
            }
        }
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        switch (type) {
            case UNFINISHED_EXCAVATOR_HEAD, RACKWHEEL_HALF -> {
                return Weight.MEDIUM;
            }
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
        return (new TextComponentTranslation("item.tfcreborncore.metal." + type.name().toLowerCase() + ".name",
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

    public boolean canStack(@Nonnull ItemStack stack) {
        return switch (this.type) {
            case UNFINISHED_MINING_HAMMER_HEAD, UNFINISHED_EXCAVATOR_HEAD -> false;
            default -> true;
        };
    }

    public static boolean isToolHead(ItemRCMetal.ItemType type) {
        return switch (type) {
            case UNFINISHED_MINING_HAMMER_HEAD, UNFINISHED_EXCAVATOR_HEAD, MINING_HAMMER_HEAD, EXCAVATOR_HEAD -> true;
            default -> false;
        };
    }

    public enum ItemType {

        UNFINISHED_MINING_HAMMER_HEAD(300),
        MINING_HAMMER_HEAD(500),
        UNFINISHED_EXCAVATOR_HEAD(200),
        EXCAVATOR_HEAD(300),
        RACKWHEEL_HALF(200);

        ItemType(int meltingAmount) {
            this(meltingAmount, ItemRCMetal::new);
        }

        ItemType(int meltingAmount, @Nonnull BiFunction<Metal, ItemRCMetal.ItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Metal, ItemRCMetal.ItemType, Item> supplier;

        public static Item Create(Metal metal, ItemRCMetal.ItemType type) {
            return type.supplier.apply(metal, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
