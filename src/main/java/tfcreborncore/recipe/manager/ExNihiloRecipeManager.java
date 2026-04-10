package tfcreborncore.recipe.manager;

import net.minecraft.item.ItemStack;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.util.ItemInfo;

public class ExNihiloRecipeManager {

    /**
     * Removes every Sieve recipe currently registered in Ex Nihilo.
     * <p>
     * This performs a full reset by clearing the internal registry returned by
     * {@code ExNihiloRegistryManager.SIEVE_REGISTRY.getRegistry()}. All default
     * Ex Nihilo sieve drops and any mod-added sieve recipes are removed.
     * Use this when replacing or rebuilding the entire sieve drop table.
     */
    public static void removeAllSieveRecipes() {
        ExNihiloRegistryManager.SIEVE_REGISTRY.getRegistry().clear();
    }

    /**
     * Registers a new Sieve recipe for all mesh tiers (String, Flint, Iron, Diamond)
     * using an OreDictionary name as the input material.
     * <p>
     * The base chance is applied to the String mesh, and each successive mesh tier
     * increases the chance by the provided multiplier. Four separate entries are
     * registered, one for each mesh level:
     * <ul>
     * <li>Mesh level 1 — String mesh</li>
     * <li>Mesh level 2 — Flint mesh</li>
     * <li>Mesh level 3 — Iron mesh</li>
     * <li>Mesh level 4 — Diamond mesh</li>
     * </ul>
     * This allows higher-tier meshes to yield better drop rates automatically.
     *
     * @param oreName    The OreDictionary key representing the block being sieved.
     * @param stack      The output item produced by the sieve.
     * @param chance     The base drop chance for the String mesh.
     * @param multiplier The amount added to the chance for each higher mesh tier.
     */
    public static void registerSieveRecipe(String oreName, ItemStack stack, float chance, float multiplier) {
        float string = chance;
        float flint = string + multiplier;
        float iron = flint + multiplier;
        float diamond = iron + multiplier;

        ExNihiloRegistryManager.SIEVE_REGISTRY.register(oreName, new ItemInfo(stack), string, 1);
        ExNihiloRegistryManager.SIEVE_REGISTRY.register(oreName, new ItemInfo(stack), flint, 2);
        ExNihiloRegistryManager.SIEVE_REGISTRY.register(oreName, new ItemInfo(stack), iron, 3);
        ExNihiloRegistryManager.SIEVE_REGISTRY.register(oreName, new ItemInfo(stack), diamond, 4);
    }
}
