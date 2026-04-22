package tfcreborncore.recipe.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.MetalItemHandler;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFluidMixing;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import tfcreborncore.recipe.enums.Mods;

public class TerrafirmacraftRecipeManager {

    /**
     * Registers a custom metal definition for a specific {@link ItemStack} in TFC.
     * <p>
     * This associates the given item with a {@link MetalItemHandler} that defines
     * its metal type, melt amount, and whether the item is allowed to melt in a
     * forge or crucible. The mapping is stored in
     * {@link CapabilityMetalItem#CUSTOM_METAL_ITEMS} using an {@link IIngredient}
     * wrapper as the lookup key.
     *
     * @param inputStack    The item to associate with a metal definition.
     * @param metalLocation The registry name of the TFC metal to assign.
     * @param amount        The amount of metal (in units) the item contains.
     * @param canMelt       Whether the item is allowed to melt into liquid metal.
     */
    @SuppressWarnings("unchecked")
    public static void addItemMetal(ItemStack inputStack, ResourceLocation metalLocation, int amount, boolean canMelt) {
        Metal metal = TFCRegistries.METALS.getValue(metalLocation);
        CapabilityMetalItem.CUSTOM_METAL_ITEMS.computeIfAbsent(IIngredient.of(inputStack),
                k -> (Supplier) () -> new MetalItemHandler(metal, amount, canMelt));
    }

    /**
     * Registers a custom metal definition for a specific {@link ItemStack} in TFC.
     * <p>
     * This variant accepts a direct {@link Metal} instance rather than a
     * {@link ResourceLocation}, allowing callers to bypass a registry lookup when
     * the metal object is already known. The item is associated with a
     * {@link MetalItemHandler} that defines its metal type, melt amount, and
     * whether the item is allowed to melt in a forge or crucible. The mapping is
     * stored in {@link CapabilityMetalItem#CUSTOM_METAL_ITEMS} using an
     * {@link IIngredient} wrapper as the lookup key.
     *
     * @param inputStack The item to associate with a metal definition.
     * @param metal      The TFC {@link Metal} instance to assign to the item.
     * @param amount     The amount of metal (in units) the item contains.
     * @param canMelt    Whether the item is allowed to melt into liquid metal.
     */
    @SuppressWarnings("unchecked")
    public static void addItemMetal(ItemStack inputStack, Metal metal, int amount, boolean canMelt) {
        CapabilityMetalItem.CUSTOM_METAL_ITEMS.computeIfAbsent(IIngredient.of(inputStack),
                k -> (Supplier) () -> new MetalItemHandler(metal, amount, canMelt));
    }

    /**
     * Registers a new TFC anvil recipe.
     * <p>
     * Creates an {@link AnvilRecipe} with the specified input ingredient, output
     * item, minimum anvil tier, smithing skill type, and required forge rules.
     * The recipe is assigned the provided {@link ResourceLocation} as its registry
     * name and registered directly into {@link TFCRegistries#ANVIL}.
     *
     * @param regName     The unique registry name for the recipe.
     * @param inputStack  The input ingredient required for the anvil operation.
     * @param outputStack The resulting item produced by the recipe.
     * @param minTier     The minimum metal tier required to perform the recipe.
     * @param skillType   The smithing skill category used for XP gain.
     * @param forgeRules  The sequence of forging steps required to complete the recipe.
     */
    public static void addAnvilRecipe(ResourceLocation regName,
                                      IIngredient<ItemStack> inputStack,
                                      ItemStack outputStack, Metal.Tier minTier, SmithingSkill.Type skillType,
                                      ForgeRule... forgeRules) {
        AnvilRecipe recipe = new AnvilRecipe(regName, inputStack, outputStack, minTier, skillType, forgeRules);
        TFCRegistries.ANVIL.register(recipe);
    }

    /**
     * Registers a new TFC anvil welding recipe.
     * <p>
     * Welding recipes combine two input ingredients on the anvil to produce a
     * single output item. This method constructs a {@link WeldingRecipe} using the
     * provided inputs, output, minimum anvil tier, and smithing skill type, then
     * registers it directly into {@link TFCRegistries#WELDING}.
     * <p>
     * Welding requires both input items to be heated appropriately and placed
     * together on the anvil before performing the required smithing actions.
     *
     * @param regName   The unique registry name for the recipe.
     * @param inputA    The first ingredient required for welding.
     * @param inputB    The second ingredient required for welding.
     * @param result    The resulting {@link ItemStack} produced by welding.
     * @param minTier   The minimum metal tier of anvil required to perform the weld.
     * @param skillType The smithing skill category used for XP gain.
     */
    public static void addWeldingRecipe(ResourceLocation regName,
                                        IIngredient<ItemStack> inputA,
                                        IIngredient<ItemStack> inputB, ItemStack result, Metal.Tier minTier,
                                        SmithingSkill.Type skillType) {
        WeldingRecipe recipe = new WeldingRecipe(regName, inputA, inputB, result, minTier, skillType);
        TFCRegistries.WELDING.register(recipe);
    }

