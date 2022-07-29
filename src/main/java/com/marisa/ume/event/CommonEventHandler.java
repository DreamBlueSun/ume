package com.marisa.ume.event;

import com.marisa.ume.smith.c.Skill;
import com.marisa.ume.util.UmeUtils;
import com.marisa.ume.util.WidgetUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event) {
        WidgetUtils.showTips(event.getItemStack(), event.getToolTip());
    }

    @SubscribeEvent
    public void itemAttributeModifier(LivingEquipmentChangeEvent event) {
        EquipmentSlot slot = event.getSlot();
        if (slot.getType() != EquipmentSlot.Type.ARMOR) return;
        LivingEntity entity = event.getEntity();
        ItemStack from = event.getFrom();
        ItemStack itemHead = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack itemChest = entity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack itemLegs = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack itemFeet = entity.getItemBySlot(EquipmentSlot.FEET);
        List<Skill> skillsTo = UmeUtils.getSkills(itemHead, itemChest, itemLegs, itemFeet);
        List<Skill> skillsFrom;
        switch (slot) {
            case HEAD -> skillsFrom = UmeUtils.getSkills(from, itemChest, itemLegs, itemFeet);
            case CHEST -> skillsFrom = UmeUtils.getSkills(itemHead, from, itemLegs, itemFeet);
            case LEGS -> skillsFrom = UmeUtils.getSkills(itemHead, itemChest, from, itemFeet);
            case FEET -> skillsFrom = UmeUtils.getSkills(itemHead, itemChest, itemLegs, from);
            default -> skillsFrom = new ArrayList<>();
        }
        for (Skill skill : skillsFrom) {
            entity.getAttributes().removeAttributeModifiers(skill.getAttributeModifiers());
        }
        for (Skill skill : skillsTo) {
            entity.getAttributes().addTransientAttributeModifiers(skill.getAttributeModifiers());
        }
    }

}
