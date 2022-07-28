package com.marisa.ume.block.craft.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public abstract class WidgetMenu extends AbstractContainerMenu {

    public static final int ARMOR_SLOT_INDEX = 0;
    public static final int WIDGET_SLOT_INDEX_1 = 1;
    public static final int WIDGET_SLOT_INDEX_2 = 2;
    public static final int WIDGET_SLOT_INDEX_3 = 3;
    private static final int INV_SLOT_START = 4;
    private static final int USE_ROW_SLOT_END = 40;
    protected final Container inputSlots = new SimpleContainer(INV_SLOT_START) {
        public void setChanged() {
            super.setChanged();
            WidgetMenu.this.slotsChanged(this);
        }
    };
    protected final ContainerLevelAccess access;
    protected final Player player;

    public WidgetMenu(@Nullable MenuType<?> menuType, int openContainerId, Inventory inventory, ContainerLevelAccess access) {
        super(menuType, openContainerId);
        this.access = access;
        this.player = inventory.player;
        this.addSlot(new Slot(this.inputSlots, ARMOR_SLOT_INDEX, 14, 47) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return WidgetMenu.this.mayPlace(ARMOR_SLOT_INDEX, stack);
            }

            @Override
            public boolean mayPickup(@NotNull Player player) {
                return WidgetMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void set(@NotNull ItemStack stack) {
                super.set(stack);
                afterSet(stack);
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                WidgetMenu.this.onTake(player, stack);
            }
        });
        this.addSlot(new Slot(this.inputSlots, WIDGET_SLOT_INDEX_1, 51, 47) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return WidgetMenu.this.mayPlace(WIDGET_SLOT_INDEX_1, stack);
            }

            @Override
            public boolean mayPickup(@NotNull Player player) {
                return WidgetMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void set(@NotNull ItemStack stack) {
                super.set(stack);
                afterSet(stack);
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                WidgetMenu.this.onTake(player, stack);
            }
        });
        this.addSlot(new Slot(this.inputSlots, WIDGET_SLOT_INDEX_2, 76, 47) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return WidgetMenu.this.mayPlace(WIDGET_SLOT_INDEX_2, stack);
            }

            @Override
            public boolean mayPickup(@NotNull Player player) {
                return WidgetMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void set(@NotNull ItemStack stack) {
                super.set(stack);
                afterSet(stack);
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                WidgetMenu.this.onTake(player, stack);
            }
        });
        this.addSlot(new Slot(this.inputSlots, WIDGET_SLOT_INDEX_3, 100, 47) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return WidgetMenu.this.mayPlace(WIDGET_SLOT_INDEX_3, stack);
            }

            @Override
            public boolean mayPickup(@NotNull Player player) {
                return WidgetMenu.this.mayPickup(player, this.hasItem());
            }

            @Override
            public void set(@NotNull ItemStack stack) {
                super.set(stack);
                afterSet(stack);
            }

            @Override
            public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
                WidgetMenu.this.onTake(player, stack);
            }
        });

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }

    }

    protected abstract boolean mayPlace(int index, ItemStack stack);

    protected abstract boolean mayPickup(Player player, boolean hasItem);

    protected abstract void afterSet(ItemStack stack);

    protected abstract void onTake(Player player, ItemStack stack);

    protected abstract boolean isValidBlock(BlockState state);

    @Override
    protected void clearContainer(@NotNull Player player, @NotNull Container container) {
        onTake(player, this.inputSlots.getItem(ARMOR_SLOT_INDEX));
        super.clearContainer(player, container);
    }

    public void removed(@NotNull Player player) {
        super.removed(player);
        this.access.execute((p_39796_, p_39797_) -> this.clearContainer(player, this.inputSlots));
    }

    public boolean stillValid(@NotNull Player player) {
        return this.access.evaluate((p_39785_, p_39786_) -> this.isValidBlock(p_39785_.getBlockState(p_39786_)) && player.distanceToSqr((double) p_39786_.getX() + 0.5D, (double) p_39786_.getY() + 0.5D, (double) p_39786_.getZ() + 0.5D) <= 64.0D, true);
    }

    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index != ARMOR_SLOT_INDEX && index != WIDGET_SLOT_INDEX_1 && index != WIDGET_SLOT_INDEX_2 && index != WIDGET_SLOT_INDEX_3) {
                if (index >= INV_SLOT_START && index < USE_ROW_SLOT_END) {
                    if (!this.moveItemStackTo(stack, ARMOR_SLOT_INDEX, WIDGET_SLOT_INDEX_3, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(stack, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }
}