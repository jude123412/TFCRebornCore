package tfcreborncore.proxy;

import net.dries007.tfc.util.fuel.FuelManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tfcreborncore.Tags;
import tfcreborncore.objects.RCItems;
import tfcreborncore.recipe.CompatManager;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

@Mod.EventBusSubscriber(modid = Tags.MODID)
public class Proxy {

    public void preLoad() {}

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RCItems.registerItems(event);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        CompatManager.loadOreDictionaries(event);
        CompatManager.loadCraftingRecipes(event);
        MinecraftRecipeManager.RECIPE_LIST.forEach(event.getRegistry()::register);
    }

    @SubscribeEvent
    public static void registerFurnaceFuels(FurnaceFuelBurnTimeEvent event) {
        ItemStack stack = event.getItemStack();

        // remove all old burn times
        if (event.getBurnTime() > 0) {
            event.setBurnTime(0);
        }

        // calculate the new based on TFC Fuel's
        if (FuelManager.isItemFuel(stack)) {
            int time = FuelManager.getFuel(stack).getAmount();
            float temp = FuelManager.getFuel(stack).getTemperature();

            event.setBurnTime((int) (time * Math.max(temp / 1000.0, 1.0)));
        }
    }
}
