package tfcreborncore.recipe.mods;

import net.minecraft.item.ItemStack;

import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.util.ItemInfo;

public class ExNihiloHelper {

    public static void removeAllSieveRecipes() {
        ExNihiloRegistryManager.SIEVE_REGISTRY.getRegistry().clear();
    }

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
