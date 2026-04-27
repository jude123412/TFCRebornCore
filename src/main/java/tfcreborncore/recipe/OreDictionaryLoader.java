package tfcreborncore.recipe;

import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.enums.Mods;

public class OreDictionaryLoader {

    /**
     * These are global ore dictionaries
     * as stated these are needed everywhere
     * so they have to be registered before everything
     */
    public static void register() {
        // GemEnder
        OreDictionary.registerOre("gemEnder",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "ender_pearl"));
        // GemEnderEye
        OreDictionary.registerOre("gemEnderEye",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "ender_eye"));
        // DustGunpowder
        OreDictionary.registerOre("dustGunpowder",
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "gunpowder"));

        if (Mods.FORESTRY.isModLoaded()) {
            // Sweetener
            OreDictionary.registerOre("sweetener",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honey_drop"));
            OreDictionary.registerOre("sweetener",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "honeydew"));
            // Pollen
            OreDictionary.registerOre("itemPollen",
                    RecipeHelper.getItemStack(Mods.FORESTRY.ID, "pollen", 1));
            // Category Fruit
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 0));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 3));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 4));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 5));
            OreDictionary.registerOre("categoryFruit", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 6));
            // Category Grain
            OreDictionary.registerOre("categoryGrain", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 1));
            OreDictionary.registerOre("categoryGrain", RecipeHelper.getItemStack(Mods.FORESTRY.ID, "fruits", 2));
        }
    }
}
