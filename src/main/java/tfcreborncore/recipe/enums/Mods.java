package tfcreborncore.recipe.enums;

public enum Mods {

    EX_NIHILO_CREATIO("exnihilocreatio"),
    TFC_TECH("tfctech"),
    MINECRAFT("minecraft"),
    STORAGE_DRAWERS("storagedrawers"),
    FRAMED_COMPACTING_DRAWERS("framedcompactdrawers"),
    TERRAFIRMACRAFT("tfc"),
    BAUBLELICIOUS("baublelicious"),
    BUBBLES("baubles"),
    FIRMALIFE("firmalife"),
    TFC_METALLUM("tfcmetallum"),
    TFC_REBORN_CORE("tfcreborncore"),
    THERMAL_FOUNDATION("thermalfoundation"),
    THERMAL_DYNAMICS("thermaldynamics"),
    THERMAL_INNOVATION("thermalinnovation"),
    IMMERSIVE_ENGINEERING("immersiveengineering"),
    BINNIE_CORE("binniecore"),
    BINNIE_BOTANY("botany"),
    BINNIE_EXTRA_BEES("extrabees"),
    BINNIE_EXTRA_TREES("extratrees"),
    BINNIE_GENETICS("genetics"),
    OPEN_GLIDER("openglider"),
    WATER_FLASKS("waterflasks"),
    APPLIED_ENERGISTICS_2("appliedenergistics2"),
    TFC_DECORATION("tfc_decoration"),
    TFC_ROCKS_PLUS("tfc_rocksplus"),
    FORESTRY("forestry");

    public final String ID;

    Mods(String name) {
        this.ID = name;
    }

    public final String getName() {
        return ID;
    }
}
