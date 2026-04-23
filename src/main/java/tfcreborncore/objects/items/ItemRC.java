package tfcreborncore.objects.items;

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
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import tfcreborncore.objects.items.enums.ItemRCType;

/*
 * Original code from TFC Tech's ItemTechMetal (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by xXjudeXx on 2026-03-25
 */
public class ItemRC extends ItemTFC implements IMetalItem {

    public static final Map<String, ItemRCType> CROSS_MOD_MAP = new HashMap<>();

    private final ItemRCType type;

    public ItemRC(ItemRCType type) {
        super();
        this.type = type;
        if (!CROSS_MOD_MAP.containsKey(type.toString())) {
            CROSS_MOD_MAP.put(type.toString(), type);
        }
        if (type.hasContainerItem()) this.setContainerItem(this);
    }

    public ItemRCType getType() {
        return type;
    }

    @Nullable
    public static ItemRC get(String typeName) {
        return CROSS_MOD_MAP.get(typeName).create();
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack stack) {
        return type.getSize();
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack stack) {
        return type.getWeight();
    }

    @Override
    public boolean canStack(@NotNull ItemStack stack) {
        return type.canStack();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return type.getMetal() != null ?
                new ForgeableHeatableHandler(nbt, type.getMetal().getSpecificHeat(), type.getMetal().getMeltTemp()) :
                null;
    }

    @Override
    public @Nullable Metal getMetal(ItemStack itemStack) {
        return type.getMetal();
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getMeltingAmount();
    }

    @Override
    public boolean canMelt(ItemStack stack) {
        return type.canMelt();
    }

    @Override
    public float getMeltTemp(ItemStack stack) {
        return type.getMetal().getMeltTemp();
    }
}
