package tfcreborncore.objects.recipe;


import com.google.gson.JsonObject;
import net.dries007.tfc.objects.recipes.RecipeUtils;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.inventory.InventoryCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

/*
 * Original code from Terrafirmacraft's ShapedSkillRecipe (EUPL v1.2)
 * Modified to create a publicly accessible recipe class
 * Modified by xXjudeXx on 2026-03-23
 */

public class ShapedSkillRecipe extends ShapedOreRecipe {

    public ShapedSkillRecipe(ResourceLocation name, @Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer) {
        super(name, result, primer);
    }

    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inventory) {
        for(int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack inputStack = inventory.getStackInSlot(i);
            float skillBonus = SmithingSkill.getSkillBonus(inputStack);
            if (skillBonus > 0.0F) {
                ItemStack outputStack = super.getCraftingResult(inventory);
                SmithingSkill.copySkillBonus(outputStack, inputStack);
                return outputStack;
            }
        }

        return super.getCraftingResult(inventory);
    }
}
