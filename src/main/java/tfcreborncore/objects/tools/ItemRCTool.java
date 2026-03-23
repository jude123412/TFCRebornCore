package tfcreborncore.objects.tools;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.api.capability.damage.DamageType;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import mcp.MethodsReturnNonnullByDefault;

/*
 * Original code from ItemMetalTool (EUPL v1.2)
 * Modified to create ItemRCTool
 * Modified by Jude on 2026-03-23
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemRCTool extends ItemTFC implements IMetalItem {

    public final Item.ToolMaterial material;
    private final int areaOfEffect;
    private final float efficiency;

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

            switch (type) {
                case EXCAVATOR:
                    this.setHarvestLevel("shovel", harvestLevel);
                    this.setMaxDamage(this.material.getMaxUses() * 9);
                    this.areaOfEffect = 2;
                    OreDictionaryHelper.registerDamageType(this, DamageType.PIERCING);
                    break;
                case MINING_HAMMER:
                    this.setHarvestLevel("pickaxe", harvestLevel);
                    this.setMaxDamage(this.material.getMaxUses() * 9);
                    this.areaOfEffect = 2;
                    OreDictionaryHelper.registerDamageType(this, DamageType.CRUSHING);
                    break;
                default:
                    throw new IllegalArgumentException("Tool from non tool type.");
            }

            if (!TOOL_MAP.containsKey(metal)) {
                TOOL_MAP.put(metal, new EnumMap<>(ItemRCTool.ItemType.class));
            }
            TOOL_MAP.get(metal).put(type, this);
        }
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
                                    EntityLivingBase entityLiving) {
        if (state.getBlockHardness(world, pos) > 0.0F && !world.isRemote) {
            stack.damageItem(1, entityLiving);
        }

        if (this.areaOfEffect > 1 && entityLiving instanceof EntityPlayer player && !world.isRemote) {
            int area = this.areaOfEffect - 1;

            for (BlockPos.MutableBlockPos extraPos : BlockPos.getAllInBoxMutable(pos.add(-area, -area, -area),
                    pos.add(area, area, area))) {
                IBlockState iBlockState = world.getBlockState(extraPos);
                if (!extraPos.equals(pos) && !world.isAirBlock(extraPos) && this.canHarvestBlock(iBlockState)) {
                    iBlockState.getBlock().onPlayerDestroy(world, extraPos, iBlockState);
                    iBlockState.getBlock().harvestBlock(world, player, extraPos, iBlockState,
                            world.getTileEntity(extraPos), stack);
                    world.setBlockToAir(extraPos);
                    stack.damageItem(1, entityLiving);
                }
            }
        }
        return true;
    }

    public boolean canHarvestBlock(IBlockState state) {
        Material material = state.getMaterial();
        return switch (this.type) {
            case MINING_HAMMER -> material == Material.IRON || material == Material.ANVIL || material == Material.ROCK;
            case EXCAVATOR -> material == Material.SNOW || material == Material.CRAFTED_SNOW ||
                    material == Material.GRASS || material == Material.GROUND;
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
            case EXCAVATOR -> {
                return Size.VERY_LARGE;
            }
            case MINING_HAMMER -> {
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
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getMeltingAmount();
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
