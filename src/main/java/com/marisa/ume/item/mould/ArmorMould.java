package com.marisa.ume.item.mould;


import com.marisa.ume.smith.c.Skill;
import com.marisa.ume.util.MakeUtils;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ArmorMould extends Mould {

    private final int rank;
    private final int type;

    private final int[] slotsBase;
    private final List<Skill> skillsBase;

    private int[] slots;
    private List<Skill> skills;

    private final Function<int[], int[]> f1;
    private final Function<List<Skill>, List<Skill>> f2;

    public ArmorMould(int rank, int type, int id, Function<List<Skill>, List<Skill>> f, Skill... skills) {
        super(id);
        this.rank = rank;
        this.type = type;
        this.slotsBase = new int[]{0, 0, 0};
        this.skillsBase = skills == null ? new ArrayList<>() : Arrays.stream(skills).toList();
        this.f1 = null;
        this.f2 = f;
    }

    public ArmorMould(int rank, int type, int id, Function<int[], int[]> f1, int[] slots) {
        super(id);
        this.rank = rank;
        this.type = type;
        this.slotsBase = slots;
        this.skillsBase = new ArrayList<>();
        this.f1 = f1;
        this.f2 = null;
    }

    public ArmorMould(int rank, int type, int id, Function<int[], int[]> f1, int[] slots, Function<List<Skill>, List<Skill>> f2, Skill... skills) {
        super(id);
        this.rank = rank;
        this.type = type;
        this.slotsBase = slots;
        this.skillsBase = skills == null ? new ArrayList<>() : Arrays.stream(skills).toList();
        this.f1 = f1;
        this.f2 = f2;
    }

    private void build() {
        this.slots = f1 == null ? this.slotsBase.clone() : this.f1.apply(this.slotsBase.clone());
        this.skills = f2 == null ? new ArrayList<>(this.skillsBase) : this.f2.apply(new ArrayList<>(this.skillsBase));
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

    @Override
    public void make(ItemStack stack) {
        build();
        MakeUtils.add(stack, this.slots, this.skills);
    }
}
