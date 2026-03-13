package tfcreborncore.api.unification.ore;

import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import tfcreborncore.api.unification.material.info.RCMaterialIconType;

public class RCOrePrefix {

    public static final OrePrefix oreSmall = new OrePrefix("oreSmall", -1, null,
            RCMaterialIconType.oreSmallTFC,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.ORE));

    public static final OrePrefix orePoor = new OrePrefix("orePoor", -1, null,
            RCMaterialIconType.orePoorTFC,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.ORE));

    public static final OrePrefix oreNormal = new OrePrefix("oreNormal", -1, null,
            RCMaterialIconType.oreNormalTFC,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.ORE));

    public static final OrePrefix oreRich = new OrePrefix("oreRich", -1, null,
            RCMaterialIconType.oreRichTFC,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.ORE));

    public static void init() {
        MetaItems.addOrePrefix(oreSmall);
        MetaItems.addOrePrefix(orePoor);
        MetaItems.addOrePrefix(oreNormal);
        MetaItems.addOrePrefix(oreRich);
    }
}
