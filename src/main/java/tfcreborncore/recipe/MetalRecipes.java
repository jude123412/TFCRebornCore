package tfcreborncore.recipe;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import tfcreborncore.Tags;
import tfcreborncore.objects.items.ItemRCToolHead;
import tfcreborncore.objects.recipe.CraftingRecipeManager;
import tfcreborncore.objects.tools.ItemRCTool;
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
                        ingredientIngot, rackwheelPiece, metal.getTier(), null, ForgeRule.UPSET_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST, ForgeRule.UPSET_LAST));
            }
        }
    }

    public static void registerWeldingRecipes(RegistryEvent.Register<WeldingRecipe> event) {
        IForgeRegistry<WeldingRecipe> r = event.getRegistry();

        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable() && metal.isToolMetal()) {
                IIngredient<ItemStack> ingredientIngotDouble = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)));

                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                // Unfinished Mining Hammer Head
                ItemStack miningHammerHeadUnfinished = new ItemStack(
                        ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.UNFINISHED_MINING_HAMMER_HEAD));
                if (!miningHammerHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() + "_" +
                                            ItemRCToolHead.ItemType.UNFINISHED_MINING_HAMMER_HEAD),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.HAMMER_HEAD))),
                            miningHammerHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Mining Hammer Head
                ItemStack miningHammerHead = new ItemStack(
                        ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.MINING_HAMMER_HEAD));
                if (!miningHammerHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() +
                                            "_" + ItemRCToolHead.ItemType.MINING_HAMMER_HEAD),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(
                                    ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.UNFINISHED_MINING_HAMMER_HEAD))),
                            miningHammerHead, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Unfinished Excavator Head
                ItemStack excavatorHeadUnfinished = new ItemStack(
                        ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.UNFINISHED_EXCAVATOR_HEAD));
                if (!excavatorHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() + "_" +
                                            ItemRCToolHead.ItemType.UNFINISHED_EXCAVATOR_HEAD),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHOVEL_HEAD))),
                            excavatorHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Excavator Head
                ItemStack excavatorHead = new ItemStack(
                        ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.EXCAVATOR_HEAD));
                if (!excavatorHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    metal.getRegistryName().getPath().toLowerCase() + "_" +
                                            ItemRCToolHead.ItemType.EXCAVATOR_HEAD),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(
                                    ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.UNFINISHED_EXCAVATOR_HEAD))),
                            excavatorHead, metal.getTier(), SmithingSkill.Type.TOOLS));

            }
        }
    }

    public static void registerShapedSkillRecipe() {
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isToolMetal()) {
                CraftingRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID, "metal/tool/" + ItemRCTool.ItemType.EXCAVATOR + "/" + metal),
                        ItemRCTool.get(metal, ItemRCTool.ItemType.EXCAVATOR).getDefaultInstance(),
                        " H ",
                        " S ",
                        "   ",
                        'S', "stickWood",
                        'H', ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.EXCAVATOR_HEAD).getDefaultInstance());

                CraftingRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "metal/tool/" + ItemRCTool.ItemType.MINING_HAMMER + "/" + metal),
                        ItemRCTool.get(metal, ItemRCTool.ItemType.MINING_HAMMER).getDefaultInstance(),
                        " H ",
                        " S ",
                        "   ",
                        'S', "stickWood",
                        'H',
                        ItemRCToolHead.get(metal, ItemRCToolHead.ItemType.MINING_HAMMER_HEAD).getDefaultInstance());
            }
        }
    }
}
