package tfcreborncore.recipe.manager.builders;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class NBTItemStackBuilder {

    private final String namespace;
    private final String path;
    private int meta = 0;
    private int size = 1;
    private NBTTagCompound nbt;

    public NBTItemStackBuilder(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public NBTItemStackBuilder setMeta(int meta) {
        this.meta = meta;
        return this;
    }

    public NBTItemStackBuilder setStackSize(int size) {
        this.size = size;
        return this;
    }

    public NBTItemStackBuilder setNBT(String key, int value) {
        if (this.nbt == null) {
            this.nbt = new NBTTagCompound();
        }
        this.nbt.setInteger(key, value);
        return this;
    }

    public ItemStack build() {
        ResourceLocation location = new ResourceLocation(namespace, path);
        Item item = ForgeRegistries.ITEMS.getValue(location);

        if (item == null || item == Items.AIR)
            return ItemStack.EMPTY;

        ItemStack stack = new ItemStack(item, size, meta);

        if (nbt != null)
            stack.setTagCompound(nbt);

        return stack;
    }
}
