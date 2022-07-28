package com.marisa.ume.smith.e;

import com.marisa.ume.item.ItemRegistry;
import com.marisa.ume.item.widget.Widget;

public enum EWidget {

    UNKNOWN(null),
    ZOMBIE_HEAD((Widget) ItemRegistry.WIDGET_HEALTH.get()),
    ZOMBIE_BODY((Widget) ItemRegistry.WIDGET_POWER.get()),
    ZOMBIE_LEG((Widget) ItemRegistry.WIDGET_SPEED.get());
    
    private final Widget widget;

    EWidget(Widget widget) {
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    public static EWidget getById(int id) {
        for (EWidget value : EWidget.values()) {
            if (value.widget.getId() == id) return value;
        }
        return UNKNOWN;
    }

}
