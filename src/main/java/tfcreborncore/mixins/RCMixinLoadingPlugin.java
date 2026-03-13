package tfcreborncore.mixins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.Name;

import org.jetbrains.annotations.Nullable;

import zone.rong.mixinbooter.ILateMixinLoader;

@Name("RCMixinLoadingPlugin")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.SortingIndex(1001)
public class RCMixinLoadingPlugin implements IFMLLoadingPlugin, ILateMixinLoader {

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        List<String> configs = new ArrayList<>();
        configs.add("mixins.tfcreborncore.json");
        return configs;
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return ILateMixinLoader.super.shouldMixinConfigQueue(mixinConfig);
    }
}
