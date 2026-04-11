package tfcreborncore.recipe.manager;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import tfcreborncore.recipe.manager.builders.ShapedSkillRecipe;
import tfcreborncore.recipe.manager.builders.ShapelessDamageRecipe;
import tfcreborncore.recipe.manager.builders.ShapelessSkillRecipe;

public class MinecraftRecipeManager {

    public static final List<IRecipe> RECIPE_LIST = new ArrayList<>();

    /**
     * Registers a shaped Skill-based crafting recipe.
     * <p>
     * This constructs a non-mirrored {@link CraftingHelper.ShapedPrimer} from the
     * provided input pattern and ingredients, wraps it in a {@link ShapedSkillRecipe},
     * assigns the given registry name, and adds it to the internal recipe list.
     * <p>
     * The first element inserted into the input array is {@code false}, which
     * disables mirrored pattern matching before parsing.
     *
     * @param name   The unique registry name for the recipe.
     * @param result The resulting {@link ItemStack} produced by the recipe.
     * @param inputs The shaped crafting pattern and ingredient definitions.
     */
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

    /**
     * Registers a shapeless Skill-based crafting recipe.
     * <p>
     * Creates a {@link ShapelessSkillRecipe} using the provided output and input
     * ingredients, assigns the registry name, and adds it to the internal recipe list.
     *
     * @param name   The unique registry name for the recipe.
     * @param result The resulting {@link ItemStack} produced by the recipe.
     * @param inputs The shapeless ingredient list for the recipe.
     */
    public static void addShapelessSkillRecipe(ResourceLocation name, ItemStack result, Object... inputs) {
        ShapelessSkillRecipe recipe = new ShapelessSkillRecipe(name, result, inputs);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    /**
     * Registers a standard shaped OreDictionary-aware crafting recipe.
     * <p>
     * Builds a non-mirrored {@link CraftingHelper.ShapedPrimer} from the provided
     * pattern and ingredient definitions, wraps it in a {@link ShapedOreRecipe},
     * assigns the registry name, and adds it to the internal recipe list.
     * <p>
     * This variant supports OreDictionary ingredient matching.
     *
     * @param name   The unique registry name for the recipe.
     * @param result The resulting {@link ItemStack} produced by the recipe.
     * @param inputs The shaped crafting pattern and ingredient definitions.
     */
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

    /**
     * Registers a standard shapeless OreDictionary-aware crafting recipe.
     * <p>
     * Creates a {@link ShapelessOreRecipe} using the provided output and input
     * ingredients, assigns the registry name, and adds it to the internal recipe list.
     *
     * @param name   The unique registry name for the recipe.
     * @param result The resulting {@link ItemStack} produced by the recipe.
     * @param inputs The shapeless ingredient list for the recipe.
     */
    public static void addShapelessRecipe(ResourceLocation name, ItemStack result, Object... inputs) {
        ShapelessOreRecipe recipe = new ShapelessOreRecipe(name, result, inputs);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    /**
     * Registers a shapeless crafting recipe that applies durability damage to one
     * or more input tools when crafted.
     * <p>
     * This uses a custom {@link ShapelessDamageRecipe} implementation which reduces
     * durability on specified inputs instead of consuming them entirely. The recipe
     * is assigned the provided registry name and added to the internal recipe list.
     *
     * @param name   The unique registry name for the recipe.
     * @param damage The amount of durability damage applied to tool inputs.
     * @param result The resulting {@link ItemStack} produced by the recipe.
     * @param inputs The shapeless ingredient list for the recipe.
     */
    public static void addShapelessDamageRecipe(ResourceLocation name, int damage, ItemStack result, Object... inputs) {
        ShapelessDamageRecipe recipe = new ShapelessDamageRecipe(name, damage, result, inputs);
        recipe.setRegistryName(name);

        RECIPE_LIST.add(recipe);
    }

    /**
     * Removes crafting recipes based on their output {@link ItemStack}.
     * <p>
     * This method scans all registered crafting recipes and identifies any whose
     * output item matches the provided {@code output} stack. Metadata comparison
     * supports wildcard matching via {@link OreDictionary#WILDCARD_VALUE}, allowing
     * removal of all variants of an item regardless of damage value.
     * <p>
     * Matching recipes are not deleted directly; instead, each is replaced with a
     * {@link DummyRecipe} registered under the same {@link ResourceLocation}. This
     * preserves registry integrity while effectively disabling the original recipe.
     *
     * @param registry The Forge registry event for {@link IRecipe} registration.
     * @param output   The output item to match against recipe results. May use
     *                 {@link OreDictionary#WILDCARD_VALUE} for metadata wildcarding.
     */
    @SuppressWarnings("unchecked")
    public static void removeRecipeByOutput(RegistryEvent.Register<IRecipe> registry, ItemStack output) {
        IForgeRegistryModifiable<IRecipe> r = (IForgeRegistryModifiable) registry.getRegistry();
        List<ResourceLocation> toRemove = new ArrayList<>();

        for (IRecipe recipe : r.getValuesCollection()) {
            ItemStack recipeOutput = recipe.getRecipeOutput();

            boolean areItemsEqual = recipeOutput.getItem().equals(output.getItem());
            boolean isMetaEqual = recipeOutput.getItemDamage() == output.getItemDamage();
            boolean isWildcard = output.getItemDamage() == OreDictionary.WILDCARD_VALUE;

            if (areItemsEqual && (isMetaEqual || isWildcard)) {
                toRemove.add(recipe.getRegistryName());
            }
        }

        for (ResourceLocation rl : toRemove) {
            r.register(new DummyRecipe(rl.getNamespace(), rl.getPath()));
        }
    }

    /**
     * Removes crafting recipes based on the namespace (mod ID) portion of their
     * {@link ResourceLocation}.
     * <p>
     * This method scans all registered crafting recipes and identifies any whose
     * registry name namespace contains the specified {@code group} string. This
     * allows bulk removal of recipes originating from a specific mod or from any
     * mod whose ID matches the provided substring.
     * <p>
     * Matching recipes are not deleted directly; instead, each is replaced with a
     * {@link DummyRecipe} registered under the same {@link ResourceLocation}. This
     * preserves registry integrity while effectively disabling the original recipe.
     *
     * @param registry The Forge registry event for {@link IRecipe} registration.
     * @param group    The namespace substring to match against recipe registry names.
     *                 Typically a mod ID (e.g. "minecraft", "thermalfoundation").
     */
    @SuppressWarnings("unchecked")
    public static void removeRecipeByModGroup(RegistryEvent.Register<IRecipe> registry, String group) {
        IForgeRegistryModifiable<IRecipe> r = (IForgeRegistryModifiable) registry.getRegistry();
        List<ResourceLocation> toRemove = new ArrayList<>();

        for (IRecipe recipe : r.getValuesCollection()) {
            String modGroup = recipe.getRegistryName().getNamespace();

            if (modGroup.contains(group)) {
                toRemove.add(recipe.getRegistryName());
            }
        }

        for (ResourceLocation rl : toRemove) {
            r.register(new DummyRecipe(rl.getNamespace(), rl.getPath()));
        }
    }

    /*
     * Original code from Terrafirmacraft's RecipeUtils.DummyRecipe (EUPL v1.2)
     * Modified to create DummyRecipe
     * Modified by xXjudeXx on 2026-04-07
     */
    private static class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

        private DummyRecipe(String domain, String id) {
            this.setRegistryName(domain, id);
        }

        public boolean matches(InventoryCrafting inventoryCrafting, World world) {
            return false;
        }

        public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
            return ItemStack.EMPTY;
        }

        public boolean canFit(int i, int i1) {
            return false;
        }

        public ItemStack getRecipeOutput() {
            return ItemStack.EMPTY;
        }
    }
}
