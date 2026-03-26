package tfcreborncore.objects.items;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import mcp.MethodsReturnNonnullByDefault;

/*
 * Original code from Terrafirmacraft's ItemMetalTool (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by xXjudeXx on 2026-03-23
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemRCTool extends ItemTFC implements IMetalItem {

    public final Item.ToolMaterial material;
    private final int areaOfEffect;
    private final float efficiency;
    private final double attackDamage;
    private final float attackSpeed;

    private static final Map<Metal, EnumMap<ItemRCTool.ItemType, ItemRCTool>> TOOL_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCTool.ItemType type;

    public ItemRCTool(Metal metal, ItemRCTool.ItemType type) {
        super();
        this.metal = metal;
        this.type = type;
        if (metal.getToolMetal() == null) {
            throw new IllegalArgumentException("Thou shall not make tools out of non tool metals.");
        } else {
            this.material = metal.getToolMetal();
            this.setMaxStackSize(1);
            this.efficiency = this.material.getEfficiency();

            int harvestLevel = this.material.getHarvestLevel();
            float typeDamage;

            switch (type) {
                case EXCAVATOR:
                    typeDamage = 0.875F;
                    this.setHarvestLevel("shovel", harvestLevel);
                    this.setMaxDamage(this.material.getMaxUses());
                    this.areaOfEffect = 3;
                    this.attackSpeed = -3.0F;
                    break;
                case MINING_HAMMER:
                    typeDamage = 1.0F;
                    this.setHarvestLevel("pickaxe", harvestLevel);
                    this.setMaxDamage(this.material.getMaxUses());
                    this.areaOfEffect = 3;
                    this.attackSpeed = -3.0F;
                    break;
                default:
                    throw new IllegalArgumentException("Tool from non tool type.");
            }

            this.attackDamage = (double) (typeDamage * this.material.getAttackDamage());

            if (!TOOL_MAP.containsKey(metal)) {
                TOOL_MAP.put(metal, new EnumMap<>(ItemRCTool.ItemType.class));
            }
            TOOL_MAP.get(metal).put(type, this);
        }
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
                                    EntityLivingBase entityLiving) {
        if (world.isRemote) return true;

        // Prevent AoE when breaking plants or other non-target blocks
        if (!this.canHarvestBlock(state, stack)) return true;

        int area = (this.areaOfEffect - 1) / 2;

        EnumFacing face = entityLiving.getHorizontalFacing();

        // Detect Mining Face
        if (entityLiving instanceof EntityPlayer) {
            RayTraceResult hit = ((EntityPlayer) entityLiving).rayTrace(5.0D, 1.0F);

            if (hit != null && hit.typeOfHit == RayTraceResult.Type.BLOCK) {
                face = hit.sideHit;
            }
        }

        int minX = pos.getX();
        int minY = pos.getY();
        int minZ = pos.getZ();
        int maxX = pos.getX();
        int maxY = pos.getY();
        int maxZ = pos.getZ();

        switch (face) {
            case UP:
            case DOWN:
                minX -= area;
                maxX += area;
                minZ -= area;
                maxZ += area;
                break;

            case NORTH:
            case SOUTH:
                minX -= area;
                maxX += area;
                minY -= area;
                maxY += area;
                break;

            case EAST:
            case WEST:
                minY -= area;
                maxY += area;
                minZ -= area;
                maxZ += area;
                break;
        }

        for (BlockPos.MutableBlockPos extraPos : BlockPos.getAllInBoxMutable(minX, minY, minZ, maxX, maxY, maxZ)) {

            if (extraPos.equals(pos)) continue;

            // Disable AOE without breaking functionality
            if (entityLiving.isSneaking()) {
                stack.damageItem(1, entityLiving);
                return true;
            }

            IBlockState extraState = world.getBlockState(extraPos);

            if (!world.isAirBlock(extraPos) && this.canHarvestBlock(extraState)) {
                extraState.getBlock().onPlayerDestroy(world, extraPos, extraState);
                extraState.getBlock().harvestBlock(world, (EntityPlayer) entityLiving,
                        extraPos, extraState, world.getTileEntity(extraPos), stack);
                world.setBlockToAir(extraPos);
                stack.damageItem(1, entityLiving);
            }
        }

        return true;
    }

    public boolean canHarvestBlock(IBlockState state) {
        Material material = state.getMaterial();
        return switch (this.type) {
            case MINING_HAMMER -> material == Material.IRON || material == Material.ANVIL || material == Material.ROCK;
            case EXCAVATOR -> material == Material.SNOW || material == Material.CRAFTED_SNOW ||
                    material == Material.GRASS || material == Material.GROUND || material == Material.SAND;
            default -> false;
        };
    }

    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        for (String type : this.getToolClasses(stack)) {
            if (state.getBlock().isToolEffective(type, state)) {
                return true;
            }
        }

        return this.canHarvestBlock(state);
    }

    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return this.canHarvestBlock(state, stack) ? this.efficiency : 1.0F;
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        switch (this.type) {
            case EXCAVATOR, MINING_HAMMER -> {
                stack.damageItem(2, attacker);
            }
        }

        return true;
    }

    public int getItemEnchantability() {
        return this.material.getEnchantability();
    }

    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, @Nonnull Enchantment enchantment) {
        switch (this.type) {
            case EXCAVATOR, MINING_HAMMER -> {
                return enchantment.type == EnumEnchantmentType.DIGGER;
            }
            default -> {
                return false;
            }
        }
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        if (slot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, 0));
        }

        return multimap;
    }

    public double getAttackDamage() {
        return this.attackDamage;
    }

    public ItemRCTool.ItemType getType() {
        return type;
    }

    @Nullable
    public static ItemRCTool get(Metal metal, ItemRCTool.ItemType type) {
        return TOOL_MAP.get(metal).get(type);
    }

    @Override
    public @NotNull Size getSize(@NotNull ItemStack itemStack) {
        switch (type) {
            case EXCAVATOR, MINING_HAMMER -> {
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
            case EXCAVATOR -> {
                return Weight.HEAVY;
            }
            case MINING_HAMMER -> {
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
        return (new TextComponentTranslation("item.tfcreborncore.tool." + type.name().toLowerCase() + ".name",
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
    public int getSmeltAmount(ItemStack stack) {
        if (this.isDamageable() && stack.isItemDamaged()) {
            double d = (double) (stack.getMaxDamage() - stack.getItemDamage()) / (double) stack.getMaxDamage() - 0.1;
            return d < (double) 0.0F ? 0 : MathHelper.floor((double) this.type.getMeltingAmount() * d);
        } else {
            return this.type.getMeltingAmount();
        }
    }

    public Metal getMetal() {
        return metal;
    }

    public enum ItemType {

        MINING_HAMMER(500),
        EXCAVATOR(300);

        ItemType(int meltingAmount) {
            this(meltingAmount, ItemRCTool::new);
        }

        ItemType(int meltingAmount, @Nonnull BiFunction<Metal, ItemRCTool.ItemType, Item> supplier) {
            this.meltingAmount = meltingAmount;
            this.supplier = supplier;
        }

        private final int meltingAmount;
        private final BiFunction<Metal, ItemRCTool.ItemType, Item> supplier;

        public static Item Create(Metal metal, ItemRCTool.ItemType type) {
            return type.supplier.apply(metal, type);
        }

        public int getMeltingAmount() {
            return meltingAmount;
        }
    }
}
