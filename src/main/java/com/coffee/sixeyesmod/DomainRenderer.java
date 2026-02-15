package com.coffee.sixeyesmod;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "sixeyesmod", value = Dist.CLIENT)
public class DomainRenderer {
    @SubscribeEvent
    public static void onRender(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.getEntityRenderDispatcher() == null) return;
        // Камера для 1.16.5
        Vector3d view = mc.getEntityRenderDispatcher().camera.getPosition();
        // Твои шейдеры пустоты внутри здесь
    }
}
