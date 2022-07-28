package com.marisa.ume.smith.e;

import com.marisa.ume.smith.c.Skill;

public enum ESkill {

    UNKNOWN(0, 0, "", 0, 0),

    POWER(1, 1001, "", 0, 5),
    CRI_POWER(1, 1002, "", -5, 5),
    ATK_HEAL(1, 1003, "", 0, 3),
    ATK_SPEED(1, 1004, "", -3, 5),
    BOW_SPEED(1, 1005, "", -3, 3),
    ARROW_MORE(1, 1006, "", 0, 2),
    ARROW_THROUGH(1, 1007, "", 0, 3),

    HEALTH(2, 2001, "", -3, 3),
    DEF(2, 2002, "", -3, 5),
    CARE(2, 2003, "", -3, 3),

    SPEED(3, 3001, "", -3, 3),
    REGULAR_HEAL(3, 3002, "", -3, 3),
    REGULAR_REPLY(3, 3003, "", -3, 3),
    LUCK(3, 3004, "", -3, 3);

    private final Skill sKill;

    ESkill(int type, int id, String name, int lvMin, int lvMax) {
        this.sKill = new Skill(type, id, name, 0, lvMin, lvMax);
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
        return new Skill(sKill.getType(), sKill.getId(), sKill.getName(), lv, lvMin, lvMax);
    }

}
