package tfcreborncore.api.unification.ore;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import tfcreborncore.api.unification.material.info.RCMaterialIconType;

public class RCOrePrefix {

    public static final OrePrefix ingotDouble = new OrePrefix("ingotDouble", M * 2, null,
            MaterialIconType.ingotDouble,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    public static final OrePrefix scrap = new OrePrefix("scrap", M * 1, null,
            RCMaterialIconType.scrap,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.DUST));

    public static final OrePrefix sheet = new OrePrefix("sheet", M * 2, null,
            RCMaterialIconType.sheet,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    public static final OrePrefix sheetDobule = new OrePrefix("sheetDouble", M * 4, null,
            RCMaterialIconType.sheetDouble,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    public static void init() {
        MetaItems.addOrePrefix(ingotDouble);
        MetaItems.addOrePrefix(scrap);
        MetaItems.addOrePrefix(sheet);
        MetaItems.addOrePrefix(sheetDobule);
    }
}
