package tfcreborncore.loaders.recipe.handlers;

public class RCRecipeHandlerList {

    public static void registerRecipeHandlers() {
        RCMaterialRecipeHandler.register();
        RCOreRecipeHandler.register();
    }
}
