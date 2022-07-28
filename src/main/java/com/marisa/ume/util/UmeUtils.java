package com.marisa.ume.util;

import com.marisa.ume.item.mould.ArmorMould;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 *
 */

public class UmeUtils {

    public static boolean canMake(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && !MakeUtils.have(stack);
    }

    public static boolean isArmorMould(ItemStack stack) {
        return stack.getItem() instanceof ArmorMould;
    }

}
