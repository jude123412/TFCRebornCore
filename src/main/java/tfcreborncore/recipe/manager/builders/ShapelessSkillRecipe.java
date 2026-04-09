package tfcreborncore.recipe.manager.builders;

import javax.annotation.Nonnull;

import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import org.jetbrains.annotations.NotNull;

/*
 * Original code from Terrafirmacraft's ShapedSkillRecipe (EUPL v1.2)
 * Modified to create a publicly accessible recipe class
 * Modified by xXjudeXx on 2026-04-09
 */
public class ShapelessSkillRecipe extends ShapelessOreRecipe {

    public ShapelessSkillRecipe(ResourceLocation group, @NotNull ItemStack result, Object... recipe) {
        super(group, result, recipe);
    }

    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
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
