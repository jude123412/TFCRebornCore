package tfcreborncore.recipe.enums;

public enum Mods {

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
    THERMAL_FOUNDATION("thermalfoundation"),
    THERMAL_DYNAMICS("thermaldynamics"),
    THERMAL_INNOVATION("thermalinnovation"),
    ENDER_IO("enderio"),
    IMMERSIVE_ENGINEERING("immersiveengineering"),
    BINNIE_CORE("binniecore"),
    BINNIE_BOTANY("botany"),
    BINNIE_EXTRA_BEES("extrabees"),
    BINNIE_EXTRA_TREES("extratrees"),
    BINNIE_GENETICS("genetics"),
    FORESTRY("forestry");

    public final String ID;

    Mods(String name) {
        this.ID = name;
    }

    public final String getName() {
        return ID;
    }
}
