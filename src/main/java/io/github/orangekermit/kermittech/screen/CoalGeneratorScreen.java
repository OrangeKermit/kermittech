package io.github.orangekermit.kermittech.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.orangekermit.kermittech.KermitTech;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.event.RenderTooltipEvent;

public class CoalGeneratorScreen extends AbstractContainerScreen<CoalGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(KermitTech.MOD_ID, "textures/gui/coal_generator_gui.png");

    public CoalGeneratorScreen(CoalGeneratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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

        renderFireProgress(guiGraphics, x, y);
        renderEnergyProgress(guiGraphics, x, y);
        renderEnergyText(guiGraphics, x, y, menu.getEnergyAmount(), menu.getMaxEnergyAmount());
        renderEnergyRateText(guiGraphics, x, y, menu.getEnergyRateAmount());
    }

    private void renderFireProgress(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isBurning()) {
            guiGraphics.blit(TEXTURE, x + 46, y + 38-menu.getScaledFireProgress(), 176, 13-menu.getScaledFireProgress(), 13, menu.getScaledFireProgress());
        }
    }

    private void renderEnergyProgress(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(TEXTURE, x + 104, y + 69-menu.getScaledEnergyProgress(), 176, 65-menu.getScaledEnergyProgress(), 13, menu.getScaledEnergyProgress());
    }

    private void renderEnergyText(GuiGraphics guiGraphics, int x, int y, int energy, int maxEnergy) {
        guiGraphics.drawString(this.font, energy+"/", x+124, y+23, 0x404040, false);
        guiGraphics.drawString(this.font, maxEnergy+" FE", x+124, y+38, 0x404040, false);
    }

    private void renderEnergyRateText(GuiGraphics guiGraphics, int x, int y, int energyRate) {
        guiGraphics.drawString(this.font, energyRate+" FE/t", x+124, y+53, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
