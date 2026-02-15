package com.coffee.sixeyesmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "six_eyes_mod";

    public Main() {
        Registry.register(); // Предметы
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(new ModEvents()); // События
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ManaStorage.register(); // Мана
            Networking.register();  // Кнопки X и B
        });
    }
}
