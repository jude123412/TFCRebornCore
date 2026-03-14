package tfcreborncore.loaders.recipe.handlers;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.loaders.recipe.handlers.OreRecipeHandler;
import tfcreborncore.api.unification.ore.RCOrePrefix;

public class RCOreRecipeHandler {

    public static void register() {
        RCOrePrefix.oreRawGranite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawDiorite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawGabbro.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawShale.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawClaystone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawRocksalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawLimestone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawConglomerate.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawDolomite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawChert.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawChalk.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawRhyolite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawBasalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawAndesite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawDacite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawQuartzite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawSlate.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawPhyllite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawSchist.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawGeneiss.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        RCOrePrefix.oreRawMarble.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
    }
}
