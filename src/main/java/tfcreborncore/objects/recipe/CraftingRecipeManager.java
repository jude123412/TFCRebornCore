package tfcreborncore.objects.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingRecipeManager {

    public static final List<IRecipe> SHAPED_RECIPES = new ArrayList<>();

    public static void addShapedSkillRecipe(ResourceLocation name, ItemStack result, Object... objects) {
        Object[] list = new Object[objects.length + 1];
        list[0] = false;
        System.arraycopy(objects, 0, list, 1, objects.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ShapedSkillRecipe recipe = new ShapedSkillRecipe(name, result, primer);
        recipe.setRegistryName(name);

        SHAPED_RECIPES.add(recipe);
    }

    public static void addShapedRecipe(ResourceLocation name, ItemStack result, Object... objects) {
        Object[] list = new Object[objects.length + 1];
        list[0] = false;
        System.arraycopy(objects, 0, list, 1, objects.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ShapedOreRecipe recipe = new ShapedOreRecipe(name, result, primer);
        recipe.setRegistryName(name);

        SHAPED_RECIPES.add(recipe);
    }
}
