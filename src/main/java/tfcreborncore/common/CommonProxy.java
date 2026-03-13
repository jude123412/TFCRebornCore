package tfcreborncore.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.MaterialRegistryEvent;
import tfcreborncore.Tags;
import tfcreborncore.api.unification.material.RCMaterials;
import tfcreborncore.api.util.RCLogger;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {

    public void preLoad() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        RCLogger.log.info("Registering Materials...");
        RCMaterials.registerMaterials();
    }

    @SubscribeEvent
    public static void createMaterialRegistry(MaterialRegistryEvent event) {
        RCLogger.log.info("Registering Material Event...");
        GregTechAPI.materialManager.createRegistry("tfcreborncore");
    }
}
