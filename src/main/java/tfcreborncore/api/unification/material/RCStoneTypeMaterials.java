package tfcreborncore.api.unification.material;

import static tfcreborncore.api.util.RCUtility.rcId;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;

public class RCStoneTypeMaterials {

    private static int startId = 0;
    private static int endId = 21;

    public static void register() {
        RCMaterials.RawGraniteStone = new Material.Builder(getMetaItemId(), rcId("raw_granite_stone"))
                .dust()
                .color(0x71706F)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawDioriteStone = new Material.Builder(getMetaItemId(), rcId("raw_diorite_stone"))
                .dust()
                .color(0x505050)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawGabbroStone = new Material.Builder(getMetaItemId(), rcId("raw_gabbro_stone"))
                .dust()
                .color(0x747471)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawShaleStone = new Material.Builder(getMetaItemId(), rcId("raw_shale_stone"))
                .dust()
                .color(0x282729)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawClaystoneStone = new Material.Builder(getMetaItemId(), rcId("raw_claystone_stone"))
                .dust()
                .color(0x997E64)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawRocksaltStone = new Material.Builder(getMetaItemId(), rcId("raw_rocksalt_stone"))
                .dust()
                .color(0xC8B7B5)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawLimestoneStone = new Material.Builder(getMetaItemId(), rcId("raw_limestone_stone"))
                .dust()
                .color(0xA2977D)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawConglomerateStone = new Material.Builder(getMetaItemId(), rcId("raw_conglomerate_stone"))
                .dust()
                .color(0x867456)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawDolomiteStone = new Material.Builder(getMetaItemId(), rcId("raw_dolomite_stone"))
                .dust()
                .color(0x605A60)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawChertStone = new Material.Builder(getMetaItemId(), rcId("raw_chert_stone"))
                .dust()
                .color(0x6A4432)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawChalkStone = new Material.Builder(getMetaItemId(), rcId("raw_chalk_stone"))
                .dust()
                .color(0xC0C0BC)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawRhyoliteStone = new Material.Builder(getMetaItemId(), rcId("raw_rhyolite_stone"))
                .dust()
                .color(0x78746B)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawBasaltStone = new Material.Builder(getMetaItemId(), rcId("raw_basalt_stone"))
                .dust()
                .color(0x242628)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawAndesiteStone = new Material.Builder(getMetaItemId(), rcId("raw_andesite_stone"))
                .dust()
                .color(0x666A6C)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawDaciteStone = new Material.Builder(getMetaItemId(), rcId("raw_dacite_stone"))
                .dust()
                .color(0x757876)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawQuartziteStone = new Material.Builder(getMetaItemId(), rcId("raw_quartzite_stone"))
                .dust()
                .color(0xAC949A)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawSlateStone = new Material.Builder(getMetaItemId(), rcId("raw_slate_stone"))
                .dust()
                .color(0x857967)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawPhylliteStone = new Material.Builder(getMetaItemId(), rcId("raw_phyllite_stone"))
                .dust()
                .color(0x736761)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawSchistStone = new Material.Builder(getMetaItemId(), rcId("raw_schist_stone"))
                .dust()
                .color(0x6E755C)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawGneissStone = new Material.Builder(getMetaItemId(), rcId("raw_gneiss_stone"))
                .dust()
                .color(0x7E7B6A)
                .iconSet(MaterialIconSet.ROUGH)
                .build();

        RCMaterials.RawMarbleStone = new Material.Builder(getMetaItemId(), rcId("raw_marble_stone"))
                .dust()
                .color(0xB0A6A3)
                .iconSet(MaterialIconSet.ROUGH)
                .build();
    }

    private static int getMetaItemId() {
        if (startId < endId) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
