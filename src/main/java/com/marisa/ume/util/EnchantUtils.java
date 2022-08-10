package com.marisa.ume.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

/**
 *
 */

public class EnchantUtils {


    public static int getMaxLv(ItemStack stack, CompoundTag compound) {
        Enchantment enchantment = null;
        Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
        for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
            ResourceLocation registryName = EnchantmentHelper.getEnchantmentId(entry.getKey());
            if (registryName != null && registryName.equals(EnchantmentHelper.getEnchantmentId(compound))) {
                enchantment = entry.getKey();
            }
        }
        return enchantment == null ? 1 : enchantment.getMaxLevel();
    }

    public static void changeLv(ItemStack stack, boolean up, int value) {
        ListTag listEnchant = stack.getEnchantmentTags();
        for (int i = 0; i < listEnchant.size(); i++) {
            CompoundTag compound = listEnchant.getCompound(i);
            if (EnchantUtils.getMaxLv(stack, compound) > 1) {
                int lv = EnchantmentHelper.getEnchantmentLevel(compound);
                EnchantmentHelper.setEnchantmentLevel(compound, up ? lv + value : Math.max(1, lv - value));
            }
        }
    }

}
