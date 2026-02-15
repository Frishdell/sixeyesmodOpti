package com.coffee.sixeyesmod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import java.util.List;

public class AbilityHandler {
    public static Vector3d domainCenter = null;
    public static final double DOMAIN_RADIUS = 15.0;

    // Кнопка X - Расширение территории
    public static void executeDomain(PlayerEntity player) {
        domainCenter = player.getPositionVec(); // Фиксируем центр купола
        
        AxisAlignedBB area = player.getBoundingBox().grow(DOMAIN_RADIUS);
        player.world.getEntitiesWithinAABB(LivingEntity.class, area).forEach(entity -> {
            if (entity != player) {
                entity.setNoAI(true); // Замораживаем мобов
                entity.setGlowing(true); // Подсвечиваем их
            }
        });

        player.sendStatusMessage(new StringTextComponent("Расширение территории: Необъятная пустота!")
                .mergeStyle(TextFormatting.DARK_PURPLE, TextFormatting.BOLD), true);
    }

    // Кнопка B - Бесконечность
    public static void executeInfinity(PlayerEntity player) {
        double r = 3.0;
        AxisAlignedBB area = player.getBoundingBox().grow(r);
        player.world.getEntitiesWithinAABB(LivingEntity.class, area).forEach(entity -> {
            if (entity != player) {
                Vector3d push = entity.getPositionVec().subtract(player.getPositionVec()).normalize().scale(1.2);
                entity.setMotion(push.x, 0.3, push.z);
            }
        });
    }

    // Логика непробиваемого барьера
    public static void checkBarrier(PlayerEntity player) {
        if (domainCenter == null) return;

        AxisAlignedBB checkArea = new AxisAlignedBB(domainCenter.subtract(20, 20, 20), domainCenter.add(20, 20, 20));
        List<LivingEntity> entities = player.world.getEntitiesWithinAABB(LivingEntity.class, checkArea);

        for (LivingEntity entity : entities) {
            double dist = entity.getPositionVec().distanceTo(domainCenter);
            // Если кто-то коснулся границы 15 блоков - толкаем внутрь
            if (dist > DOMAIN_RADIUS) {
                Vector3d toCenter = domainCenter.subtract(entity.getPositionVec()).normalize().scale(0.8);
                entity.setMotion(toCenter.x, 0.2, toCenter.z);
                entity.velocityChanged = true;
            }
        }
    }
}

