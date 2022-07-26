package com.marisa.ume.util;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;

import java.util.List;

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
