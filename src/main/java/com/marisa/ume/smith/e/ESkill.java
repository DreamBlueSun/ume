package com.marisa.ume.smith.e;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.marisa.ume.smith.c.Skill;
import com.marisa.ume.util.UmeUtils;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.function.Function;

public enum ESkill {

    UNKNOWN(0, 0, "", 0, 0, ESkill::attributesNull),

    POWER(1, 1001, "", 0, 5, ESkill::attributesNull),
    CRI_POWER(1, 1002, "", -5, 5, ESkill::attributesNull),
    ATK_HEAL(1, 1003, "", 0, 3, ESkill::attributesNull),
    ATK_SPEED(1, 1004, "", -3, 5, (lv) -> {
        if (lv == null || lv == 0) return null;
        Function<Integer, Double> f = i -> i < 0 ? i * 10D : i * 4D;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        multimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(UmeUtils.UUID_ATTACK_SPEED, "ume atk speed modifier", f.apply(lv), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return multimap;
    }),
    BOW_SPEED(1, 1005, "", -3, 3, ESkill::attributesNull),
    ARROW_MORE(1, 1006, "", 0, 2, ESkill::attributesNull),
    ARROW_THROUGH(1, 1007, "", 0, 3, ESkill::attributesNull),

    HEALTH(2, 2001, "", -3, 3, (lv) -> {
        if (lv == null || lv == 0) return null;
        Function<Integer, Double> f = i -> i < 0 ? i * 2D : i == 1 ? 4D : (i - 1) * 8D + 4D;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        multimap.put(Attributes.MAX_HEALTH, new AttributeModifier(UmeUtils.UUID_MAX_HEALTH, "ume health modifier", f.apply(lv), AttributeModifier.Operation.ADDITION));
        return multimap;
    }),
    DEF(2, 2002, "", -3, 7, (lv) -> {
        if (lv == null || lv == 0) return null;
        Function<Integer, Double> f = i -> i < 0 ? i * 4D : i == 6 ? 7D : i == 7 ? 10D : i;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        multimap.put(Attributes.ARMOR, new AttributeModifier(UmeUtils.UUID_ARMOR, "ume armor modifier", f.apply(lv), AttributeModifier.Operation.ADDITION));
        return multimap;
    }),
    CARE(2, 2003, "", -3, 3, ESkill::attributesNull),

    SPEED(3, 3001, "", -3, 3, (lv) -> {
        if (lv == null || lv == 0) return null;
        Function<Integer, Double> f = i -> i < 0 ? i * 10D : i * 5D;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        multimap.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UmeUtils.UUID_MOVEMENT_SPEED, "ume movement speed modifier", f.apply(lv), AttributeModifier.Operation.MULTIPLY_TOTAL));
        return multimap;
    }),
    REGULAR_HEAL(3, 3002, "", -3, 3, ESkill::attributesNull),
    REGULAR_REPLY(3, 3003, "", -3, 3, ESkill::attributesNull),
    LUCK(3, 3004, "", -3, 3, (lv) -> {
        if (lv == null || lv == 0) return null;
        Multimap<Attribute, AttributeModifier> multimap = HashMultimap.create();
        multimap.put(Attributes.LUCK, new AttributeModifier(UmeUtils.UUID_LUCK, "ume luck modifier", lv, AttributeModifier.Operation.ADDITION));
        return multimap;
    }),
    ENCHANT_UP_MAIN_HAND(3, 3005, "", 0, 1, ESkill::attributesNull);

    private final Skill sKill;

    ESkill(int type, int id, String name, int lvMin, int lvMax, Function<Integer, Multimap<Attribute, AttributeModifier>> attributesSupplier) {
        this.sKill = new Skill(type, id, name, 0, lvMin, lvMax, attributesSupplier);
    }

    public int getSkillId() {
        return sKill.getId();
    }

    public static ESkill getById(int id) {
        for (ESkill value : ESkill.values()) {
            if (value.sKill.getId() == id) return value;
        }
        return UNKNOWN;
    }

    public Skill lv(int lv) {
        int lvMin = sKill.getLvMin();
        int lvMax = sKill.getLvMax();
        if (lv < lvMin) {
            lv = lvMin;
        } else if (lv > lvMax) {
            lv = lvMax;
        }
        return new Skill(sKill.getType(), sKill.getId(), sKill.getName(), lv, lvMin, lvMax, sKill.getAttributesFunction());
    }

    private static Multimap<Attribute, AttributeModifier> attributesNull(int i) {
        return null;
    }

}
