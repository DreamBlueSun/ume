package com.marisa.ume.block.craft.screen;

import com.marisa.ume.Ume;
import com.marisa.ume.block.craft.menu.ArmorWidgetMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


@OnlyIn(Dist.CLIENT)
public class ArmorWidgetScreen<T extends ArmorWidgetMenu> extends AbstractContainerScreen<T> implements ContainerListener {
    
    private final ResourceLocation menuResource;

    public ArmorWidgetScreen(T menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.menuResource = new ResourceLocation(Ume.MODID, "textures/gui/container/item_strengthen.png");
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void subInit() {
    }

    protected void init() {
        super.init();
        this.subInit();
        this.menu.addSlotListener(this);
    }

    public void removed() {
        super.removed();
        this.menu.removeSlotListener(this);
    }

    public void render(@NotNull PoseStack stack, int a, int b, float c) {
        this.renderBackground(stack);
        super.render(stack, a, b, c);
        RenderSystem.disableBlend();
        this.renderFg(stack, a, b, c);
        this.renderTooltip(stack, a, b);
    }

    protected void renderFg(PoseStack a, int b, int c, float d) {
    }

    protected void renderLabels(@NotNull PoseStack stack, int a, int b) {
        RenderSystem.disableBlend();
        super.renderLabels(stack, a, b);
    }

    protected void renderBg(@NotNull PoseStack stack, float a, int b, int c) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.menuResource);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(stack, i + 59, j + 20, 0, this.imageHeight + (this.menu.getSlot(0).hasItem() ? 0 : 16), 110, 16);
        if ((this.menu.getSlot(ArmorWidgetMenu.ARMOR_SLOT_INDEX).hasItem() || this.menu.getSlot(ArmorWidgetMenu.WIDGET_SLOT_INDEX_1).hasItem()
                || this.menu.getSlot(ArmorWidgetMenu.WIDGET_SLOT_INDEX_2).hasItem() || this.menu.getSlot(ArmorWidgetMenu.WIDGET_SLOT_INDEX_3).hasItem())) {
            this.blit(stack, i + 117, j + 45, this.imageWidth, 0, 28, 21);
        }

    }

    public void dataChanged(@NotNull AbstractContainerMenu containerMenu, int a, int b) {
    }

    public void slotChanged(@NotNull AbstractContainerMenu containerMenu, int a, @NotNull ItemStack stack) {
    }
}