package tfcreborncore.common;

import gregtech.api.unification.material.event.PostMaterialEvent;
import gregtech.api.unification.stack.ItemMaterialInfo;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.event.MaterialRegistryEvent;
import tfcreborncore.Tags;
import tfcreborncore.api.unification.material.RCMaterials;
import tfcreborncore.api.unification.ore.RCStoneTypes;
import tfcreborncore.api.util.RCLogger;
import tfcreborncore.loaders.recipe.mod.gregtech.RCRecipeManager;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class CommonProxy {

    public void preLoad() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event) {
        RCLogger.log.info("Registering Materials...");
        RCMaterials.registerMaterials();
    }

    @SubscribeEvent
    public static void registerPostMaterials(PostMaterialEvent event) {
        RCLogger.log.info("Registering Stone Types...");
        RCStoneTypes.registerOreTypes();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        RCLogger.log.info("Registering recipes...");
        MinecraftForge.EVENT_BUS.post(new GregTechAPI.RegisterEvent<>(null, ItemMaterialInfo.class));

        RCRecipeManager.load();
    }

    @SubscribeEvent
    public static void createMaterialRegistry(MaterialRegistryEvent event) {
        RCLogger.log.info("Registering Material Event...");
        GregTechAPI.materialManager.createRegistry("tfcreborncore");
    }
}
