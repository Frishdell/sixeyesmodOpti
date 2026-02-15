package com.coffee.sixeyesmod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "sixeyesmod")
public class AbilityHandler {

    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        
        // Логика бесконечности: если моб слишком близко к игроку
        if (entity instanceof MobEntity) {
            PlayerEntity closestPlayer = entity.level.getNearestPlayer(entity, 5.0D);
            if (closestPlayer != null) {
                // ИСПРАВЛЕНО ДЛЯ 1.16.5: Кастуем в MobEntity для setNoAI
                ((MobEntity) entity).setNoAI(true);
            }
        }
    }
}
