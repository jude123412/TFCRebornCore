package tfcreborncore.client;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import tfcreborncore.common.CommonProxy;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    public void preLoad() {
        super.preLoad();
    }
}
