package tfcreborncore.proxy;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.objects.recipe.CraftingRecipeManager;
import tfcreborncore.recipe.MetalRecipes;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class Proxy {

    public void preLoad() {}

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RCItems.registerItems(event);
    }

    @SubscribeEvent
    public static void registerAnvilRecipe(RegistryEvent.Register<AnvilRecipe> event) {
        MetalRecipes.registerAnvilRecipes(event);
    }

    @SubscribeEvent
    public static void registerWeldingRecipe(RegistryEvent.Register<WeldingRecipe> event) {
        MetalRecipes.registerWeldingRecipes(event);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        MetalRecipes.registerShapedSkillRecipe();

        CraftingRecipeManager.SKILL_RECIPES.forEach(event.getRegistry()::register);
    }
}
