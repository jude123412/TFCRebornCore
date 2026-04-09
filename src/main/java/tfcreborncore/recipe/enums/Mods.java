package tfcreborncore.recipe.enums;

public enum Mods {

    THERMAL_EXPANSION("thermalexpansion"),
    THERMAL_FOUNDATION("thermalfoundation"),
    EX_NIHILO_CREATIO("exnihilocreatio"),
    TFC_TECH("tfctech"),
    MINECRAFT("minecraft"),
    STORAGE_DRAWERS("storagedrawers"),
    TERRAFIRMACRAFT("tfc"),
    BAUBLELICIOUS("baublelicious"),
    BAUBLES("baubles"),
    FIRMALIFE("firmalife"),
    TFC_METALLUM("tfcmetallum"),
    TFC_REBORN_CORE("tfcreborncore"),
    FORESTRY("forestry");

    public final String ID;

    Mods(String name) {
        this.ID = name;
    }

    public final String getName() {
        return ID;
    }
}
