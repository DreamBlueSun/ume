package com.marisa.ume.block.craft;


import com.marisa.ume.block.craft.menu.ArmorMakeMenu;
import com.marisa.ume.item.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ArmorMakeBlock extends CraftBlock {

    public ArmorMakeBlock() {
        super();
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_ANVIL);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
        return new SimpleMenuProvider((openContainerId, inventory, player) ->
                new ArmorMakeMenu(openContainerId, inventory, ContainerLevelAccess.create(level, pos)),
                MutableComponent.create(new TranslatableContents("ArmorMake")));
    }

    @Override
    public void spawnAfterBreak(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull ItemStack stack, boolean p) {
        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), ItemRegistry.ARMOR_MAKE_BLOCK.get().getDefaultInstance());
        super.spawnAfterBreak(state, level, pos, stack, p);
    }
}