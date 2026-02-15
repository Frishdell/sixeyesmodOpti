package com.coffee.sixeyesmod;

import net.minecraft.client.Minecraft;
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
        if (mc.player == null) return;

        // ИСПРАВЛЕНО ДЛЯ 1.16.5: Используем getActiveRenderInfo()
        Vector3d view = mc.gameRenderer.getActiveRenderInfo().getProjectedView();
        
        // Логика отрисовки купола Годжо (шейдеры)
        // Здесь должен быть твой код вызова купола
    }
}
