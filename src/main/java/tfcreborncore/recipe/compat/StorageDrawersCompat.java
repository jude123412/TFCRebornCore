package tfcreborncore.recipe.compat;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import tfcreborncore.recipe.ICompatModule;
import tfcreborncore.recipe.RecipeHelper;
import tfcreborncore.recipe.enums.Mods;
import tfcreborncore.recipe.manager.MinecraftRecipeManager;

public class StorageDrawersCompat implements ICompatModule {

    @Override
    public List<String> dependencies() {
        return Arrays.asList(
                Mods.STORAGE_DRAWERS.ID,
                Mods.FRAMED_COMPACTING_DRAWERS.ID,
                Mods.TFC_TECH.ID,
                Mods.TERRAFIRMACRAFT.ID);
    }

    @Override
    public void registerOreDictionaries(RegistryEvent.Register<IRecipe> r) {
        OreDictionary.registerOre("drawerFramed",
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", OreDictionary.WILDCARD_VALUE));
    }

    @Override
    public void registerRecipeRemoval(FMLPostInitializationEvent r) {
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.STORAGE_DRAWERS.ID);
        MinecraftRecipeManager.removeRecipeByModGroup(Mods.FRAMED_COMPACTING_DRAWERS.ID);
    }

    @Override
    public void registerCraftingRecipe(RegistryEvent.Register<IRecipe> r) {
        // Upgrade Template
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/upgrade_template"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"),
                "WWW",
                "WFW",
                "WWW",
                'W', "stickWood",
                'F', "drawerFramed");

        // Storage Upgrade I
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_upgrade_1"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage"),
                "WWW",
                "CUC",
                "WWW",
                'W', "stickWood",
                'C', "ingotCopper",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Storage Upgrade II
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_upgrade_2"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 1),
                "WWW",
                "RUR",
                "WWW",
                'W', "stickWood",
                'R', "ingotRoseGold",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage"));

        // Storage Upgrade III
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_upgrade_3"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 2),
                "WWW",
                "SUS",
                "WWW",
                'W', "stickWood",
                'S', "ingotSterlingSilver",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 1));

        // Storage Upgrade IV
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_upgrade_4"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 3),
                "WWW",
                "BUB",
                "WWW",
                'W', "stickWood",
                'B', "ingotBronze",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 2));

        // Storage Upgrade V
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_upgrade_5"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 4),
                "WWW",
                "BUB",
                "WWW",
                'W', "stickWood",
                'B', "ingotBismuthBronze",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_storage", 3));

        // Storage Downgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/storage_downgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_one_stack"),
                "WWW",
                "CUC",
                "WWW",
                'W', "stickWood",
                'C', "cobblestone",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Status Upgrade (I)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/status_upgrade_1"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_status", 0),
                "WWW",
                "RUT",
                "WWW",
                'W', "stickWood",
                'R', "dustRedstone",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"),
                'T', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone_torch"));

        // Status Upgrade (II)
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/status_upgrade_2"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_status", 1),
                "WWW",
                "TUT",
                "WWW",
                'W', "stickWood",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"),
                'T', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "redstone_torch"));

        // Void Upgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/void_upgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_void"),
                "WWW",
                "BUB",
                "WWW",
                'W', "stickWood",
                'B', "ingotBlackBronze",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Conversion Upgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/conversion_upgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_conversion"),
                "LWL",
                "WUW",
                "LWL",
                'L', "gemLapis",
                'W', "stickWood",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Redstone Upgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_upgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_redstone", 0),
                "WWW",
                "RUR",
                "WWW",
                'W', "stickWood",
                'R', "dustRedstone",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Redstone Max Upgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_max_upgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_redstone", 1),
                "WWW",
                "TUR",
                "WWW",
                'W', "stickWood",
                'R', "dustRedstone",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"),
                'T', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "comparator"));

        // Redstone Min Upgrade
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/redstone_min_upgrade"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_redstone", 2),
                "WWW",
                "RUT",
                "WWW",
                'W', "stickWood",
                'R', "dustRedstone",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"),
                'T', RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "comparator"));

        // Drawer Key
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/drawer_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                "SG ",
                " G ",
                " U ",
                'S', "stripGold",
                'G', "ingotGold",
                'U', RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "upgrade_template"));

        // Concealment Key
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/concealment_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "shroud_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                "gemEnderEye");

        // Personal Key
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/personal_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "shroud_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                "gemDiamond");

        // Quantify Key
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/quantify_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "shroud_key"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "writable_book"));

        // Packing Tape
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/packing_tape"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "tape"),
                "SPS",
                "PRP",
                "SPS",
                'S', "slimeball",
                'P', "paper",
                'R', "stickAnyBronze");

        // Drawer Key Button
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/drawer_key_button"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "keybutton", 0),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stone_button"));

        // Concealment Key Button
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/concealment_key_button"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "keybutton", 1),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stone_button"));

        // Personal Key Button
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/personal_key_button"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "keybutton", 2),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stone_button"));

        // Quantify Key Button
        MinecraftRecipeManager.addShapelessRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/quantify_key_button"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "keybutton", 3),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "drawer_key"),
                RecipeHelper.getItemStack(Mods.MINECRAFT.ID, "stone_button"));

        // Framing Table
        MinecraftRecipeManager.addShapedDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framing_table"),
                1,
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "framingtable"),
                "  S",
                "PPP",
                "P P",
                'P', "plankWood",
                'S', "saw");

        // Framed Drawer
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_drawer"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 0),
                "WWW",
                "WCW",
                "WWW",
                'W', "stickWood",
                'C', "chest");

        // Framed Drawer 1x2
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_drawer_1x2"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 1),
                "WWW",
                "CWC",
                "WWW",
                'W', "stickWood",
                'C', "chest");

        // Framed Drawer 2x2
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_drawer_2x2"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 2),
                "CWC",
                "WWW",
                "CWC",
                'W', "stickWood",
                'C', "chest");

        // Framed Half Drawer 1x2
        MinecraftRecipeManager.addShapelessDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_half_drawer_1x2"),
                1,
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 3, 2),
                "saw",
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 1));

        // Framed Half Drawer 2x2
        MinecraftRecipeManager.addShapelessDamageRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_half_drawer_2x2"),
                1,
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 4, 2),
                "saw",
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customdrawers", 2));

        // Framed Trim
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_trim"),
                RecipeHelper.getItemStack(Mods.STORAGE_DRAWERS.ID, "customtrim"),
                "PWP",
                "WWW",
                "PWP",
                'W', "stickWood",
                'P', "plankWood");

        // Framed Compacting Drawer
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_compact_drawer"),
                RecipeHelper.getItemStack(Mods.FRAMED_COMPACTING_DRAWERS.ID, "framed_compact_drawer"),
                "WWW",
                "PFP",
                "WIW",
                'W', "stickWood",
                'P', RecipeHelper.getItemStack("minecraft", "piston"),
                'F', "drawerFramed",
                'I', "ingotIron");

        // Framed Drawer Controller
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_drawer_controller"),
                RecipeHelper.getItemStack(Mods.FRAMED_COMPACTING_DRAWERS.ID, "framed_drawer_controller"),
                "WWW",
                "CFC",
                "WGW",
                'W', "stickWood",
                'C', RecipeHelper.getItemStack("minecraft", "comparator"),
                'F', "drawerFramed",
                'G', "ingotGold");

        // Framed Slave
        MinecraftRecipeManager.addShapedRecipe(
                new ResourceLocation(Mods.TFC_REBORN_CORE.ID, "crafting/shaped/framed_slave"),
                RecipeHelper.getItemStack(Mods.FRAMED_COMPACTING_DRAWERS.ID, "framed_slave"),
                "WWW",
                "CFC",
                "WIW",
                'W', "stickWood",
                'C', RecipeHelper.getItemStack("minecraft", "comparator"),
                'F', "drawerFramed",
                'I', "ingotIron");
    }
}
