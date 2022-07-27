package com.marisa.ume.smith.e;

import com.marisa.ume.item.ItemRegistry;
import com.marisa.ume.item.mould.ArmorMould;
import com.marisa.ume.item.mould.Mould;
import com.marisa.ume.smith.c.SKill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum EMould {

    UNKNOWN(0, null),
    ZOMBIE_HEAD(1001, (ArmorMould) ItemRegistry.ZOMBIE_HEAD.get()),
    ZOMBIE_BODY(2001, (ArmorMould) ItemRegistry.ZOMBIE_BODY.get()),
    ZOMBIE_LEG(3001, (ArmorMould) ItemRegistry.ZOMBIE_LEG.get()),
    ZOMBIE_FOOT(4001, (ArmorMould) ItemRegistry.ZOMBIE_FOOT.get()),
    RABBIT_HEAD(1002, (ArmorMould) ItemRegistry.RABBIT_HEAD.get()),
    RABBIT_BODY(2002, (ArmorMould) ItemRegistry.RABBIT_BODY.get()),
    RABBIT_LEG(3002, (ArmorMould) ItemRegistry.RABBIT_LEG.get()),
    RABBIT_FOOT(4002, (ArmorMould) ItemRegistry.RABBIT_FOOT.get());

    private final int id;
    private final Mould mould;

    EMould(int id, Mould mould) {
        this.id = id;
        this.mould = mould;
    }

    public int getId() {
        return id;
    }

    public Mould getMould() {
        return mould;
    }

    public static EMould getById(int id) {
        for (EMould value : EMould.values()) {
            if (value.id == id) return value;
        }
        return UNKNOWN;
    }

    public static int[] f1A(int[] slots) {
        for (int i = 0; i < slots.length; i++) {
            slots[i] -= new Random(slots[i] + 1).nextInt();
        }
        return Arrays.stream(slots).sorted().distinct().toArray();
    }

    public static int[] f1B(int[] slots) {
        for (int i = 0; i < slots.length; i++) {
            slots[i] -= new Random(slots[i] + 1).nextInt();
        }
        int[] toArray = Arrays.stream(slots).sorted().distinct().toArray();
        if (Arrays.stream(toArray).sum() == 0) {
            toArray[0] = 1;
        }
        return toArray;
    }

    public static List<SKill> f2A(List<SKill> skills) {
        List<SKill> list = new ArrayList<>();
        for (SKill skill : skills) {
            int lv = skill.getLv();
            if (lv == 0) continue;
            int i = lv + (lv > 0 ? -new Random(lv + 1).nextInt() : new Random(-lv - 1).nextInt());
            list.add(ESkill.getById(skill.getId()).lv(i));
        }
        return list;
    }

    public static List<SKill> f2B(List<SKill> skills) {
        List<SKill> list = new ArrayList<>();
        for (SKill skill : skills) {
            int lv = skill.getLv();
            if (lv == 0) continue;
            int i = lv + (lv > 0 ? -new Random(lv).nextInt() : new Random(-lv).nextInt());
            if (i == 0) continue;
            list.add(ESkill.getById(skill.getId()).lv(i));
        }
        return list;
    }

}
