package tfcreborncore.objects.items;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import tfcreborncore.objects.items.enums.ItemRCAnyMetalType;

/*
 * Original code from TFC Tech's ItemTechMetal (EUPL v1.2)
 * Modified to create ItemRCAnyMetal
 * Modified by xXjudeXx on 2026-04-21
 */
public class ItemRCAnyMetal extends ItemTFC implements IMetalItem {

    public static final Map<Metal, EnumMap<ItemRCAnyMetalType, ItemRCAnyMetal>> TUBE_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCAnyMetalType type;

    public ItemRCAnyMetal(Metal metal, ItemRCAnyMetalType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!TUBE_MAP.containsKey(metal)) {
            TUBE_MAP.put(metal, new EnumMap<>(ItemRCAnyMetalType.class));
        }
        TUBE_MAP.get(metal).put(type, this);
    }

    public ItemRCAnyMetalType getType() {
        return type;
    }

    @Nullable
    public static ItemRCAnyMetal get(Metal metal, ItemRCAnyMetalType type) {
        return TUBE_MAP.get(metal).get(type);
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack stack) {
        return Size.SMALL;
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack stack) {
        return Weight.LIGHT;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String metalName = (new TextComponentTranslation(
                "tfc.types.metal." + metal.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcreborncore.metal." + type.name().toLowerCase() + ".name",
                metalName)).getFormattedText();
    }

    @javax.annotation.Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack,
                                                @javax.annotation.Nullable NBTTagCompound nbt) {
        return new ForgeableHeatableHandler(nbt, metal.getSpecificHeat(), metal.getMeltTemp());
    }

    @Nonnull
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return 100;
    }

    public Metal getMetal() {
        return metal;
    }
}
