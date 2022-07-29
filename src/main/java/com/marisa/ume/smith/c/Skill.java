package com.marisa.ume.smith.c;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.function.Function;

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

    private final Function<Integer, Multimap<Attribute, AttributeModifier>> attributesFunction;

    public Skill(int type, int id, String name, int lv, int lvMin, int lvMax, Function<Integer, Multimap<Attribute, AttributeModifier>> attributesFunction) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.lv = lv;
        this.lvMin = lvMin;
        this.lvMax = lvMax;
        this.attributesFunction = attributesFunction;
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

    public Function<Integer, Multimap<Attribute, AttributeModifier>> getAttributesFunction() {
        return attributesFunction;
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return this.attributesFunction.apply(this.lv);
    }
}
