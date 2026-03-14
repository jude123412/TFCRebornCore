package tfcreborncore.api.util;

import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import tfcreborncore.Tags;

public class RCUtility {

    @NotNull
    public static ResourceLocation rcId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }
}
