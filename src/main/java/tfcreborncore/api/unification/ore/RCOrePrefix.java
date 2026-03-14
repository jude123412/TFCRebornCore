package tfcreborncore.api.unification.ore;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.items.MetaItems;
import tfcreborncore.api.unification.material.RCMaterials;
import tfcreborncore.api.unification.material.info.RCMaterialIconType;

public class RCOrePrefix {

    // New Material Ore Prefixes
    public static final OrePrefix ingotDouble = new OrePrefix("ingotDouble", M * 2, null,
            MaterialIconType.ingotDouble,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    public static final OrePrefix scrap = new OrePrefix("scrap", M * 1, null,
            RCMaterialIconType.scrap,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.DUST) && mat.hasProperty(PropertyKey.INGOT));

    public static final OrePrefix sheet = new OrePrefix("sheet", M * 2, null,
            RCMaterialIconType.sheet,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    public static final OrePrefix sheetDobule = new OrePrefix("sheetDouble", M * 4, null,
            RCMaterialIconType.sheetDouble,
            ENABLE_UNIFICATION,
            mat -> mat.hasProperty(PropertyKey.INGOT));

    // New Ore Ore Prefixes
    public static final OrePrefix oreRawGranite = new OrePrefix("oreRawGranite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawDiorite = new OrePrefix("oreRawDiorite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawGabbro = new OrePrefix("oreRawGabbro", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawShale = new OrePrefix("oreRawShale", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawClaystone = new OrePrefix("oreRawClaystone", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawRocksalt = new OrePrefix("oreRawRocksalt", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawLimestone = new OrePrefix("oreRawLimestone", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawConglomerate = new OrePrefix("oreRawConglomerate", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawDolomite = new OrePrefix("oreRawDolomite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawChert = new OrePrefix("oreRawChert", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawChalk = new OrePrefix("oreRawChalk", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawRhyolite = new OrePrefix("oreRawRhyolite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawBasalt = new OrePrefix("oreRawBasalt", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawAndesite = new OrePrefix("oreRawAndesite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawDacite = new OrePrefix("oreRawDacite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawQuartzite = new OrePrefix("oreRawQuartzite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawSlate = new OrePrefix("oreRawSlate", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawPhyllite = new OrePrefix("oreRawPhyllite", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawSchist = new OrePrefix("oreRawSchist", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawGeneiss = new OrePrefix("oreRawGeneiss", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static final OrePrefix oreRawMarble = new OrePrefix("oreRawMarble", -1, null,
            MaterialIconType.ore,
            OrePrefix.Flags.ENABLE_UNIFICATION,
            OrePrefix.Conditions.hasOreProperty);

    public static void init() {
        RCOrePrefix.oreRawGranite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawGraniteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawGraniteStone)));
        RCOrePrefix.oreRawDiorite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawDioriteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawDioriteStone)));
        RCOrePrefix.oreRawGabbro.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawGabbroStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawGabbroStone)));
        RCOrePrefix.oreRawShale.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawShaleStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawShaleStone)));
        RCOrePrefix.oreRawClaystone.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawClaystoneStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawClaystoneStone)));
        RCOrePrefix.oreRawRocksalt.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawRocksaltStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawRocksaltStone)));
        RCOrePrefix.oreRawLimestone.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawLimestoneStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawLimestoneStone)));
        RCOrePrefix.oreRawConglomerate.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawConglomerateStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawConglomerateStone)));
        RCOrePrefix.oreRawDolomite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawDolomiteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawDolomiteStone)));
        RCOrePrefix.oreRawChert.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawChertStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawChertStone)));
        RCOrePrefix.oreRawChalk.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawChalkStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawChalkStone)));
        RCOrePrefix.oreRawRhyolite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawRhyoliteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawRhyoliteStone)));
        RCOrePrefix.oreRawBasalt.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawBasaltStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawBasaltStone)));
        RCOrePrefix.oreRawAndesite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawAndesiteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawAndesiteStone)));
        RCOrePrefix.oreRawDacite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawDaciteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawDaciteStone)));
        RCOrePrefix.oreRawQuartzite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawQuartziteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawQuartziteStone)));
        RCOrePrefix.oreRawSlate.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawSlateStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawSlateStone)));
        RCOrePrefix.oreRawPhyllite.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawPhylliteStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawPhylliteStone)));
        RCOrePrefix.oreRawSchist.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawSchistStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawSchistStone)));
        RCOrePrefix.oreRawGeneiss.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawGneissStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawGneissStone)));
        RCOrePrefix.oreRawMarble.addSecondaryMaterial(
                new MaterialStack(RCMaterials.RawMarbleStone,
                        OrePrefix.dust.getMaterialAmount(RCMaterials.RawMarbleStone)));

        MetaItems.addOrePrefix(ingotDouble);
        MetaItems.addOrePrefix(scrap);
        MetaItems.addOrePrefix(sheet);
        MetaItems.addOrePrefix(sheetDobule);
    }
}
