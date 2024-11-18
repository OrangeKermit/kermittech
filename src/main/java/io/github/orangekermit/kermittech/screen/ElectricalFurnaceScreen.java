package io.github.orangekermit.kermittech.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.orangekermit.kermittech.KermitTech;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectricalFurnaceScreen extends AbstractContainerScreen<ElectricalFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(KermitTech.MOD_ID, "textures/gui/electrical_furnace_gui.png");

    public ElectricalFurnaceScreen(ElectricalFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProcessProgress(guiGraphics, x, y);
        renderEnergyProgress(guiGraphics, x, y);
    }

    private void renderProcessProgress(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isProcessing()) {
            guiGraphics.blit(TEXTURE, x + 77, y + 35, 176, 0, menu.getScaledProcessProgress(), 16);
        }
    }

    private void renderEnergyProgress(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 163, y + 68-menu.getScaledEnergyProgress(), 176, 67-menu.getScaledEnergyProgress(), 3, menu.getScaledEnergyProgress());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
