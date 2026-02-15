package com.coffee.sixeyesmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "sixeyesmod", value = Dist.CLIENT)
public class DomainWorldRenderer {

    @SubscribeEvent
    public static void onRenderWorld(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.gameRenderer == null) return;

        // Прямое обращение к ActiveRenderInfo для 1.16.5
        ActiveRenderInfo info = mc.gameRenderer.getMainCamera(); 
        Vector3d view = info.getPosition();
        
        // Здесь твоя логика отрисовки купола Годжо
    }
}
