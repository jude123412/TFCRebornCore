package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import tfcreborncore.recipe.ICompatModule;

public class ForestryCompat implements ICompatModule {

    @Override
    public List<String> dependancies() {
        return Arrays.asList(
                Mods.FORESTRY.getName());
    }
}
