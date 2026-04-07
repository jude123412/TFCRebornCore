package tfcreborncore.recipe.compat;

public enum Mods {

    THERMAL_EXPANSION("thermalexpansion"),
    EX_NIHILO_CREATIO("exnihilocreatio"),
    FORESTRY("forestry");

    private final String name;

    Mods(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }
}
