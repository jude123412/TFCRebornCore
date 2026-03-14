package tfcreborncore.api.unification.material;

import gregtech.api.unification.material.Material;
import tfcreborncore.api.unification.ore.RCOrePrefix;

public class RCMaterials {

    // Stone Type Stones
    public static Material RawGraniteStone;
    public static Material RawDioriteStone;
    public static Material RawGabbroStone;
    public static Material RawShaleStone;
    public static Material RawClaystoneStone;
    public static Material RawRocksaltStone;
    public static Material RawLimestoneStone;
    public static Material RawConglomerateStone;
    public static Material RawDolomiteStone;
    public static Material RawChertStone;
    public static Material RawChalkStone;
    public static Material RawRhyoliteStone;
    public static Material RawBasaltStone;
    public static Material RawAndesiteStone;
    public static Material RawDaciteStone;
    public static Material RawQuartziteStone;
    public static Material RawSlateStone;
    public static Material RawPhylliteStone;
    public static Material RawSchistStone;
    public static Material RawGneissStone;
    public static Material RawMarbleStone;

    public static void registerMaterials() {
        /*
         * Ranges 0-20
         * All Stone Types go here
         */
        RCStoneTypeMaterials.register();

        /*
         * TFC: Reborn Core OrePrefix Registry
         */
        RCOrePrefix.init();
    }
}
