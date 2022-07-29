package com.marisa.ume.util;

import com.marisa.ume.item.mould.ArmorMould;
import com.marisa.ume.smith.c.Skill;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */

public class UmeUtils {
    
    public static final UUID UUID_ATTACK_SPEED = UUID.randomUUID();
    public static final UUID UUID_MAX_HEALTH = UUID.randomUUID();
    public static final UUID UUID_ARMOR = UUID.randomUUID();
    public static final UUID UUID_MOVEMENT_SPEED = UUID.randomUUID();
    public static final UUID UUID_LUCK = UUID.randomUUID();

    public static boolean canMake(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem && !MakeUtils.have(stack);
    }

    public static boolean isArmorMould(ItemStack stack) {
        return stack.getItem() instanceof ArmorMould;
    }
    
    public static List<Skill> getSkills(ItemStack... stacks) {
        List<Skill> list = new ArrayList<>();
        for (ItemStack stack : stacks) {
            if (!MakeUtils.have(stack)) continue;
            list.addAll(MakeUtils.getSkills(stack));
            list.addAll(WidgetUtils.getSkills(stack));
        }
        return WidgetUtils.build(list);
    }

}
