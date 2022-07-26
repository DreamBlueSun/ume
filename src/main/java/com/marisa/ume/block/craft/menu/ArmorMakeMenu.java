package com.marisa.ume.block.craft.menu;

import com.marisa.ume.block.BlockRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class ArmorMakeMenu extends ItemCombinerMenu {

    public ArmorMakeMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ArmorMakeMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ArmorMakeMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuType.ANVIL, containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return !this.resultSlots.getItem(0).isEmpty();
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.shrinkStackInSlot(0);
        this.shrinkStackInSlot(1);
//        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.SMITH_BLOCK_CRAFT.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ARMOR_MAKE_BLOCK.get());
    }

    @Override
    public void createResult() {
        ItemStack stack0 = this.inputSlots.getItem(0);
        Item item0 = stack0.getItem();
        //TODO
        this.resultSlots.setItem(0, stack0);
    }
}
