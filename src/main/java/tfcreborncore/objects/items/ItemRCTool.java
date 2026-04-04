package tfcreborncore.objects.items;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

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
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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

import blusunrize.immersiveengineering.api.ApiUtils;
import blusunrize.immersiveengineering.api.TargetingInfo;
import blusunrize.immersiveengineering.api.energy.wires.IImmersiveConnectable;
import blusunrize.immersiveengineering.api.energy.wires.ImmersiveNetHandler;
import blusunrize.immersiveengineering.common.IESaveData;
import blusunrize.immersiveengineering.common.util.Utils;
import mcp.MethodsReturnNonnullByDefault;
import tfcreborncore.objects.items.enums.ItemRCToolType;

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

    private static final Map<Metal, EnumMap<ItemRCToolType, ItemRCTool>> TOOL_MAP = new HashMap<>();

    private final Metal metal;
    private final ItemRCToolType type;

    public ItemRCTool(Metal metal, ItemRCToolType type) {
        super();
        this.metal = metal;
        this.type = type;

        if (metal.getToolMetal() == null) {
            throw new IllegalArgumentException("Thou shall not make tools out of non tool metals.");
        }

        this.material = metal.getToolMetal();
        this.setMaxStackSize(1);
        this.efficiency = this.material.getEfficiency();

        int harvestLevel = this.material.getHarvestLevel();

        // 🔥 All old switch logic replaced by enum data
        this.setHarvestLevel(type.getHarvestType(), harvestLevel);
        this.setMaxDamage(this.material.getMaxUses());
        this.areaOfEffect = type.getAreaOfEffect();
        this.attackSpeed = type.getAttackSpeed();

        // Attack damage is still scaled by metal
        this.attackDamage = type.getAttackDamage() * this.material.getAttackDamage();

        // Registry map logic unchanged
        TOOL_MAP.computeIfAbsent(metal, m -> new EnumMap<>(ItemRCToolType.class))
                .put(type, this);
    }

    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
                                    EntityLivingBase entityLiving) {
        if (state.getBlockHardness(worldIn, pos) > 0.0F && !worldIn.isRemote) stack.damageItem(1, entityLiving);

        // Prevent AoE when breaking plants or other non-target blocks
        if (!this.canHarvestBlock(state)) return true;

        int area = (this.areaOfEffect - 1) / 2;

        EnumFacing face = entityLiving.getHorizontalFacing();

        // Detect Mining Face
        if (entityLiving instanceof EntityPlayer) {
            RayTraceResult hit = entityLiving.rayTrace(5.0D, 1.0F);

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

        // Disable AOE without breaking functionality
        if (entityLiving.isSneaking()) {
            return true;
        }

        if (area >= 1 && entityLiving instanceof EntityPlayer player && !worldIn.isRemote) {
            for (BlockPos extraPos : BlockPos.getAllInBoxMutable(minX, minY, minZ, maxX, maxY, maxZ)) {
                IBlockState st = worldIn.getBlockState(extraPos);

                if (!extraPos.equals(pos) && !worldIn.isAirBlock(extraPos) && this.canHarvestBlock(st)) {
                    st.getBlock().onPlayerDestroy(worldIn, extraPos, st);
                    st.getBlock().harvestBlock(worldIn, player,
                            extraPos, st, worldIn.getTileEntity(extraPos), stack);
                    worldIn.setBlockToAir(extraPos);
                    stack.damageItem(1, player);
                }
            }
        }

        return true;
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side,
                                      float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        TileEntity tileEntity = world.getTileEntity(pos);

        switch (type) {
            case WIRE_CUTTER -> {
                // Based on wire cutter logic taken from Immersive Engineering's
                // ItemIETool class (BluSunrize)
                if (tileEntity instanceof IImmersiveConnectable immersiveConnectableTile) {
                    TargetingInfo target = new TargetingInfo(side, hitX, hitY, hitZ);
                    tileEntity = world.getTileEntity(immersiveConnectableTile.getConnectionMaster(null, target));
                    if (!(tileEntity instanceof IImmersiveConnectable nodeHere)) return EnumActionResult.PASS;
                    if (!world.isRemote) {
                        boolean cut = ImmersiveNetHandler.INSTANCE.clearAllConnectionsFor(Utils.toCC(nodeHere), world,
                                target);
                        IESaveData.setDirty(world.provider.getDimension());
                        if (cut) stack.damageItem(1, player);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }
            default -> {
                return EnumActionResult.PASS;
            }
        }

        return EnumActionResult.FAIL;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        switch (type) {
            case WIRE_CUTTER -> {
                // Based on wire cutter logic taken from Immersive Engineering's
                // ItemIETool class (BluSunrize)
                if (!world.isRemote) {
                    double reachDistance = player.getAttributeMap().getAttributeInstance(EntityPlayer.REACH_DISTANCE)
                            .getAttributeValue();
                    ImmersiveNetHandler.Connection target = ApiUtils.getTargetConnection(world, player, null,
                            reachDistance);
                    if (target != null) {
                        ImmersiveNetHandler.INSTANCE.removeConnectionAndDrop(target, world, player.getPosition());
                        stack.damageItem(1, player);
                    }
                }

                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }
            default -> {
                return new ActionResult<>(EnumActionResult.PASS, stack);
            }
        }
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
        stack.damageItem(2, attacker);
        return true;
    }

    public int getItemEnchantability() {
        return this.material.getEnchantability();
    }

    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, @Nonnull Enchantment enchantment) {
        switch (this.type) {
            case WIRE_CUTTER -> {
                return enchantment == Enchantments.EFFICIENCY || enchantment == Enchantments.UNBREAKING ||
                        enchantment == Enchantments.MENDING;
            }
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

    public ItemRCToolType getType() {
        return type;
    }

    @Nullable
    public static ItemRCTool get(Metal metal, ItemRCToolType type) {
        return TOOL_MAP.get(metal).get(type);
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
}
