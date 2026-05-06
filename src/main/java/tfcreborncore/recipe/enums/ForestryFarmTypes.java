package tfcreborncore.recipe.enums;

import net.minecraft.item.ItemStack;

import tfcreborncore.recipe.RecipeHelper;

public enum ForestryFarmTypes {

    STONE_BRICK(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stonebrick", 0)),
    MOSSY_STONE_BRICK(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stonebrick", 1)),
    CRACKED_STONE_BRICK(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stonebrick", 2)),
    BRICKS(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "brick_block")),
    SMOOTH_SANDSTONE(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "sandstone", 2)),
    CHISELED_SANDSTONE(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "sandstone", 1)),
    NETHER_BRICK(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "nether_brick")),
    CHISELED_STONE_BRICKS(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stonebrick", 3)),
    QUARTZ_BLOCK(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "quartz_block", 0)),
    QUARTZ_CHISELED(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "quartz_block", 1)),
    QUARTZ_LINES(RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "quartz_block", 2));

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final ItemStack input;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ForestryFarmTypes(ItemStack input) {
        this.input = input;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public ItemStack getInput() {
        return this.input;
    }
}
