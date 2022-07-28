package com.marisa.ume.item.widget;

import com.marisa.ume.group.StoryGroup;
import com.marisa.ume.smith.c.Skill;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 *
 */

public class Widget extends Item {
    
    private final int id;
    private final int slotLv;
    private final Skill skill;

    public Widget(int id, int slotLv, Skill skill) {
        super(new Properties().stacksTo(1).tab(StoryGroup.MAIN));
        this.id = id;
        this.slotLv = slotLv;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public int getSlotLv() {
        return slotLv;
    }

    public Skill getSkill() {
        return skill;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        toolTip.add(MutableComponent.create(new TranslatableContents("饰品")).withStyle(ChatFormatting.LIGHT_PURPLE));
        toolTip.add(MutableComponent.create(new TranslatableContents("")));
    }
    
}
