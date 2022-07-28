package com.marisa.ume.block.craft.menu;

import com.marisa.ume.block.BlockRegistry;
import com.marisa.ume.item.mould.ArmorMould;
import com.marisa.ume.sound.SoundRegistry;
import com.marisa.ume.util.UmeUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
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
        super(MenuTypeRegistry.ARMOR_MAKE_MENU.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPickup(@NotNull Player player, boolean slotHasItem) {
        return slotHasItem;
    }

    @Override
    protected void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        ((ArmorMould) this.inputSlots.getItem(ItemCombinerMenu.ADDITIONAL_SLOT).getItem()).make(stack);
        this.shrinkStackInSlot(ItemCombinerMenu.INPUT_SLOT);
        this.shrinkStackInSlot(ItemCombinerMenu.ADDITIONAL_SLOT);
        this.access.execute((level, blockPos) -> level.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundRegistry.BLOCK_CRAFT_MAKE.get(), SoundSource.BLOCKS, 1.0F, 1.0F));
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
        ItemStack stack0 = this.inputSlots.getItem(ItemCombinerMenu.INPUT_SLOT);
        if (!UmeUtils.canMake(stack0)) return;
        ItemStack stack1 = this.inputSlots.getItem(ItemCombinerMenu.ADDITIONAL_SLOT);
        if (!UmeUtils.isArmorMould(stack1)) return;
        this.resultSlots.setItem(ItemCombinerMenu.INPUT_SLOT, stack0.copy());
    }
}
