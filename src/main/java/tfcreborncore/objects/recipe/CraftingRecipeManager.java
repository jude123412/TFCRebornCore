package tfcreborncore.objects.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class CraftingRecipeManager {

    public static final List<IRecipe> RECIPE_LIST = new ArrayList<>();

    public static void addShapedSkillRecipe(ResourceLocation name, ItemStack result, Object... inputs) {
        Object[] list = new Object[inputs.length + 1];
        list[0] = false;
        System.arraycopy(inputs, 0, list, 1, inputs.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ShapedSkillRecipe recipe = new ShapedSkillRecipe(name, result, primer);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    public static void addShapedRecipe(ResourceLocation name, ItemStack result, Object... inputs) {
        Object[] list = new Object[inputs.length + 1];
        list[0] = false;
        System.arraycopy(inputs, 0, list, 1, inputs.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ShapedOreRecipe recipe = new ShapedOreRecipe(name, result, primer);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    public static void addShapelessRecipe(ResourceLocation name, ItemStack result, Object... inputs) {
        ShapelessOreRecipe recipe = new ShapelessOreRecipe(name, result, inputs);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    public static void addShapelessDamageRecipe(ResourceLocation name, int damage, ItemStack result, Object... inputs) {
        ShapelessDamageRecipe recipe = new ShapelessDamageRecipe(name, damage, result, inputs);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }
}
