package tfcreborncore.recipe.enums;

import net.minecraft.util.ResourceLocation;

public enum LootTables {

    INFESTED_SEQUOIA(Mods.TFC_REBORN_CORE.ID, "chests/infested_sequoia"),
    PRIMITIVE_HUT(Mods.TFC_REBORN_CORE.ID, "chests/primitive_hut");

    private final String location;
    private final String name;

    LootTables(String location, String name) {
        this.location = location;
        this.name = name;
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(location, name);
    }
}
