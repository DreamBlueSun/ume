package com.marisa.ume.block.craft.menu;

import com.marisa.ume.block.BlockRegistry;
import com.marisa.ume.item.widget.Widget;
import com.marisa.ume.smith.e.EWidget;
import com.marisa.ume.util.MakeUtils;
import com.marisa.ume.util.WidgetUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ArmorWidgetMenu extends WidgetMenu {

    public ArmorWidgetMenu(int containerId, Inventory inventory, FriendlyByteBuf extraData) {
        this(containerId, inventory);
    }

    public ArmorWidgetMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public ArmorWidgetMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(MenuTypeRegistry.ARMOR_WIDGET_MENU.get(), containerId, inventory, access);
    }

    @Override
    protected boolean mayPlace(int index, ItemStack stack) {
        if (index == WidgetMenu.ARMOR_SLOT_INDEX) {
            return MakeUtils.have(stack);
        } else {
            ItemStack stack0 = this.inputSlots.getItem(WidgetMenu.ARMOR_SLOT_INDEX);
            if (stack0.isEmpty()) {
                return false;
            } else {
                int[] ints = WidgetUtils.getAll(stack0);
                if (ints == null || ints.length < index) return false;
                return stack.getItem() instanceof Widget widget && widget.getSlotLv() <= ints[index - 1];
            }
        }
    }

    @Override
    protected boolean mayPickup(Player player, boolean hasItem) {
        return hasItem;
    }

    @Override
    protected void afterSet(ItemStack stack) {
        if (MakeUtils.have(stack)) {
            int[] ints = WidgetUtils.getAll(stack);
            if (ints != null && ints.length > 0) {
                for (int i = 0; i < ints.length; i++) {
                    Widget widget = EWidget.getById(ints[i]).getWidget();
                    if (widget != null) {
                        this.inputSlots.setItem(i + 1, widget.getDefaultInstance());
                    }
                }
            }
        } else {
            change(this.inputSlots.getItem(WidgetMenu.ARMOR_SLOT_INDEX));
        }
    }

    @Override
    protected void onTake(Player player, ItemStack stack) {
        change(stack);
        if (MakeUtils.have(stack)) {
            shrinkStackInSlot(WidgetMenu.WIDGET_SLOT_INDEX_1);
            shrinkStackInSlot(WidgetMenu.WIDGET_SLOT_INDEX_2);
            shrinkStackInSlot(WidgetMenu.WIDGET_SLOT_INDEX_3);
        }
    }

    private void change(ItemStack stack) {
        WidgetUtils.clear(stack);
        int[] ints = new int[]{0, 0, 0};
        ItemStack stack1 = this.inputSlots.getItem(WidgetMenu.WIDGET_SLOT_INDEX_1);
        if (!stack1.isEmpty()) ints[0] = ((Widget) stack1.getItem()).getId();
        ItemStack stack2 = this.inputSlots.getItem(WidgetMenu.WIDGET_SLOT_INDEX_2);
        if (!stack2.isEmpty()) ints[1] = ((Widget) stack2.getItem()).getId();
        ItemStack stack3 = this.inputSlots.getItem(WidgetMenu.WIDGET_SLOT_INDEX_3);
        if (!stack3.isEmpty()) ints[2] = ((Widget) stack3.getItem()).getId();
        WidgetUtils.add(stack, ints);
    }

    private void shrinkStackInSlot(int slot) {
        ItemStack itemstack = this.inputSlots.getItem(slot);
        itemstack.shrink(1);
        this.inputSlots.setItem(slot, itemstack);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(BlockRegistry.ARMOR_WIDGET_BLOCK.get());
    }
}
