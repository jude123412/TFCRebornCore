package tfcreborncore.recipe.enums;

public enum OreProcessingTypes {

    NATIVE_COPPER("native_gold", "copper", "gold"),
    NATIVE_GOLD("native_silver", "gold", "silver"),
    NATIVE_PLATINUM("native_iridium", "platinum", "iridium"),
    HEMATITE("garnierite", "wrought_iron", "nickel"),
    NATIVE_SILVER("galena", "silver", "lead"),
    CASSITERITE("magnetite", "tin", "wrought_iron"),
    GALENA("native_silver", "lead", "silver"),
    BISMUTHINITE("galena", "bismuth", "lead"),
    GARNIERITE("magnetite", "nickel", "wrought_iron"),
    MALACHITE("bismuthinite", "copper", "bismuth"),
    MAGNETITE("tetrahedrite", "wrought_iron", "copper"),
    LIMONITE("wolframite", "wrought_iron", "tungsten"),
    SPHALERITE("bismuthinite", "zinc", "bismuth"),
    TETRAHEDRITE("cassiterite", "copper", "tin"),
    STIBNITE("spodumene", "antimony", "lithium"),
    SPODUMENE("galena", "lithium", "lead"),
    NATIVE_IRIDIUM("native_platinum", "iridium", "platinum"),
    NATIVE_ARDITE("cobaltite", "ardite", "cobalt"),
    NATIVE_OSMIUM("zircon", "osmium", "zirconium"),
    BAUXITE("sphalerite", "aluminium", "zinc"),
    WOLFRAMITE("rutile", "tungsten", "titanium"),
    COBALTITE("native_ardite", "cobalt", "ardite"),
    RUTILE("wolframite", "titanium", "tungsten"),
    THORIANITE("galena", "thorium", "lead"),
    PYROLUSITE("magnesite", "manganese", "magnesium"),
    MAGNESITE("pyrolusite", "magnesium", "manganese"),
    ZIRCON("native_silver", "zirconium", "silver");

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final String productA;
    private final String productB;
    private final String productC;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    OreProcessingTypes(String productA, String productB, String productC) {
        this.productA = productA;
        this.productB = productB;
        this.productC = productC;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public String getPrimaryName() {
        return this.name().toLowerCase();
    }

    public String getProductA() {
        return this.productA;
    }

    public String getProductB() {
        return this.productB;
    }

    public String getProductC() {
        return this.productC;
    }
}
