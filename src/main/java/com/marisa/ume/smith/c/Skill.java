package com.marisa.ume.smith.c;

/**
 *
 */

public class Skill {

    private final int type;
    private final int id;
    private final String name;
    private final int lv;
    private final int lvMin;
    private final int lvMax;

    public Skill(int type, int id, String name, int lv, int lvMin, int lvMax) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.lv = lv;
        this.lvMin = lvMin;
        this.lvMax = lvMax;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLv() {
        return lv;
    }

    public int getLvMin() {
        return lvMin;
    }

    public int getLvMax() {
        return lvMax;
    }
}
