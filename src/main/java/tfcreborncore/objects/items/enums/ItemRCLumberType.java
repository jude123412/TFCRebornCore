package tfcreborncore.objects.items.enums;

import java.util.function.Function;

import net.minecraft.item.Item;

import tfcreborncore.objects.items.ItemRCLumber;

public enum ItemRCLumberType {

    // Forestry's Trees
    LARCH(ModGroup.FORESTRY, 0xE4AD9F),
    TEAK(ModGroup.FORESTRY, 0x8E8A77),
    ACACIA(ModGroup.FORESTRY, 0xA0B58F),
    LIME(ModGroup.FORESTRY, 0xCEA375),
    CHESTNUT(ModGroup.FORESTRY, 0xD1CF70),
    WENGE(ModGroup.FORESTRY, 0x726B5F),
    BAOBAB(ModGroup.FORESTRY, 0xACC17C),
    SEQUOIA(ModGroup.FORESTRY, 0xA05145),
    KAPOK(ModGroup.FORESTRY, 0x8F933B),
    EBONY(ModGroup.FORESTRY, 0x4C4841),
    MAHOGANY(ModGroup.FORESTRY, 0xB77368),
    BALSA(ModGroup.FORESTRY, 0xD1CBC6),
    WILLOW(ModGroup.FORESTRY, 0xC7C96E),
    WALNUT(ModGroup.FORESTRY, 0x7C605D),
    GREENHEART(ModGroup.FORESTRY, 0x68A581),
    CHERRY(ModGroup.FORESTRY, 0xCCA145),
    MAHOE(ModGroup.FORESTRY, 0x6F8EA5),
    POPLAR(ModGroup.FORESTRY, 0xD3D35D),
    PALM(ModGroup.FORESTRY, 0xBC6F38),
    PAPAYA(ModGroup.FORESTRY, 0xE2C541),
    PINE(ModGroup.FORESTRY, 0xD1A047),
    PLUM(ModGroup.FORESTRY, 0xB7637F),
    MAPLE(ModGroup.FORESTRY, 0xB7722D),
    CITRUS(ModGroup.FORESTRY, 0xA8B520),
    GIGANTEUM(ModGroup.FORESTRY, 0xA05145),
    IPE(ModGroup.FORESTRY, 0x96573C),
    PADAUK(ModGroup.FORESTRY, 0xD1874B),
    COCOBOLO(ModGroup.FORESTRY, 0x912400),
    ZEBRAWOOD(ModGroup.FORESTRY, 0xC1975D),

    // Binnie's Trees
    APPLE(ModGroup.BINNIES, 0x75462E),
    FIG(ModGroup.BINNIES, 0xE58F20),
    BUTTERNUT(ModGroup.BINNIES, 0xEFC492),
    WHITEBEAM(ModGroup.BINNIES, 0xD6CDC5),
    ROWAN(ModGroup.BINNIES, 0xC9ABA9),
    HEMLOCK(ModGroup.BINNIES, 0xC9C08B),
    ASH(ModGroup.BINNIES, 0xEFC364),
    ALDER(ModGroup.BINNIES, 0xBF8754),
    BEECH(ModGroup.BINNIES, 0xEFA662),
    HAWTHORN(ModGroup.BINNIES, 0xEF945F),
    BANANA(ModGroup.BINNIES, 0x968376),
    YEW(ModGroup.BINNIES, 0xFCA26F),
    CYPRESS(ModGroup.BINNIES, 0xF9BC59),
    FIR(ModGroup.BINNIES, 0xD89829),
    HAZEL(ModGroup.BINNIES, 0xFCCAA1),
    HICKORY(ModGroup.BINNIES, 0xF4C48D),
    ELM(ModGroup.BINNIES, 0xFFB87F),
    ELDER(ModGroup.BINNIES, 0xF4AD73),
    HOLLY(ModGroup.BINNIES, 0xF9F7EF),
    HORNBEAM(ModGroup.BINNIES, 0xD6A44F),
    CEDAR(ModGroup.BINNIES, 0xED5E25),
    OLIVE(ModGroup.BINNIES, 0xC4B150),
    SWEETGUM(ModGroup.BINNIES, 0xE59D59),
    LOCUST(ModGroup.BINNIES, 0xD89F70),
    PEAR(ModGroup.BINNIES, 0xC68F65),
    MACLURA(ModGroup.BINNIES, 0xFFB521),
    BRAZILWOOD(ModGroup.BINNIES, 0x89345F),
    LOGWOOD(ModGroup.BINNIES, 0xAF392A),
    ROSEWOOD(ModGroup.BINNIES, 0x93190B),
    PURPLEHEART(ModGroup.BINNIES, 0x6D0640),
    IROKO(ModGroup.BINNIES, 0x913000),
    GINGKO(ModGroup.BINNIES, 0xF9EAC2),
    EUCALYPTUS(ModGroup.BINNIES, 0xF9AF84),
    BOX(ModGroup.BINNIES, 0xFCEEC9),
    SYZGIUM(ModGroup.BINNIES, 0xF9C7CE),
    PINKIVORY(ModGroup.BINNIES, 0xFF899F),
    EUCALYPTUS2(ModGroup.BINNIES, 0xFFFFFF),
    EUCALYPTUS3(ModGroup.BINNIES, 0xFFFFFF),
    CHERRY2(ModGroup.BINNIES, 0xFFFFFF),
    CINNAMON(ModGroup.BINNIES, 0xC48C58),

    // Immersive Engineering
    TREATED_WOOD(ModGroup.IMMERSIVE_ENGINEERING, 0x643A27);

    // ---------------------------------------------------------------------
    // Fields
    // ---------------------------------------------------------------------

    private final ModGroup group;
    private final int color;

    private final Function<ItemRCLumberType, ItemRCLumber> factory;

    // ---------------------------------------------------------------------
    // Constructor
    // ---------------------------------------------------------------------

    ItemRCLumberType(ModGroup group, int color) {
        this(group, color, ItemRCLumber::new);
    }

    ItemRCLumberType(ModGroup group, int color, Function<ItemRCLumberType, ItemRCLumber> factory) {
        this.group = group;
        this.color = color;
        this.factory = factory;
    }

    // ---------------------------------------------------------------------
    // Accessors
    // ---------------------------------------------------------------------

    public int localIndex() {
        int index = 0;
        for (ItemRCLumberType t : values()) {
            if (t == this) break;
            if (t.group == this.group) index++;
        }
        return index;
    }

    public int getWoodSpeciesId() {
        return localIndex() / 4;
    }

    public int getWoodMeta() {
        return localIndex() % 4;
    }

    public int getPlankSpeciesId() {
        return localIndex() / 16;
    }

    public int getPlankMeta() {
        return localIndex() % 16;
    }

    public ModGroup getModGroup() {
        return group;
    }

    public int getColor() {
        return color;
    }

    // ---------------------------------------------------------------------
    // Factory
    // ---------------------------------------------------------------------

    public static Item Create(ItemRCLumberType type) {
        return type.factory.apply(type);
    }

    public ItemRCLumber create() {
        return factory.apply(this);
    }
}
