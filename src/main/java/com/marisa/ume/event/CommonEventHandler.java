package com.marisa.ume.event;

import com.marisa.ume.util.WidgetUtils;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * 监听事件处理器
 */

public class CommonEventHandler {

    @SubscribeEvent
    public void itemTooltip(ItemTooltipEvent event) {
        WidgetUtils.showTips(event.getItemStack(), event.getToolTip());
    }

//    private static final UUID UUID_MOVEMENT_SPEED = UUID.randomUUID();
//
//    @SubscribeEvent
//    public void itemAttributeModifier(ItemAttributeModifierEvent event) {
//        ItemStack stack = event.getItemStack();
//        //判断主手或盔甲槽位
//        EquipmentSlot slot;
//        if (stack.getItem() instanceof ArmorItem armor) {
//            slot = armor.getSlot();
//        } else {
//            slot = EquipmentSlot.MAINHAND;
//        }
//        if (slot != event.getSlotType()) {
//            return;
//        }
//        //鉴定属性
//        EQuality quality = QualityHelper.getQuality(stack);
//        if (quality != EQuality.UNKNOWN) {
//            quality.getAttr(stack.getItem()).addAttributeModifier(event);
//        }
//        //武器敏捷属性
//        if (StoryUtils.isWeapon(stack.getItem())) {
//            int agl = SmithHelper.getSmithAgl(stack);
//            if (agl != 0) {
//                double abs = Math.abs(agl);
//                double v = agl < 0 ? -(abs / (abs + 100.0F)) : abs / (abs + 100.0F);
//                event.addModifier(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID_MOVEMENT_SPEED, "main hand modifier", v, AttributeModifier.Operation.MULTIPLY_TOTAL));
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public void livingUpdate(LivingEvent.LivingUpdateEvent event) {
//        //拦截实体tick
//        if (event.getEntity() instanceof ServerPlayer player) {
//            int value = (int) player.getAttributeValue(Attributes.MAX_HEALTH);
//            if (player.getHealth() > value) {
//                player.setHealth(value);
//            }
//        }
//    }

}
