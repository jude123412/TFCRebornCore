package tfcreborncore.objects.items;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import net.dries007.tfc.api.capability.damage.DamageType;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/*
 * Original code from Terrafirmacraft's ItemMetalSword (EUPL v1.2)
 * Modified to create ItemRCUniversalWeapon
 * Modified by xXjudeXx on 2026-04-09
 */
public class ItemRCUniversalWeapon extends ItemSword implements IMetalItem, IItemSize {

    private static final Map<Metal, ItemRCUniversalWeapon> TABLE = new HashMap();
    public final Item.ToolMaterial material;
    private final Metal metal;
    private final float attackDamage;

    public static ItemRCUniversalWeapon get(Metal metal) {
        return (ItemRCUniversalWeapon) TABLE.get(metal);
    }

    public ItemRCUniversalWeapon(Metal metal) {
        super(metal.getToolMetal());
        this.metal = metal;
        if (metal.getToolMetal() == null) {
            throw new IllegalArgumentException("You can't make weapons out of non tool metals.");
        } else {
            this.material = metal.getToolMetal();
            if (!TABLE.containsKey(metal)) {
                TABLE.put(metal, this);
            }
            this.setMaxStackSize(1);
            this.setMaxDamage(this.material.getMaxUses());
            this.attackDamage = this.material.getAttackDamage();
            OreDictionaryHelper.register(this, "universalWeapon");
            OreDictionaryHelper.registerDamageType(this, DamageType.SLASHING);
            OreDictionaryHelper.registerDamageType(this, DamageType.CRUSHING);
            OreDictionaryHelper.registerDamageType(this, DamageType.PIERCING);
        }
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) -2.4F, 0));
        }

        return multimap;
    }

    @Override
    public @Nullable Metal getMetal(ItemStack stack) {
        return this.metal;
    }

    public Metal getMetal() {
        return this.metal;
    }

    @Override
    public int getSmeltAmount(ItemStack stack) {
        if (this.isDamageable() && stack.isItemDamaged()) {
            double d = (double) (stack.getMaxDamage() - stack.getItemDamage()) / (double) stack.getMaxDamage() - 0.1;
            return d < (double) 0.0F ? 0 : MathHelper.floor((double) Metal.ItemType.SWORD.getSmeltAmount() * d);
        } else {
            return Metal.ItemType.SWORD.getSmeltAmount();
        }
    }

    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ForgeableHeatableHandler(nbt, this.metal.getSpecificHeat(), this.metal.getMeltTemp());
    }

    @Nonnull
    public IRarity getForgeRarity(@Nonnull ItemStack stack) {
        switch (this.metal.getTier()) {
            case TIER_I:
            case TIER_II:
                return EnumRarity.COMMON;
            case TIER_III:
                return EnumRarity.UNCOMMON;
            case TIER_IV:
                return EnumRarity.RARE;
            case TIER_V:
                return EnumRarity.EPIC;
            default:
                return super.getForgeRarity(stack);
        }
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        return Size.LARGE;
    }

    @Override
    public @NotNull Weight getWeight(@NotNull ItemStack itemStack) {
        return Weight.HEAVY;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        String metalName = (new TextComponentTranslation(
                "tfc.types.metal." + metal.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcreborncore.tool.universal_weapon.name",
                metalName)).getFormattedText();
    }
}
