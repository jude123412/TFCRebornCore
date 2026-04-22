package tfcreborncore.recipe.manager;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import tfctech.api.recipes.GlassworkingRecipe;
import tfctech.api.recipes.SmelteryRecipe;
import tfctech.registry.TechRegistries;

public class TFCTechRecipeManager {

    /**
     * Registers a new smeltery recipe.
     *
     * Smeltery recipes define how one or more solid inputs melt into a molten
     * fluid at a specified temperature. This method builds a {@link SmelteryRecipe}
     * by adding each input ingredient to the recipe builder, then assigning the
     * provided {@link ResourceLocation} as the registry name before registering
     * it directly into {@link TechRegistries#SMELTERY}.
     *
     * @param regName  The unique registry name for the recipe.
     * @param result   The molten fluid produced when the inputs are melted.
     * @param meltTemp The temperature required to melt the inputs.
     * @param inputs   One or more input ingredients accepted by the smeltery.
     */
    @SafeVarargs
    public static void addSmelteryRecipe(ResourceLocation regName, FluidStack result, int meltTemp,
                                         IIngredient<ItemStack>... inputs) {
        SmelteryRecipe.Builder recipe = new SmelteryRecipe.Builder();

        for (IIngredient<ItemStack> input : inputs) {
            recipe.addInput(input);
        }

        recipe.setOutput(result, meltTemp);
        TechRegistries.SMELTERY.register(recipe.build().setRegistryName(regName));
    }

    /**
     * Registers a new glassworking recipe.
     *
     * Glassworking recipes define how molten glass is shaped using a pattern
     * of inputs. This creates a {@link GlassworkingRecipe} using the specified
     * output item and pattern, assigns the provided {@link ResourceLocation}
     * as its registry name, and registers it directly into
     * {@link TechRegistries#GLASSWORKING}.
     *
     * @param regName The unique registry name for the recipe.
     * @param result  The item produced by the glassworking pattern.
     * @param pattern The glassworking grid pattern, using characters to define
     *                the required shape.
     */
    public static void addGlassWorkingRecipe(ResourceLocation regName, ItemStack result, String... pattern) {
        GlassworkingRecipe recipe = new GlassworkingRecipe(result, pattern).setRegistryName(regName);
        TechRegistries.GLASSWORKING.register(recipe);
    }
}
