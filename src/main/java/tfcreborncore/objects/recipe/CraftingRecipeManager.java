package tfcreborncore.objects.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

import java.util.ArrayList;
import java.util.List;

public class CraftingRecipeManager {

    public static final List<IRecipe> SKILL_RECIPES = new ArrayList<>();

    public static void addShapedSkillRecipe(ResourceLocation name, ItemStack result, Object... objects) {
        Object[] list = new Object[objects.length + 1];
        list[0] = false;
        System.arraycopy(objects, 0, list, 1, objects.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ShapedSkillRecipe recipe = new ShapedSkillRecipe(name, result, primer);
        recipe.setRegistryName(name);

        SKILL_RECIPES.add(recipe);
    }
}
