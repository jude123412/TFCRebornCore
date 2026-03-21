package tfcreborncore.recipe;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.RCItemMetal;
import tfctech.objects.items.metal.ItemTechMetal;

@SuppressWarnings({ "ConstantConditions", "unused" })
public class MetalRecipes {

    public static void registerAnvilRecipes(RegistryEvent.Register<AnvilRecipe> event) {
        IForgeRegistry<AnvilRecipe> r = event.getRegistry();
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));
                ItemStack rackwheelPiece = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE));
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                (metal.getRegistryName().getPath()).toLowerCase() + "_" +
                                        ItemTechMetal.ItemType.RACKWHEEL_PIECE),
                        ingredientIngot, rackwheelPiece, metal.getTier(), null, ForgeRule.DRAW_THIRD_LAST,
                        ForgeRule.HIT_SECOND_LAST, ForgeRule.DRAW_LAST));
            }
        }
    }

    public static void registerWeldingRecipes(RegistryEvent.Register<WeldingRecipe> event) {
        IForgeRegistry<WeldingRecipe> r = event.getRegistry();

        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable() && metal.isToolMetal()) {
                IIngredient<ItemStack> ingredientIngotDouble = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)));

                // Unfinished Mining Hammer Head
                ItemStack miningHammerHeadUnfinished = new ItemStack(
                        RCItemMetal.get(metal, RCItemMetal.RCMetalItemType.UNFINISHED_MINING_HAMMER_HEAD));
                if (!miningHammerHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() + "_" +
                                            RCItemMetal.RCMetalItemType.UNFINISHED_MINING_HAMMER_HEAD),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.HAMMER_HEAD))),
                            miningHammerHeadUnfinished, metal.getTier(), null));

                // Mining Hammer Head
                ItemStack miningHammerHead = new ItemStack(
                        RCItemMetal.get(metal, RCItemMetal.RCMetalItemType.MINING_HAMMER_HEAD));
                if (!miningHammerHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() +
                                            "_" + RCItemMetal.RCMetalItemType.MINING_HAMMER_HEAD),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(
                                    RCItemMetal.get(metal, RCItemMetal.RCMetalItemType.UNFINISHED_MINING_HAMMER_HEAD))),
                            miningHammerHead, metal.getTier(), null));

                // Excavator Head
                ItemStack excavatorHead = new ItemStack(
                        RCItemMetal.get(metal, RCItemMetal.RCMetalItemType.EXCAVATOR_HEAD));
                if (!excavatorHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() + "_" +
                                            RCItemMetal.RCMetalItemType.EXCAVATOR_HEAD),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHOVEL_HEAD))),
                            excavatorHead, metal.getTier(), null));

            }
        }
    }
}
