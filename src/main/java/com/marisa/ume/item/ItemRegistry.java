package com.marisa.ume.item;

import com.marisa.ume.Ume;
import com.marisa.ume.block.BlockRegistry;
import com.marisa.ume.group.StoryGroup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * 物品注册
 */

public class ItemRegistry {
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ume.MODID);

    //方块物品
    public static RegistryObject<Item> ARMOR_MAKE_BLOCK = ITEMS.register("armor_make_block", () -> new BlockItem(BlockRegistry.ARMOR_MAKE_BLOCK.get(), new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ARMOR_WIDGET_BLOCK = ITEMS.register("armor_widget_block", () -> new BlockItem(BlockRegistry.ARMOR_WIDGET_BLOCK.get(), new Item.Properties().tab(StoryGroup.MAIN)));
    
}
