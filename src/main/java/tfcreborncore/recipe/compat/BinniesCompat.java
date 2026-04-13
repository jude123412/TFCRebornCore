package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class BinniesCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.BINNIE_CORE.ID,
                Mods.BINNIE_BOTANY.ID,
                Mods.BINNIE_EXTRA_BEES.ID,
                Mods.BINNIE_EXTRA_TREES.ID,
                Mods.BINNIE_GENETICS.ID);
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BINNIE_CORE.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BINNIE_BOTANY.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BINNIE_EXTRA_BEES.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BINNIE_EXTRA_TREES.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.BINNIE_GENETICS.ID);
    }
}
