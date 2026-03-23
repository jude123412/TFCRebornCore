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
public class ItemRCToolHead extends ItemTFC implements IMetalItem {

    private static final Map<Metal, EnumMap<ItemRCToolHead.ItemType, ItemRCToolHead>> METAL_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCToolHead.ItemType type;

    public ItemRCToolHead(Metal metal, ItemRCToolHead.ItemType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!METAL_MAP.containsKey(metal)) {
            METAL_MAP.put(metal, new EnumMap<>(ItemRCToolHead.ItemType.class));
        }
        METAL_MAP.get(metal).put(type, this);
        setNoRepair();
    }

    public ItemRCToolHead.ItemType getType() {
        return type;
    }

    @Nullable
    public static ItemRCToolHead get(Metal metal, ItemRCToolHead.ItemType type) {
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
        return (new TextComponentTranslation("item.tfcreborncore.tool_head." + type.name().toLowerCase() + ".name",
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

    public enum ItemType {

        UNFINISHED_MINING_HAMMER_HEAD(300),
        MINING_HAMMER_HEAD(500),
        EXCAVATOR_HEAD(300);

        ItemType(int meltingAmount) {
            this(meltingAmount, ItemRCToolHead::new);
        }

        ItemType(int meltingAmount, @Nonnull BiFunction<Metal, ItemRCToolHead.ItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Metal, ItemRCToolHead.ItemType, Item> supplier;

        public static Item Create(Metal metal, ItemRCToolHead.ItemType type) {
            return type.supplier.apply(metal, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
