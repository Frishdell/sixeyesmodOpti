package com.coffee.sixeyesmod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "sixeyesmod")
public class AbilityHandler {
    @SubscribeEvent
    public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity entity = event.getEntityLiving();
        // В 1.16.5 используем .world
        if (entity instanceof MobEntity && entity.world != null) {
            PlayerEntity closestPlayer = entity.world.getNearestPlayer(entity, 5.0D);
            if (closestPlayer != null) {
                ((MobEntity) entity).setNoAI(true);
            }
        }
    }

    // Методы для Networking и ModEvents
    public static void executeDomain(ServerPlayerEntity player) {}
    public static void executeInfinity(ServerPlayerEntity player) {}
    public static void checkBarrier(PlayerEntity player) {}
}
