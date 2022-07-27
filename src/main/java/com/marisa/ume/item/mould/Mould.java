package com.marisa.ume.item.mould;


import com.marisa.ume.group.StoryGroup;
import com.marisa.ume.smith.i.IMake;
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
 * 抽象模具类
 */

public abstract class Mould extends Item implements IMake{

    public Mould() {
        super(new Properties().stacksTo(1).tab(StoryGroup.MAIN));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        toolTip.add(MutableComponent.create(new TranslatableContents("模具")).withStyle(ChatFormatting.LIGHT_PURPLE));
        toolTip.add(MutableComponent.create(new TranslatableContents("")));
    }

}
