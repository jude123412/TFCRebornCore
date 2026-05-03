package tfcreborncore.loot_tables;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import tfcreborncore.recipe.enums.Mods;

public enum LootTables {

    INFESTED_SEQUOIA(Mods.TFC_REBORN_CORE.ID, "structures/chests/infested_sequoia", Mods.FIRMALIFE),
    PRIMITIVE_HUT(Mods.TFC_REBORN_CORE.ID, "structures/chests/primitive_hut");

    private final String location;
    private final String name;
    private final Mods[] dependancies;

    LootTables(String location, String name, Mods... dependancies) {
        this.location = location;
        this.name = name;
        this.dependancies = dependancies;
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(location, name);
    }

    public boolean shouldRegister() {
        for (Mods dep : dependancies) {
            if (!Loader.isModLoaded(dep.ID)) {
                return false;
            }
        }
        return true;
    }
}
