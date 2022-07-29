package com.marisa.ume.util;

import com.marisa.ume.smith.c.Skill;
import com.marisa.ume.smith.e.ESkill;
import com.marisa.ume.smith.e.EWidget;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;

import java.util.*;

/**
 *
 */

public class WidgetUtils {

    private final static String FLAG = "ume_widget_flag";
    private final static String ID = "ume_widget";

    public static boolean have(ItemStack stack) {
        return stack.getOrCreateTag().getInt(FLAG) == 1;
    }

    public static void add(ItemStack stack, int[] ints) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.put(FLAG, IntTag.valueOf(1));
        tag.put(ID, new IntArrayTag(ints));
    }

    public static int[] getAll(ItemStack stack) {
        if (stack.getTag() == null) return null;
        int[] ints = stack.getTag().getIntArray(ID);
        return ints.length > 0 ? ints : null;
    }

    public static List<Skill> getSkills(ItemStack stack) {
        if (stack.getTag() == null) return new ArrayList<>();
        int[] ints = stack.getTag().getIntArray(ID);
        return ints.length > 0 ? build(Arrays.stream(ints).filter(i -> i != 0).mapToObj(i -> EWidget.getById(i).getWidget().getSkill()).toList()) : new ArrayList<>();
    }

    public static List<Skill> build(List<Skill> listSkill) {
        List<Skill> list = new ArrayList<>();
        for (Skill skill : listSkill) {
            boolean add = true;
            for (int i = 0; i < list.size(); i++) {
                Skill sk = list.get(i);
                if (skill.getId() == sk.getId()) {
                    list.set(i, ESkill.getById(sk.getId()).lv(sk.getLv() + skill.getLv()));
                    add = false;
                }
            }
            if (add) {
                list.add(skill);
            }
        }
        list.sort(Comparator.comparing(Skill::getLv).reversed());
        return list;
    }

    public static void clear(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.remove(FLAG);
        tag.remove(ID);
    }

    public static void showTips(ItemStack stack, List<Component> toolTip) {
        if (have(stack)) {
            int index = 0;
            toolTip.add(++index, MutableComponent.create(new TranslatableContents("1")).withStyle(ChatFormatting.DARK_RED));
            toolTip.add(++index, MutableComponent.create(new TranslatableContents("2")));
        }
    }
}
