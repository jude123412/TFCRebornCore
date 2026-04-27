package tfcreborncore.recipe.enums;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.minecraft.util.ResourceLocation;

public enum OreProcessingTypes {

    NATIVE_COPPER("native_gold", "copper", "gold", 0xEA824A),
    NATIVE_GOLD("native_silver", "gold", "silver", 0xD9A517),
    NATIVE_PLATINUM("native_iridium", "platinum", "iridium", 0xBCD3D8),
    HEMATITE("garnierite", "wrought_iron", "nickel", 0xC96266),
    NATIVE_SILVER("galena", "silver", "lead", 0xCBC9E0),
    CASSITERITE("magnetite", "tin", "wrought_iron", 0x827C66),
    GALENA("native_silver", "lead", "silver", 0x6C5A72),
    BISMUTHINITE("galena", "bismuth", "lead", 0x265D2A),
    GARNIERITE("magnetite", "nickel", "wrought_iron", 0x5A664B),
    MALACHITE("bismuthinite", "copper", "bismuth", 0x78C9AF),
    MAGNETITE("tetrahedrite", "wrought_iron", "copper", 0x7F8E8A),
    LIMONITE("wolframite", "wrought_iron", "tungsten", 0x4D2F27),
    SPHALERITE("bismuthinite", "zinc", "bismuth", 0xDBDADA),
    TETRAHEDRITE("cassiterite", "copper", "tin", 0x938CA5),
    STIBNITE("spodumene", "antimony", "lithium", 0xA0A7D3),
    SPODUMENE("galena", "lithium", "lead", 0xECD3FF),
    NATIVE_IRIDIUM("native_platinum", "iridium", "platinum", 0x868BC9),
    NATIVE_ARDITE("cobaltite", "ardite", "cobalt", 0xFF8517),
    NATIVE_OSMIUM("zircon", "osmium", "zirconium", 0x908FA8),
    BAUXITE("sphalerite", "aluminium", "zinc", 0xD7652F),
    WOLFRAMITE("rutile", "tungsten", "titanium", 0x4F2C25),
    COBALTITE("native_ardite", "cobalt", "ardite", 0x87BCF2),
    RUTILE("wolframite", "titanium", "tungsten", 0x915638),
    THORIANITE("galena", "thorium", "lead", 0x3D2B34),
    PYROLUSITE("magnesite", "manganese", "magnesium", 0x585A5E),
    MAGNESITE("pyrolusite", "magnesium", "manganese", 0xB7B2AD),
    ZIRCON("native_silver", "zirconium", "silver", 0x696915);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final String productA;
    private final String productB;
    private final String productC;
    private final int oreColor;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    OreProcessingTypes(String productA, String productB, String productC, int oreColor) {
        this.productA = productA;
        this.productB = productB;
        this.productC = productC;
        this.oreColor = oreColor;
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

    public int getOreColor() {
        return this.oreColor;
    }

    public Ore getOre() {
        return TFCRegistries.ORES.getValue(new ResourceLocation(Mods.TERRAFIRMACRAFT.ID, name().toLowerCase()));
    }

    public Metal getMetal() {
        return TFCRegistries.METALS.getValue(new ResourceLocation(Mods.TERRAFIRMACRAFT.ID, productB));
    }
}
