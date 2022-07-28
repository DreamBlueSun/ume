package com.marisa.ume.util;

import com.marisa.ume.smith.c.Skill;
import com.marisa.ume.smith.e.ESkill;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MakeUtils {

    private final static String FLAG = "ume_make_flag";
    private final static String ID_SLOT = "ume_make_slots";
    private final static String ID_SKILL = "ume_make_skills";
    private final static String ID_SKILL_LV = "ume_make_skills_lv";

    public static boolean have(ItemStack stack) {
        return stack.getOrCreateTag().getInt(FLAG) == 1;
    }

    public static void add(ItemStack stack, int[] ints, List<Skill> sKills) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.put(FLAG, IntTag.valueOf(1));
        tag.put(ID_SLOT, new IntArrayTag(ints));
        int[] ints1 = sKills.stream().mapToInt(Skill::getId).toArray();
        tag.put(ID_SKILL, new IntArrayTag(ints1));
        int[] ints2 = sKills.stream().mapToInt(Skill::getLv).toArray();
        tag.put(ID_SKILL_LV, new IntArrayTag(ints2));
    }

    public static int[] getSlots(ItemStack stack) {
        if (stack.getTag() == null) return null;
        int[] ints = stack.getTag().getIntArray(ID_SLOT);
        return ints.length > 0 ? ints : null;
    }

    public static List<Skill> getSkills(ItemStack stack) {
        if (stack.getTag() == null) return null;
        int[] ints1 = stack.getTag().getIntArray(ID_SKILL);
        int[] ints2 = stack.getTag().getIntArray(ID_SKILL_LV);
        if (ints1.length > 0 && ints2.length > 0) {
            List<Skill> list = new ArrayList<>();
            for (int i = 0; i < ints1.length; i++) {
                list.add(ESkill.getById(ints1[i]).lv(ints2[i]));
            }
            return list;
        }
        return null;
    }

    public static void clear(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.remove(FLAG);
        tag.remove(ID_SLOT);
        tag.remove(ID_SKILL);
        tag.remove(ID_SKILL_LV);
    }

    public static void showTips(ItemStack stack, List<Component> toolTip) {
        if (have(stack)) {
            int index = 0;
            toolTip.add(++index, MutableComponent.create(new TranslatableContents("1")).withStyle(ChatFormatting.DARK_RED));
            toolTip.add(++index, MutableComponent.create(new TranslatableContents("2")));
        }
    }
}
