package tfcreborncore.api.unification.ore;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.StoneType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.stone.BlockRockRaw;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.SoundType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tfcreborncore.api.unification.material.RCMaterials;

public class RCStoneTypes {
    @GameRegistry.ObjectHolder("tfc:granite")
    public static final Rock GRANITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:diorite")
    public static final Rock DIORITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:gabbro")
    public static final Rock GABBRO = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:shale")
    public static final Rock SHALE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:claystone")
    public static final Rock CLAYSTONE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:rocksalt")
    public static final Rock ROCKSALT = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:limestone")
    public static final Rock LIMESTONE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:conglomerate")
    public static final Rock CONGLOMERATE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:dolomite")
    public static final Rock DOLOMITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:chert")
    public static final Rock CHERT = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:chalk")
    public static final Rock CHALK = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:rhyolite")
    public static final Rock RHYOLITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:basalt")
    public static final Rock BASALT = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:andesite")
    public static final Rock ANDESITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:dacite")
    public static final Rock DACITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:quartzite")
    public static final Rock QUARTZITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:slate")
    public static final Rock SLATE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:phyllite")
    public static final Rock PHYLLITE = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:schist")
    public static final Rock SCHIST = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:gneiss")
    public static final Rock GNEISS = (Rock) Helpers.getNull();

    @GameRegistry.ObjectHolder("tfc:marble")
    public static final Rock MARBLE = (Rock) Helpers.getNull();


    private static int startId = 12;
    private static int endId = 512;

