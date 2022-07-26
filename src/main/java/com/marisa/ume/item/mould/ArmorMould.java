package com.marisa.ume.item.mould;


import com.marisa.ume.smith.ISkill;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ArmorMould extends Mould {

    protected int[] slots;
    protected List<ISkill> skills;

    public ArmorMould(List<ISkill> skills) {
        super();
        this.slots = new int[]{0, 0, 0};
        this.skills = skills;
    }

    public ArmorMould(int[] slots, List<ISkill> skills) {
        super();
        this.slots = slots;
        this.skills = skills;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> toolTip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, level, toolTip, flagIn);
        toolTip.add(MutableComponent.create(new TranslatableContents("")).withStyle(ChatFormatting.YELLOW).append("     ")
                .append(MutableComponent.create(new TranslatableContents("")).withStyle(ChatFormatting.YELLOW)));


        toolTip.add(MutableComponent.create(new TranslatableContents("")).withStyle(ChatFormatting.YELLOW).append("     ")
                .append(MutableComponent.create(new TranslatableContents("")).withStyle(ChatFormatting.YELLOW)));
    }
}
