package tfcreborncore.recipe.manager;

import java.util.function.Supplier;

import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.MetalItemHandler;
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
import net.minecraftforge.registries.IForgeRegistry;

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
     * Registers a new TFC anvil recipe.
     * <p>
     * Creates an {@link AnvilRecipe} with the specified input ingredient, output
     * item, minimum anvil tier, smithing skill type, and required forge rules.
     * The recipe is assigned a registry name under {@code tfc:anvil/<name>} and
     * registered into the provided {@link IForgeRegistry}.
     *
     * @param r           The anvil recipe registry.
     * @param regName     The unique name for the recipe (lowercased automatically).
     * @param inputStack  The input ingredient required for the anvil operation.
     * @param outputStack The resulting item produced by the recipe.
     * @param minTier     The minimum metal tier required to perform the recipe.
     * @param skillType   The smithing skill category used for XP gain.
     * @param forgeRules  The sequence of forging steps required to complete the recipe.
     */
    public static void addAnvilRecipe(IForgeRegistry<AnvilRecipe> r, ResourceLocation regName,
                                      IIngredient<ItemStack> inputStack,
                                      ItemStack outputStack, Metal.Tier minTier, SmithingSkill.Type skillType,
                                      ForgeRule... forgeRules) {
        r.register(new AnvilRecipe(regName, inputStack,
                outputStack, minTier, skillType, forgeRules));
    }

    /**
     * Registers a new TFC quern recipe.
     * <p>
     * This creates a {@link QuernRecipe} using the specified input ingredient and
     * output item, assigns the provided {@link ResourceLocation} as its registry
     * name, and registers it into the supplied {@link IForgeRegistry}. Quern
     * recipes define simple manual grinding transformations performed using the
     * TFC quern.
     *
     * @param r          The quern recipe registry.
     * @param regName    The unique registry name for the recipe.
     * @param inputStack The ingredient required to be ground in the quern.
     * @param result     The resulting {@link ItemStack} produced by grinding.
     */
    public static void addQuernRecipe(IForgeRegistry<QuernRecipe> r, ResourceLocation regName,
                                      IIngredient<ItemStack> inputStack, ItemStack result) {
        r.register(new QuernRecipe(inputStack, result).setRegistryName(regName));
    }

    /**
     * Registers a new TFC barrel recipe involving fluid + item transformation.
     * <p>
     * Creates a {@link BarrelRecipe} that consumes the specified input fluid and
     * input item, producing either an output fluid, an output item, or both. The
     * recipe is assigned a registry name under {@code tfc:barrel/<name>} and
     * registered into the provided {@link IForgeRegistry}.
     *
     * @param r           The barrel recipe registry.
     * @param inputFluid  The required input fluid ingredient.
     * @param inputStack  The required input item ingredient.
     * @param outputFluid The resulting fluid (maybe {@code null}).
     * @param outputStack The resulting item (maybe {@code ItemStack.EMPTY}).
     * @param duration    The time (in ticks) required to complete the recipe.
     * @param regName     The unique name for the recipe (lowercased automatically).
     */
    public static void addBarrelRecipe(IForgeRegistry<BarrelRecipe> r, ResourceLocation regName,
                                       IIngredient<FluidStack> inputFluid,
                                       IIngredient<ItemStack> inputStack, FluidStack outputFluid, ItemStack outputStack,
                                       int duration) {
        r.register(new BarrelRecipe(inputFluid, inputStack, outputFluid, outputStack, duration * 1000)
                .setRegistryName(regName));
    }

    /**
     * Registers a TFC barrel fluid-mixing recipe.
     * <p>
     * This variant mixes two fluids together inside the barrel. The first fluid is
     * defined as an {@link IIngredient}, while the second is wrapped in an
     * {@link IngredientFluidItem}. The resulting {@link BarrelRecipeFluidMixing}
     * produces a new output fluid after the specified duration. The recipe is
     * registered under {@code tfc:barrel/mixing/<name>}.
     *
     * @param r           The barrel recipe registry.
     * @param inputFluid1 The first input fluid ingredient.
     * @param inputFluid2 The second input fluid (exact stack).
     * @param outputFluid The resulting mixed fluid.
     * @param duration    The time (in ticks) required for mixing.
     * @param regName     The unique name for the recipe (lowercased automatically).
     */
    public static void addBarrelRecipeFluidMixin(IForgeRegistry<BarrelRecipe> r, ResourceLocation regName,
                                                 IIngredient<FluidStack> inputFluid1,
                                                 FluidStack inputFluid2, FluidStack outputFluid, int duration) {
        r.register(new BarrelRecipeFluidMixing(inputFluid1, new IngredientFluidItem(inputFluid2), outputFluid,
                duration * 1000)
                        .setRegistryName(regName));
    }
}