    /**
     * Registers a new TFC quern recipe.
     * <p>
     * Creates a {@link QuernRecipe} using the specified input ingredient and
     * output item, assigns the provided {@link ResourceLocation} as its registry
     * name, and registers it directly into {@link TFCRegistries#QUERN}.
     *
     * @param regName    The unique registry name for the recipe.
     * @param inputStack The ingredient required to be ground in the quern.
     * @param result     The resulting {@link ItemStack} produced by grinding.
     */
    public static void addQuernRecipe(ResourceLocation regName,
                                      IIngredient<ItemStack> inputStack, ItemStack result) {
        QuernRecipe recipe = new QuernRecipe(inputStack, result).setRegistryName(regName);
        TFCRegistries.QUERN.register(recipe);
    }

    /**
     * Removes all TFC quern recipes that produce the specified output item.
     * <p>
     * This method scans {@link TFCRegistries#QUERN} and identifies any
     * {@link QuernRecipe} whose primary output matches the provided item using
     * {@link ItemStack#isItemEqual(ItemStack)}. Matching recipes are removed using
     * {@link IForgeRegistryModifiable#remove(ResourceLocation)}.
     * <p>
     * Recipes belonging to this mod (namespace matching {@code tfc_reborn_core})
     * are ignored to avoid removing internally registered recipes.
     *
     * @param result The output item used to identify which quern recipes to remove.
     */
    public static void removeQuernRecipe(ItemStack result) {
        List<QuernRecipe> toRemove = new ArrayList<>();

        TFCRegistries.QUERN.getValuesCollection().stream()
                .filter(r -> r.getOutputs().get(0).isItemEqual(result) &&
                        !r.getRegistryName().getNamespace().contains(Mods.TFC_REBORN_CORE.ID))
                .forEach(toRemove::add);

        for (QuernRecipe recipe : toRemove) {
            IForgeRegistryModifiable registry = (IForgeRegistryModifiable) TFCRegistries.QUERN;
            registry.remove(recipe.getRegistryName());
        }
    }

    /**
     * Registers a new TFC barrel recipe involving fluid + item transformation.
     * <p>
     * Creates a {@link BarrelRecipe} that consumes the specified input fluid and
     * input item, producing an output fluid, an output item, or both. The recipe is
     * assigned the provided {@link ResourceLocation} and registered directly into
     * {@link TFCRegistries#BARREL}.
     *
     * @param regName     The unique registry name for the recipe.
     * @param inputFluid  The required input fluid ingredient.
     * @param inputStack  The required input item ingredient.
     * @param outputFluid The resulting fluid (may be {@code null}).
     * @param outputStack The resulting item (may be {@code ItemStack.EMPTY}).
     * @param duration    The time (in ticks) required to complete the recipe.
     */
    public static void addBarrelRecipe(ResourceLocation regName,
                                       IIngredient<FluidStack> inputFluid,
                                       IIngredient<ItemStack> inputStack, FluidStack outputFluid, ItemStack outputStack,
                                       int duration) {
        BarrelRecipe recipe = new BarrelRecipe(inputFluid, inputStack, outputFluid, outputStack, duration * 1000)
                .setRegistryName(regName);
        TFCRegistries.BARREL.register(recipe);
    }

    /**
     * Registers a TFC barrel fluid‑mixing recipe.
     * <p>
     * This variant mixes two fluids together inside the barrel. The first fluid is
     * defined as an {@link IIngredient}, while the second is wrapped in an
     * {@link IngredientFluidItem}. The resulting {@link BarrelRecipeFluidMixing}
     * produces a new output fluid after the specified duration. The recipe is
     * assigned the provided {@link ResourceLocation} and registered directly into
     * {@link TFCRegistries#BARREL}.
     *
     * @param regName     The unique registry name for the recipe.
     * @param inputFluid1 The first input fluid ingredient.
     * @param inputFluid2 The second input fluid (exact stack).
     * @param outputFluid The resulting mixed fluid.
     * @param duration    The time (in ticks) required for mixing.
     */

    public static void addBarrelRecipeFluidMixin(ResourceLocation regName,
                                                 IIngredient<FluidStack> inputFluid1,
                                                 FluidStack inputFluid2, FluidStack outputFluid, int duration) {
        if (duration != 0) duration = duration * 1000;
        BarrelRecipeFluidMixing recipe = (BarrelRecipeFluidMixing) new BarrelRecipeFluidMixing(inputFluid1,
                new IngredientFluidItem(inputFluid2), outputFluid, duration).setRegistryName(regName);
        TFCRegistries.BARREL.register(recipe);
    }
}
