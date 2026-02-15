package com.coffee.sixeyesmod;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class DomainRenderer {
    private static boolean active = false;
    private static int ticks = 0;
    private static final ResourceLocation VOID_TEX = new ResourceLocation(Main.MODID, "textures/misc/void_overlay.png");

    public static void startEffect() { active = true; ticks = 200; }

    @SubscribeEvent
    public static void onRender(RenderGameOverlayEvent.Post event) {
        if (active && event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            ticks--; if (ticks <= 0) active = false;
            Minecraft mc = Minecraft.getInstance();
            RenderSystem.enableBlend();
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.7F);
            mc.getTextureManager().bindTexture(VOID_TEX);
            int w = event.getWindow().getScaledWidth();
            int h = event.getWindow().getScaledHeight();
            mc.ingameGUI.blit(event.getMatrixStack(), 0, 0, 0, 0, w, h, w, h);
        }
    }
}
