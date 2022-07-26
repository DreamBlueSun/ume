package com.marisa.ume.group;

import com.marisa.ume.item.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 创建创造物品栏分组
 */

public class StoryGroup {

    public static CreativeModeTab MAIN = new CreativeModeTab("tab_main") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.ARMOR_WIDGET_BLOCK.get());
        }
    };

}
