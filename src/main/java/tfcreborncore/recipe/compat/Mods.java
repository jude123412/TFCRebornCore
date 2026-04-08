package tfcreborncore.recipe.compat;

public enum Mods {

    THERMAL_EXPANSION("thermalexpansion"),
    EX_NIHILO_CREATIO("exnihilocreatio"),
    TFC_TECH("tfctech"),
    MINECRAFT("minecraft"),
    STORAGE_DRAWERS("storagedrawers"),
    TERRAFIRMACRAFT("tfc"),
    FIRMALIFE("firmalife"),
    FORESTRY("forestry");

    public final String ID;

    Mods(String name) {
        this.ID = name;
    }

    public final String getName() {
        return ID;
    }
}
