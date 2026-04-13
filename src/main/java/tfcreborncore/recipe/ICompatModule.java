package tfcreborncore.recipe;

import java.util.List;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public interface ICompatModule {

    List<String> dependencies();

    default void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {}

    /**
     * Performs late-phase recipe removal and mutation during {@link FMLPostInitializationEvent}.
     * <p>
     * This hook is invoked after all mods have completed their registry phases and
     * initialization stages, but before the final recipe lists are cached by systems
     * such as JEI or other post-load processors. In many modpacks and SRG production
     * environments, {@code FMLPostInitializationEvent} is the earliest phase where the
     * crafting recipe registry is both populated and stable enough for safe mutation.
     * <p>
     * Implementations should use this method to perform compatibility-layer cleanup,
     * such as removing unwanted or conflicting crafting recipes from other mods. The
     * provided helper methods {@code MinecraftRecipeManager.removeRecipeByOutput(ItemStack)}
     * and {@code MinecraftRecipeManager.removeRecipeByModGroup(String)} are designed
     * specifically for this phase: they safely replace matched recipes with
     * {@code DummyRecipe} instances while preserving all recipes registered by
     * TFCRebornCore itself.
     * <p>
     * These helpers rely on the fact that the recipe registry is not reliably stable
     * during {@code RegistryEvent.Register<IRecipe>} (too early) and may be rebuilt or
     * wrapped by other mods during {@code FMLLoadCompleteEvent} (too late). Running
     * removal during {@code FMLPostInitializationEvent} ensures that:
     * <ul>
     * <li>all external recipes have been registered and are visible,</li>
     * <li>TFCRebornCore's own recipes remain intact and protected,</li>
     * <li>CraftTweaker and other late injectors have completed their additions,</li>
     * <li>the registry has not yet been finalized or cached by JEI,</li>
     * <li>recipe replacement via {@code IForgeRegistryModifiable} is effective in both
     * development and production (SRG) environments.</li>
     * </ul>
     * <p>
     * This method is <b>not</b> intended for registering new Forge registry entries.
     * All new recipes, blocks, items, and other registry objects must still be added
     * during their respective {@code RegistryEvent.Register} phases. Only mutation or
     * removal of existing crafting recipes should occur here.
     *
     * @param r The {@link FMLPostInitializationEvent} fired once all mods have finished
     *          initialization and the crafting recipe registry is safe to modify.
     */
    default void registerRecipeRemoval(FMLPostInitializationEvent r) {}

    default void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {}

    default void registerItemMetal(FMLPostInitializationEvent r) {}

    default void registerBarrelRecipes(IForgeRegistry<BarrelRecipe> r) {}

    default void registerAnvilRecipes(IForgeRegistry<AnvilRecipe> r) {}

    default void registerWeldingRecipes(IForgeRegistry<WeldingRecipe> r) {}

    default void registerQuernRecipes(IForgeRegistry<QuernRecipe> r) {}

    default void registerSieveRecipes(FMLPostInitializationEvent r) {}

    default void registerSagMillRecipes(FMLPostInitializationEvent r) {}

    default void registerCrusherRecipes(FMLPostInitializationEvent r) {}

    default boolean areRecipesLoadable() {
        for (String dep : dependencies()) {
            if (!Loader.isModLoaded(dep)) {
                return false;
            }
        }
        return true;
    }
}
