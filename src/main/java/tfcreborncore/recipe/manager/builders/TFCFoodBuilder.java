package tfcreborncore.recipe.manager.builders;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodData;
import net.dries007.tfc.api.capability.food.FoodHandler;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;

/**
 * A fluent builder for registering custom TFC food properties.
 * <p>
 * This builder allows mods to define detailed nutritional data, decay rate,
 * hydration value, and hunger restoration for a specific {@link ItemStack}.
 * Once configured, calling {@link #register()} stores the resulting
 * {@link FoodHandler} in {@link CapabilityFood#CUSTOM_FOODS}, making the item
 * behave as a fully defined TFC food item.
 * <p>
 * Usage example:
 * 
 * <pre>
 * TFCFoodBuilder.create()
 *         .item(new ItemStack(Items.APPLE))
 *         .hunger(4)
 *         .water(5f)
 *         .fruit(10f)
 *         .decay(0.5f)
 *         .register();
 * </pre>
 */
public class TFCFoodBuilder {

    private ItemStack stack;
    private int hunger;
    private float water;
    private float saturation;
    private float decay;
    private float grain;
    private float vegetable;
    private float fruit;
    private float protein;
    private float dairy;

    private TFCFoodBuilder() {
        this.hunger = 0;
        this.water = 0f;
        this.saturation = 0f;
        this.decay = 0f;
        this.grain = 0f;
        this.vegetable = 0f;
        this.fruit = 0f;
        this.protein = 0f;
        this.dairy = 0f;
    }

    /**
     * Creates a new empty {@link TFCFoodBuilder} instance.
     * <p>
     * All nutritional values default to zero until explicitly set.
     *
     * @return A new builder instance.
     */
    public static TFCFoodBuilder create() {
        return new TFCFoodBuilder();
    }

    /**
     * Sets the item that will receive the custom food properties.
     *
     * @param stack The item stack to modify.
     * @return This builder instance for chaining.
     */
    public TFCFoodBuilder item(ItemStack stack) {
        this.stack = stack;
        return this;
    }

    /**
     * Sets the hunger value restored by the food.
     *
     * @param hunger The hunger amount.
     * @return This builder instance.
     */
    public TFCFoodBuilder hunger(int hunger) {
        this.hunger = hunger;
        return this;
    }

    /**
     * Sets the water value restored by the food.
     *
     * @param water The hydration amount.
     * @return This builder instance.
     */
    public TFCFoodBuilder water(float water) {
        this.water = water;
        return this;
    }

    /**
     * Sets the saturation modifier applied when eating the food.
     *
     * @param saturation The saturation value.
     * @return This builder instance.
     */
    public TFCFoodBuilder saturation(float saturation) {
        this.saturation = saturation;
        return this;
    }

    /**
     * Sets the decay rate of the food.
     *
     * @param decay The decay value.
     * @return This builder instance.
     */
    public TFCFoodBuilder decay(float decay) {
        this.decay = decay;
        return this;
    }

    /**
     * Sets the grain nutrition value.
     *
     * @param grain The grain category value.
     * @return This builder instance.
     */
    public TFCFoodBuilder grain(float grain) {
        this.grain = grain;
        return this;
    }

    /**
     * Sets the vegetable nutrition value.
     *
     * @param vegetable The vegetable category value.
     * @return This builder instance.
     */
    public TFCFoodBuilder vegetable(float vegetable) {
        this.vegetable = vegetable;
        return this;
    }

    /**
     * Sets the fruit nutrition value.
     *
     * @param fruit The fruit category value.
     * @return This builder instance.
     */
    public TFCFoodBuilder fruit(float fruit) {
        this.fruit = fruit;
        return this;
    }

    /**
     * Sets the protein nutrition value.
     *
     * @param protein The protein category value.
     * @return This builder instance.
     */
    public TFCFoodBuilder protein(float protein) {
        this.protein = protein;
        return this;
    }

    /**
     * Sets the dairy nutrition value.
     *
     * @param dairy The dairy category value.
     * @return This builder instance.
     */
    public TFCFoodBuilder dairy(float dairy) {
        this.dairy = dairy;
        return this;
    }

    /**
     * Registers the configured food properties into TFC.
     * <p>
     * This creates a {@link FoodHandler} containing a {@link FoodData} instance
     * built from the values supplied to this builder. The handler is then stored
     * in {@link CapabilityFood#CUSTOM_FOODS} using an {@link IIngredient} wrapper
     * of the target item as the lookup key.
     * <p>
     * After calling this method, the item behaves as a fully defined TFC food
     * with the specified nutritional profile and decay characteristics.
     */
    public void register() {
        CapabilityFood.CUSTOM_FOODS.put(
                IIngredient.of(stack),
                () -> new FoodHandler(
                        null,
                        new FoodData(
                                hunger,
                                water,
                                saturation,
                                grain,
                                fruit,
                                vegetable,
                                protein,
                                dairy,
                                decay)));
    }
}
