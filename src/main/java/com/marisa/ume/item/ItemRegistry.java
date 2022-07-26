package com.marisa.ume.item;

import com.marisa.ume.Ume;
import com.marisa.ume.block.BlockRegistry;
import com.marisa.ume.group.StoryGroup;
import com.marisa.ume.item.mould.ArmorMould;
import com.marisa.ume.item.widget.Widget;
import com.marisa.ume.smith.e.EMould;
import com.marisa.ume.smith.e.ESkill;
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

    //方块
    public static RegistryObject<Item> ARMOR_MAKE_BLOCK = ITEMS.register("armor_make_block", () -> new BlockItem(BlockRegistry.ARMOR_MAKE_BLOCK.get(), new Item.Properties().tab(StoryGroup.MAIN)));
    public static RegistryObject<Item> ARMOR_WIDGET_BLOCK = ITEMS.register("armor_widget_block", () -> new BlockItem(BlockRegistry.ARMOR_WIDGET_BLOCK.get(), new Item.Properties().tab(StoryGroup.MAIN)));

    //模具
    public static RegistryObject<Item> ZOMBIE_HEAD = ITEMS.register("zombie_head", () -> new ArmorMould(1, 1, 1001, EMould::f2B, ESkill.POWER.lv(3), ESkill.HEALTH.lv(-3)));
    public static RegistryObject<Item> ZOMBIE_BODY = ITEMS.register("zombie_body", () -> new ArmorMould(1, 2, 2001, EMould::f1A, new int[]{2, 0, 0}, EMould::f2A, ESkill.POWER.lv(1), ESkill.HEALTH.lv(-2)));
    public static RegistryObject<Item> ZOMBIE_LEG = ITEMS.register("zombie_leg", () -> new ArmorMould(1, 3, 3001, EMould::f1A, new int[]{1, 1, 0}, EMould::f2A, ESkill.POWER.lv(1), ESkill.HEALTH.lv(-2)));
    public static RegistryObject<Item> ZOMBIE_FOOT = ITEMS.register("zombie_foot", () -> new ArmorMould(1, 4, 4001, EMould::f2B, ESkill.POWER.lv(1), ESkill.HEALTH.lv(-1)));
    public static RegistryObject<Item> RABBIT_HEAD = ITEMS.register("rabbit_head", () -> new ArmorMould(1, 1, 1002, EMould::f2B, ESkill.SPEED.lv(1)));
    public static RegistryObject<Item> RABBIT_BODY = ITEMS.register("rabbit_body", () -> new ArmorMould(1, 2, 2002, EMould::f1A, new int[]{1, 1, 0}, EMould::f2A, ESkill.SPEED.lv(1)));
    public static RegistryObject<Item> RABBIT_LEG = ITEMS.register("rabbit_leg", () -> new ArmorMould(1, 3, 3002, EMould::f1A, new int[]{2, 0, 0}, EMould::f2A, ESkill.SPEED.lv(1)));
    public static RegistryObject<Item> RABBIT_FOOT = ITEMS.register("rabbit_foot", () -> new ArmorMould(1, 4, 4002, EMould::f1B, new int[]{3, 1, 0}));

    //饰品
    public static RegistryObject<Item> WIDGET_HEALTH = ITEMS.register("widget_health", () -> new Widget(1001, 1, ESkill.HEALTH.lv(1)));
    public static RegistryObject<Item> WIDGET_POWER = ITEMS.register("widget_power", () -> new Widget(2001, 2, ESkill.POWER.lv(1)));
    public static RegistryObject<Item> WIDGET_SPEED = ITEMS.register("widget_speed", () -> new Widget(3001, 2, ESkill.SPEED.lv(1)));
    
}
