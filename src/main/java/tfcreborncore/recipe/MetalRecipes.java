package tfcreborncore.recipe;

import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
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
import tfcreborncore.objects.items.ItemRCMetal;
import tfcreborncore.objects.items.ItemRCTool;
import tfcreborncore.objects.items.ItemRCUniversalWeapon;
import tfcreborncore.objects.items.enums.ItemRCMetalType;
import tfcreborncore.objects.items.enums.ItemRCToolType;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;
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

                // Rackwheel Piece
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.RACKWHEEL_PIECE +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, rackwheelPiece, metal.getTier(), null, ForgeRule.UPSET_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST, ForgeRule.UPSET_LAST));

                ItemStack rod = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.ROD), 2);

                // Rod
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.ROD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, rod, metal.getTier(), null, ForgeRule.HIT_THIRD_LAST,
                        ForgeRule.DRAW_SECOND_LAST, ForgeRule.HIT_LAST));

                ItemStack strip = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.STRIP), 2);

                // Strip
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemTechMetal.ItemType.STRIP +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, strip, metal.getTier(), null, ForgeRule.HIT_ANY,
                        ForgeRule.HIT_ANY, ForgeRule.SHRINK_ANY));

            }
            if (metal.isToolMetal()) {
                IIngredient<ItemStack> ingredientIngotDouble = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)));

                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                ItemStack wireCutterHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.WIRE_CUTTER_HEAD));

                ItemStack unfinishedUniversalWeaponHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_UNIVERSAL_WEAPON_HEAD));

                // Wire Cutter Head
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + ItemRCMetalType.WIRE_CUTTER_HEAD +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngot, wireCutterHead, metal.getTier(), null, ForgeRule.BEND_ANY,
                        ForgeRule.HIT_SECOND_LAST, ForgeRule.DRAW_ANY));

                // Unfinished Universal Weapon Head
                r.register(new AnvilRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/working/" + "unfinished_universal_weapon_head" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientIngotDouble, unfinishedUniversalWeaponHead, metal.getTier(), null, ForgeRule.BEND_ANY,
                        ForgeRule.BEND_ANY, ForgeRule.DRAW_ANY));
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
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD));
                if (!miningHammerHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.HAMMER_HEAD))),
                            miningHammerHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Mining Hammer Head
                ItemStack miningHammerHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.MINING_HAMMER_HEAD));
                if (!miningHammerHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.MINING_HAMMER_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngotDouble,
                            IIngredient.of(new ItemStack(
                                    ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_MINING_HAMMER_HEAD))),
                            miningHammerHead, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Unfinished Excavator Head
                ItemStack excavatorHeadUnfinished = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD));
                if (!excavatorHeadUnfinished.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHOVEL_HEAD))),
                            excavatorHeadUnfinished, metal.getTier(), SmithingSkill.Type.TOOLS));

                // Excavator Head
                ItemStack excavatorHead = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.EXCAVATOR_HEAD));
                if (!excavatorHead.isEmpty())
                    r.register(new WeldingRecipe(
                            new ResourceLocation(Tags.MODID,
                                    "anvil/welding/" + ItemRCMetalType.EXCAVATOR_HEAD + "/" +
                                            metal.getRegistryName().getPath().toLowerCase()),
                            ingredientIngot,
                            IIngredient.of(new ItemStack(
                                    ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_EXCAVATOR_HEAD))),
                            excavatorHead, metal.getTier(), SmithingSkill.Type.TOOLS));
            }

            if (metal.isUsable()) {
                IIngredient<ItemStack> ingredientRackwheelPiece = IIngredient
                        .of(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)));

                ItemStack rackwheelHalf = new ItemStack(
                        ItemRCMetal.get(metal, ItemRCMetalType.RACKWHEEL_HALF));

                IIngredient<ItemStack> ingredientRackwheelHalf = IIngredient
                        .of(rackwheelHalf);

                ItemStack rackwheel = new ItemStack(
                        ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL));

                // Rackwheel Half
                r.register(new WeldingRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/welding/" + ItemRCMetalType.RACKWHEEL_HALF + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientRackwheelPiece,
                        ingredientRackwheelPiece,
                        rackwheelHalf, metal.getTier(), null));

                // Rackwheel
                r.register(new WeldingRecipe(
                        new ResourceLocation(Tags.MODID,
                                "anvil/welding/" + ItemTechMetal.ItemType.RACKWHEEL + "/" +
                                        metal.getRegistryName().getPath().toLowerCase()),
                        ingredientRackwheelHalf,
                        ingredientRackwheelHalf,
                        rackwheel, metal.getTier(), null));
            }
        }
    }

    public static void registerQuernRecipes(RegistryEvent.Register<QuernRecipe> event) {
        IForgeRegistry<QuernRecipe> r = event.getRegistry();
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isUsable()) {
                IIngredient<ItemStack> ingredientIngot = IIngredient
                        .of(new ItemStack(ItemMetal.get(metal, Metal.ItemType.INGOT)));

                ItemStack dust = new ItemStack(
                        ItemMetal.get(metal, Metal.ItemType.DUST));
                r.register(new QuernRecipe(ingredientIngot, dust).setRegistryName(Tags.MODID,
                        "quern/" + metal.getRegistryName().getPath().toLowerCase() + "/dust"));
            }
        }
    }

    public static void registerShapedSkillRecipe() {
        for (Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if (metal.isToolMetal()) {
                // Excavator
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.EXCAVATOR + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.EXCAVATOR).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', ItemRCMetal.get(metal, ItemRCMetalType.EXCAVATOR_HEAD).getDefaultInstance());

                // Mining Hammer
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.MINING_HAMMER + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.MINING_HAMMER).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H',
                        ItemRCMetal.get(metal, ItemRCMetalType.MINING_HAMMER_HEAD).getDefaultInstance());

                // Wire Cutter
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID,
                                "crafting/shaped/skill/" + ItemRCToolType.WIRE_CUTTER + "/" + metal),
                        ItemRCTool.get(metal, ItemRCToolType.WIRE_CUTTER).getDefaultInstance(),
                        "HS",
                        "S ",
                        'S', "stickWood",
                        'H',
                        ItemRCMetal.get(metal, ItemRCMetalType.WIRE_CUTTER_HEAD).getDefaultInstance());

                // Universal Weapon
                MinecraftRecipeManager.addShapedSkillRecipe(
                        new ResourceLocation(Tags.MODID, "crafting/shaped/skill/universal_weapon/" + metal),
                        ItemRCUniversalWeapon.get(metal).getDefaultInstance(),
                        "H",
                        "S",
                        'S', "stickWood",
                        'H', ItemRCMetal.get(metal, ItemRCMetalType.UNIVERSAL_WEAPON_HEAD).getDefaultInstance());

                // Universal Weapon Head
                MinecraftRecipeManager.addShapelessSkillRecipe(
                        new ResourceLocation(Tags.MODID, "crafting/shapeless/skill/universal_weapon_head/" + metal),
                        ItemRCMetal.get(metal, ItemRCMetalType.UNIVERSAL_WEAPON_HEAD).getDefaultInstance(),
                        "gemAmethyst",
                        ItemRCMetal.get(metal, ItemRCMetalType.UNFINISHED_UNIVERSAL_WEAPON_HEAD).getDefaultInstance());
            }
        }
    }
}
