package tfcreborncore.recipe.enums;

public enum ThermalToolMaterialTypes {

    TIN(0),
    INVAR(1),
    ELECTRUM(2),
    SIGNALUM(3),
    ENDERIUM(4);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final int meta;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ThermalToolMaterialTypes(int meta) {
        this.meta = meta;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public String getName() {
        return name().toLowerCase();
    }

    public int getMeta() {
        return this.meta;
    }
}
