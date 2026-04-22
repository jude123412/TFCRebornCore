package tfcreborncore.recipe.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodData;
import net.dries007.tfc.api.capability.food.FoodHandler;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.MetalItemHandler;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFluidMixing;
import net.dries007.tfc.api.recipes.heat.HeatRecipeSimple;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipeSimple;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.fuel.Fuel;
import net.dries007.tfc.util.fuel.FuelManager;
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
     * Registers custom heat properties for a specific {@link ItemStack} in TFC.
     * <p>
     * Depending on whether the item is forgeable, this creates either a
     * {@link ForgeableHeatableHandler} or a standard {@link ItemHeatHandler}.
     * The handler defines the item's heat capacity, melting temperature, and
     * whether it can be worked on the anvil. The mapping is stored in
     * {@link CapabilityItemHeat#CUSTOM_ITEMS} using an {@link IIngredient}
     * wrapper as the lookup key.
     *
     * @param inputStack      The item to assign heat properties to.
     * @param heatCapacity    The amount of heat the item can store.
     * @param meltTemperature The temperature at which the item melts.
     * @param isForgable      Whether the item can be forged on the anvil.
     */
    @SuppressWarnings("unchecked")
    public static void addItemHeat(ItemStack inputStack, float heatCapacity, int meltTemperature, boolean isForgable) {
        if (isForgable) {
            CapabilityItemHeat.CUSTOM_ITEMS.computeIfAbsent(IIngredient.of(inputStack),
                    k -> (Supplier) () -> new ForgeableHeatableHandler(null, heatCapacity, meltTemperature));
        } else {
            CapabilityItemHeat.CUSTOM_ITEMS.computeIfAbsent(IIngredient.of(inputStack),
                    k -> (Supplier) () -> new ItemHeatHandler(null, heatCapacity, meltTemperature));
        }
    }

    /**
     * Registers custom food statistics for a specific {@link ItemStack} in TFC.
     * <p>
     * This assigns a {@link FoodHandler} containing a {@link FoodData} instance
     * that defines the nutritional profile, decay rate, and hydration value of
     * the item. The mapping is stored in {@link CapabilityFood#CUSTOM_FOODS}
     * using an {@link IIngredient} wrapper as the lookup key.
     *
     * @param inputStack The food item to assign nutritional data to.
     * @param hunger     The hunger value restored by the food.
     * @param water      The amount of water restored.
     * @param saturation The saturation modifier.
     * @param decay      The decay rate of the food.
     * @param grain      Grain nutrition value.
     * @param vegtable   Vegetable nutrition value.
     * @param fruit      Fruit nutrition value.
     * @param protein    Protein nutrition value.
     * @param dairy      Dairy nutrition value.
     */
    @SuppressWarnings("unchecked")
    public static void addItemFoodStats(ItemStack inputStack, int hunger, float water, float saturation, float decay,
                                        float grain, float vegtable, float fruit, float protein, float dairy) {
        CapabilityFood.CUSTOM_FOODS.put(IIngredient.of(inputStack), (Supplier) () -> new FoodHandler(
                null,
                new FoodData(hunger, water, saturation, grain, fruit, vegtable, protein, dairy, decay)));
    }

    /**
     * Registers a custom fuel definition for a specific {@link ItemStack} in TFC.
     * <p>
     * This creates a {@link Fuel} entry defining burn duration, burn temperature,
     * and whether the item can be used as forge or bloomery fuel. If the fuel
     * passes validation via {@link FuelManager#canRegister(Fuel)}, it is added to
     * the global fuel registry.
     *
     * @param inputStack     The item to register as a fuel source.
     * @param burnTicks      How long the fuel burns (in ticks).
     * @param temperature    The burn temperature produced by the fuel.
     * @param isForgeFuel    Whether the fuel is valid for forges.
     * @param isBloomeryFuel Whether the fuel is valid for bloomeries.
     */
    @SuppressWarnings("unchecked")
    public static void addItemFuel(ItemStack inputStack, int burnTicks, float temperature, boolean isForgeFuel,
                                   boolean isBloomeryFuel) {
        Fuel fuel = new Fuel(IIngredient.of(inputStack), burnTicks, temperature, isForgeFuel, isBloomeryFuel);
        if (FuelManager.canRegister(fuel)) FuelManager.addFuel(fuel);
    }

    /**
     * Registers a new TFC knapping recipe.
     *
     * This creates a {@link KnappingRecipeSimple} using the specified knapping
     * type, output item, and pattern. The recipe is assigned the provided
     * {@link ResourceLocation} and registered directly into
     * {@link TFCRegistries#KNAPPING}.
     *
     * @param regName The unique registry name for the recipe.
     * @param type    The knapping type (e.g., clay, leather, stone).
     * @param result  The resulting item produced by the knapping pattern.
     * @param pattern The knapping grid pattern, using 'X' for filled and ' ' for empty.
     */
    public static void addKnappingRecipe(ResourceLocation regName, KnappingType type, ItemStack result,
                                         String... pattern) {
        KnappingRecipe recipe = new KnappingRecipeSimple(type, true, result, pattern).setRegistryName(regName);
        TFCRegistries.KNAPPING.register(recipe);
    }

    /**
     * Registers a new TFC heat‑transform recipe.
     *
     * Heat transform recipes convert an input item into an output item once it
     * reaches a specified temperature. This creates a {@link HeatRecipeSimple}
     * using the provided input ingredient, output item, and transformation
     * temperature, assigns the given {@link ResourceLocation}, and registers it
     * into {@link TFCRegistries#HEAT}.
     *
     * @param regName              The unique registry name for the recipe.
     * @param inputStack           The ingredient that will transform when heated.
     * @param result               The resulting item after transformation.
     * @param transformTemperature The temperature required to trigger the transform.
     */
    public static void addHeatTransformRecipe(ResourceLocation regName, IIngredient<ItemStack> inputStack,
                                              ItemStack result, float transformTemperature) {
        HeatRecipeSimple recipe = (HeatRecipeSimple) new HeatRecipeSimple(inputStack, result, transformTemperature)
                .setRegistryName(regName);
        TFCRegistries.HEAT.register(recipe);
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
