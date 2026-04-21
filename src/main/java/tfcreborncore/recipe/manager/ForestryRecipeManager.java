package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.cleanroommc.groovyscript.core.mixin.forestry.CarpenterRecipeManagerAccessor;
import com.cleanroommc.groovyscript.core.mixin.forestry.FabricatorRecipeManagerAccessor;

import forestry.api.recipes.ICarpenterRecipe;
import forestry.api.recipes.IFabricatorRecipe;
import forestry.api.recipes.IFabricatorSmeltingRecipe;
import forestry.core.recipes.ShapedRecipeCustom;
import forestry.factory.recipes.CarpenterRecipe;
import forestry.factory.recipes.FabricatorRecipe;
import forestry.factory.recipes.FabricatorSmeltingRecipe;
import forestry.factory.recipes.FabricatorSmeltingRecipeManager;

public class ForestryRecipeManager {

    /**
     * Removes all registered Carpenter recipes.
     * <p>
     * This performs a complete reset of the Carpenter processing system by clearing
     * the internal recipe list accessed through
     * {@link CarpenterRecipeManagerAccessor#getRecipes()}. All default Forestry
     * Carpenter recipes and any mod-added Carpenter recipes are removed. Use this
     * when replacing the entire Carpenter recipe set or implementing a custom
     * progression overhaul.
     */
    public static void removeAllCarpenterRecipes() {
        CarpenterRecipeManagerAccessor.getRecipes().clear();
    }

    /**
     * Registers a new Forestry Carpenter recipe.
     * <p>
     * This creates an {@link ICarpenterRecipe} using the specified packaging time,
     * optional box item, optional fluid input, and a shaped crafting pattern
     * provided through {@link ShapedRecipeCustom}. The resulting recipe is added
     * directly to the Carpenter recipe list via
     * {@link CarpenterRecipeManagerAccessor#getRecipes()}.
     * <p>
     * If {@code box} is {@code null}, it is replaced with {@link ItemStack#EMPTY}
     * to ensure the recipe is valid. The Carpenter will consume the input fluid,
     * apply the crafting pattern, and output the specified result item.
     *
     * @param packageTime The time (in ticks) required for the Carpenter to process the recipe.
     * @param box         The optional box item used in the recipe (maybe {@code null}).
     * @param result      The resulting {@link ItemStack} produced by the recipe.
     * @param fluidInput  The optional {@link FluidStack} consumed by the Carpenter (maybe {@code null}).
     * @param inputs      The shaped crafting pattern and ingredient definitions.
     */
    public static void addCarpenterRecipe(int packageTime, ItemStack box, ItemStack result, FluidStack fluidInput,
                                          Object... inputs) {
        if (box == null) box = ItemStack.EMPTY;
        if (result == null || result.isEmpty())
            throw new IllegalArgumentException("Carpenter recipe result cannot be null or empty!");

        ICarpenterRecipe recipe = new CarpenterRecipe(packageTime, fluidInput, box,
                new ShapedRecipeCustom(result, inputs));
        CarpenterRecipeManagerAccessor.getRecipes().add(recipe);
    }

    /**
     * Removes all registered Fabricator recipes, including both crafting and smelting
     * definitions.
     * <p>
     * This clears the internal recipe list accessed through
     * {@link FabricatorRecipeManagerAccessor#getRecipes()} as well as the smelting
     * recipe list stored in {@link FabricatorSmeltingRecipeManager#recipes}. All
     * default Forestry Fabricator recipes and any mod-added recipes are removed.
     * Use this when replacing or rebuilding the entire Fabricator processing set.
     */
    public static void removeAllFabricatorRecipes() {
        FabricatorRecipeManagerAccessor.getRecipes().clear();
        FabricatorSmeltingRecipeManager.recipes.clear();
    }

    /**
     * Registers a new Forestry Fabricator crafting recipe.
     * <p>
     * This constructs a {@link ShapedRecipeCustom} from the provided pattern and
     * ingredient definitions, then uses its parsed ingredient matrix to create a
     * {@link FabricatorRecipe}. The recipe defines a molten-fluid input, an optional
     * cast item, and a shaped crafting grid that determines the final output.
     * <p>
     * If {@code cast} is {@code null}, it is replaced with {@link ItemStack#EMPTY}.
     * The {@code result} must be non-null and non-empty or an exception is thrown.
     * The recipe is added directly to the Fabricator recipe list via
     * {@link FabricatorRecipeManagerAccessor#getRecipes()}.
     *
     * @param cast       The optional cast item required for the recipe (may be empty).
     * @param fluidInput The molten fluid consumed by the Fabricator.
     * @param result     The resulting {@link ItemStack} produced by the recipe.
     * @param inputs     The shaped crafting pattern and ingredient definitions.
     */
    public static void addFabricatorRecipe(ItemStack cast, FluidStack fluidInput, ItemStack result, Object... inputs) {
        if (cast == null) cast = ItemStack.EMPTY;
        if (result == null || result.isEmpty())
            throw new IllegalArgumentException("Fabricator recipe result cannot be null or empty!");

        ShapedRecipeCustom internal = new ShapedRecipeCustom(result, inputs);
        IFabricatorRecipe recipe = new FabricatorRecipe(
                cast,
                fluidInput,
                result,
                internal.getRawIngredients(),
                internal.getOreDicts(),
                internal.getRecipeWidth(),
                internal.getRecipeHeight());

        FabricatorRecipeManagerAccessor.getRecipes().add(recipe);
    }

    /**
     * Registers a new Forestry Fabricator smelting recipe.
     * <p>
     * Smelting recipes define how solid items melt into molten fluids inside the
     * Fabricator. This creates a {@link FabricatorSmeltingRecipe} using the provided
     * input item, resulting fluid, and melting point temperature. The recipe is then
     * added to {@link FabricatorSmeltingRecipeManager#recipes}.
     * <p>
     * The {@code input} must be non-null and non-empty or an exception is thrown.
     *
     * @param input        The item to be melted in the Fabricator.
     * @param result       The molten fluid produced by melting the input.
     * @param meltingPoint The temperature required to melt the item.
     */
    public static void addFabricatorSmeltingRecipe(ItemStack input, FluidStack result, int meltingPoint) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("Fabricator melting recipe input cannot be null or empty!");

        IFabricatorSmeltingRecipe recipe = new FabricatorSmeltingRecipe(input, result, meltingPoint);
        FabricatorSmeltingRecipeManager.recipes.add(recipe);
    }
}
