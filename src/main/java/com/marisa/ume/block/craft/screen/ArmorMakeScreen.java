package com.marisa.ume.block.craft.screen;

import com.marisa.ume.Ume;
import com.marisa.ume.block.craft.menu.ArmorMakeMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArmorMakeScreen extends ItemCombinerScreen<ArmorMakeMenu> {
    private static final ResourceLocation LOCATION = new ResourceLocation(Ume.MODID, "textures/gui/container/item_edge.png");

    public ArmorMakeScreen(ArmorMakeMenu p_99290_, Inventory p_99291_, Component p_99292_) {
        super(p_99290_, p_99291_, p_99292_, LOCATION);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(PoseStack p_99294_, int p_99295_, int p_99296_) {
        RenderSystem.disableBlend();
        super.renderLabels(p_99294_, p_99295_, p_99296_);
    }
}