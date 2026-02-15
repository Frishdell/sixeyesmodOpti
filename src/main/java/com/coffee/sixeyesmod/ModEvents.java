package com.coffee.sixeyesmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModEvents {
    // Остальной код с маной и дропом оставь как был, просто добавь/замени этот метод:
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.player.world.isRemote) {
            AbilityHandler.checkBarrier(event.player);
        }
    }
}
