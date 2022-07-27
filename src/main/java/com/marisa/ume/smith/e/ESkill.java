package com.marisa.ume.smith.e;

import com.marisa.ume.smith.c.SKill;

public enum ESkill {

    UNKNOWN(0, 0, "", 0, 0, 0),

    POWER(1, 1, "", 0, 0, 5),

    HEALTH(2, 2, "", 0, -3, 3),

    SPEED(3, 3, "", 0, -3, 3);

    private final SKill sKill;

    ESkill(int type, int id, String name, int lv, int lvMin, int lvMax) {
        this.sKill = new SKill(type, id, name, lv, lvMin, lvMax);
    }

    public int getType() {
        return sKill.getType();
    }

    public int getId() {
        return sKill.getId();
    }

    public String getName() {
        return sKill.getName();
    }

    public int getLv() {
        return sKill.getLv();
    }

    public int getLvMin() {
        return sKill.getLvMin();
    }

    public int getLvMax() {
        return sKill.getLvMax();
    }

    public static ESkill getById(int id) {
        for (ESkill value : ESkill.values()) {
            if (value.sKill.getId() == id) return value;
        }
        return UNKNOWN;
    }

    public SKill lv(int lv) {
        return new SKill(getType(), getId(), getName(), lv, getLvMin(), getLvMax());
    }

}
