package com.coffee.sixeyesmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class DomainWorldRenderer {

    @SubscribeEvent
    public static void onRenderWorld(RenderWorldLastEvent event) {
        if (AbilityHandler.domainCenter != null) {
            renderSphere(event.getMatrixStack(), AbilityHandler.domainCenter, AbilityHandler.DOMAIN_RADIUS);
        }
    }

    private static void renderSphere(MatrixStack ms, Vector3d center, double radius) {
        Minecraft mc = Minecraft.getInstance();
        Vector3d view = mc.gameRenderer.getMainCamera().getPosition();
        
        ms.push();
        ms.translate(center.x - view.x, center.y - view.y, center.z - view.z);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.disableCull(); 
        RenderSystem.color4f(0.05f, 0.0f, 0.2f, 0.5f); // Темно-фиолетовый полупрозрачный цвет

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        buffer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION);

        int segs = 16; 
        for (int i = 0; i <= segs; i++) {
            double lat0 = Math.PI * (-0.5 + (double) (i - 1) / segs);
            double z0 = Math.sin(lat0) * radius;
            double zr0 = Math.cos(lat0) * radius;

            double lat1 = Math.PI * (-0.5 + (double) i / segs);
            double z1 = Math.sin(lat1) * radius;
            double zr1 = Math.cos(lat1) * radius;

            for (int j = 0; j <= segs; j++) {
                double lng = 2 * Math.PI * (double) (j - 1) / segs;
                double x = Math.cos(lng);
                double y = Math.sin(lng);
                buffer.pos(x * zr0, z0, y * zr0).endVertex();
                buffer.pos(x * zr1, z1, y * zr1).endVertex();
            }
        }
        tess.draw();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        ms.pop();
    }
}
