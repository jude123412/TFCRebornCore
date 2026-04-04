package tfcreborncore.objects.items;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

import tfcreborncore.objects.items.enums.ItemRCMetalType;

/*
 * Original code from TFC Tech's ItemTechMetal (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by xXjudeXx on 2026-03-21
 */
public class ItemRCMetal extends ItemTFC implements IMetalItem {

    private static final Map<Metal, EnumMap<ItemRCMetalType, ItemRCMetal>> METAL_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCMetalType type;

    public ItemRCMetal(Metal metal, ItemRCMetalType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (!METAL_MAP.containsKey(metal)) {
            METAL_MAP.put(metal, new EnumMap<>(ItemRCMetalType.class));
        }
        METAL_MAP.get(metal).put(type, this);
        setNoRepair();
    }

    public ItemRCMetalType getType() {
        return type;
    }

    @Nullable
    public static ItemRCMetal get(Metal metal, ItemRCMetalType type) {
        return METAL_MAP.get(metal).get(type);
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        return type.getSize();
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        return type.getWeight();
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
        return type.canStack();
    }
}
