package tfcreborncore.api.util;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import tfcreborncore.Tags;

public class RCUtility {

    public static int S = 20;

    @NotNull
    public static ResourceLocation rcId(@NotNull String path) {
        return new ResourceLocation(Tags.MODID, path);
    }
}