    public static void registerOreTypes() {
        StoneType RAW_GRANITE = new StoneType(getMetaItemId(), "raw_granite", SoundType.STONE, RCOrePrefix.oreRawGranite,
                RCMaterials.RawGraniteStone,
                () -> BlockRockRaw.get(GRANITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(GRANITE, Rock.Type.RAW),
                true);

        StoneType RAW_DIORITE = new StoneType(getMetaItemId(), "raw_diorite", SoundType.STONE, RCOrePrefix.oreRawDiorite,
                RCMaterials.RawDioriteStone,
                () -> BlockRockRaw.get(DIORITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(DIORITE, Rock.Type.RAW),
                true);

        StoneType RAW_GABBRO = new StoneType(getMetaItemId(), "raw_gabbro", SoundType.STONE, RCOrePrefix.oreRawGabbro,
                RCMaterials.RawGabbroStone,
                () -> BlockRockRaw.get(GABBRO, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(GABBRO, Rock.Type.RAW),
                true);

        StoneType RAW_SHALE = new StoneType(getMetaItemId(), "raw_shale", SoundType.STONE, RCOrePrefix.oreRawShale,
                RCMaterials.RawShaleStone,
                () -> BlockRockRaw.get(SHALE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(SHALE, Rock.Type.RAW),
                true);

        StoneType RAW_CLAYSTONE = new StoneType(getMetaItemId(), "raw_claystone", SoundType.STONE, RCOrePrefix.oreRawClaystone,
                RCMaterials.RawClaystoneStone,
                () -> BlockRockRaw.get(CLAYSTONE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(CLAYSTONE, Rock.Type.RAW),
                true);

        StoneType RAW_ROCKSALT = new StoneType(getMetaItemId(), "raw_rocksalt", SoundType.STONE, RCOrePrefix.oreRawRocksalt,
                RCMaterials.RawRocksaltStone,
                () -> BlockRockRaw.get(ROCKSALT, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(ROCKSALT, Rock.Type.RAW),
                true);

        StoneType RAW_LIMESTONE = new StoneType(getMetaItemId(), "raw_limestone", SoundType.STONE, RCOrePrefix.oreRawLimestone,
                RCMaterials.RawLimestoneStone,
                () -> BlockRockRaw.get(LIMESTONE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(LIMESTONE, Rock.Type.RAW),
                true);

        StoneType RAW_CONGLOMERATE = new StoneType(getMetaItemId(), "raw_conglomerate", SoundType.STONE, RCOrePrefix.oreRawConglomerate,
                RCMaterials.RawConglomerateStone,
                () -> BlockRockRaw.get(CONGLOMERATE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(CONGLOMERATE, Rock.Type.RAW),
                true);

        StoneType RAW_DOLOMITE = new StoneType(getMetaItemId(), "raw_dolomite", SoundType.STONE, RCOrePrefix.oreRawDolomite,
                RCMaterials.RawDolomiteStone,
                () -> BlockRockRaw.get(DOLOMITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(DOLOMITE, Rock.Type.RAW),
                true);

        StoneType RAW_CHERT = new StoneType(getMetaItemId(), "raw_chert", SoundType.STONE, RCOrePrefix.oreRawChert,
                RCMaterials.RawChertStone,
                () -> BlockRockRaw.get(CHERT, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(CHERT, Rock.Type.RAW),
                true);

        StoneType RAW_CHALK = new StoneType(getMetaItemId(), "raw_chalk", SoundType.STONE, RCOrePrefix.oreRawChalk,
                RCMaterials.RawChalkStone,
                () -> BlockRockRaw.get(CHALK, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(CHALK, Rock.Type.RAW),
                true);

        StoneType RAW_RHYOLITE = new StoneType(getMetaItemId(), "raw_rhyolite", SoundType.STONE, RCOrePrefix.oreRawRhyolite,
                RCMaterials.RawRhyoliteStone,
                () -> BlockRockRaw.get(RHYOLITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(RHYOLITE, Rock.Type.RAW),
                true);

        StoneType RAW_BASALT = new StoneType(getMetaItemId(), "raw_basalt", SoundType.STONE, RCOrePrefix.oreRawBasalt,
                RCMaterials.RawBasaltStone,
                () -> BlockRockRaw.get(BASALT, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(BASALT, Rock.Type.RAW),
                true);

        StoneType RAW_ANDESITE = new StoneType(getMetaItemId(), "raw_andesite", SoundType.STONE, RCOrePrefix.oreRawAndesite,
                RCMaterials.RawAndesiteStone,
                () -> BlockRockRaw.get(ANDESITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(ANDESITE, Rock.Type.RAW),
                true);

        StoneType RAW_DACITE = new StoneType(getMetaItemId(), "raw_dacite", SoundType.STONE, RCOrePrefix.oreRawDacite,
                RCMaterials.RawDaciteStone,
                () -> BlockRockRaw.get(DACITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(DACITE, Rock.Type.RAW),
                true);

        StoneType RAW_QUARTZITE = new StoneType(getMetaItemId(), "raw_quartzite", SoundType.STONE, RCOrePrefix.oreRawQuartzite,
                RCMaterials.RawQuartziteStone,
                () -> BlockRockRaw.get(QUARTZITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(QUARTZITE, Rock.Type.RAW),
                true);

        StoneType RAW_SLATE = new StoneType(getMetaItemId(), "raw_slate", SoundType.STONE, RCOrePrefix.oreRawSlate,
                RCMaterials.RawSlateStone,
                () -> BlockRockRaw.get(SLATE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(SLATE, Rock.Type.RAW),
                true);

        StoneType RAW_PHYLLITE = new StoneType(getMetaItemId(), "raw_phyllite", SoundType.STONE, RCOrePrefix.oreRawPhyllite,
                RCMaterials.RawPhylliteStone,
                () -> BlockRockRaw.get(PHYLLITE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(PHYLLITE, Rock.Type.RAW),
                true);

        StoneType RAW_SCHIST = new StoneType(getMetaItemId(), "raw_schist", SoundType.STONE, RCOrePrefix.oreRawSchist,
                RCMaterials.RawSchistStone,
                () -> BlockRockRaw.get(SCHIST, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(SCHIST, Rock.Type.RAW),
                true);

        StoneType RAW_GNEISS = new StoneType(getMetaItemId(), "raw_gneiss", SoundType.STONE, RCOrePrefix.oreRawGeneiss,
                RCMaterials.RawGneissStone,
                () -> BlockRockRaw.get(GNEISS, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(GNEISS, Rock.Type.RAW),
                true);

        StoneType RAW_MARBLE = new StoneType(getMetaItemId(), "raw_marble", SoundType.STONE, RCOrePrefix.oreRawMarble,
                RCMaterials.RawMarbleStone,
                () -> BlockRockRaw.get(MARBLE, Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw &&
                        state.getBlock() == BlockRockRaw.get(MARBLE, Rock.Type.RAW),
                true);
    }

    private static int getMetaItemId() {
        if (startId < endId) {
            return startId++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
