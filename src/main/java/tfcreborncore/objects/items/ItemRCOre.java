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
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;

import tfcreborncore.objects.items.enums.ItemRCOreType;

/*
 * Original code from TFC Tech's ItemTechMetal (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by xXjudeXx on 2026-03-21
 */
public class ItemRCOre extends ItemTFC implements IMetalItem {

    private static final Map<Ore, EnumMap<ItemRCOreType, ItemRCOre>> ORE_MAP = new HashMap<>();

    private final Ore ore;
    private final ItemRCOreType type;

    public ItemRCOre(Ore ore, ItemRCOreType type) {
        super();
        this.ore = ore;
        this.type = type;
        if (!ORE_MAP.containsKey(ore)) {
            ORE_MAP.put(ore, new EnumMap<>(ItemRCOreType.class));
        }
        ORE_MAP.get(ore).put(type, this);
        setNoRepair();
    }

    public ItemRCOreType getType() {
        return type;
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
}
