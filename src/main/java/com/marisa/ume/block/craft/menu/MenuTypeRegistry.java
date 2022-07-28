package com.marisa.ume.block.craft.menu;

import com.marisa.ume.Ume;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


/**
 * MenuTypeRegistry
 */

public class MenuTypeRegistry {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Ume.MODID);

    public static final RegistryObject<MenuType<ArmorMakeMenu>> ARMOR_MAKE_MENU = MENU_TYPES.register("armor_make_menu_type", () -> IForgeMenuType.create(ArmorMakeMenu::new));
    public static final RegistryObject<MenuType<ArmorWidgetMenu>> ARMOR_WIDGET_MENU = MENU_TYPES.register("armor_widget_menu_type", () -> IForgeMenuType.create(ArmorWidgetMenu::new));
}
