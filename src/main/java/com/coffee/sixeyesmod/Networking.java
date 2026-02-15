package com.coffee.sixeyesmod;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import java.util.function.Supplier;

public class Networking {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(Main.MODID, "main"), () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals
    );

    public static void register() {
        INSTANCE.registerMessage(0, AbilityPacket.class, AbilityPacket::encode, AbilityPacket::decode, AbilityPacket::handle);
    }

    public static class AbilityPacket {
        private final int key; // 0 = X, 1 = B
        public AbilityPacket(int key) { this.key = key; }
        public static void encode(AbilityPacket msg, PacketBuffer buf) { buf.writeInt(msg.key); }
        public static AbilityPacket decode(PacketBuffer buf) { return new AbilityPacket(buf.readInt()); }

        public static void handle(AbilityPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    player.getCapability(ManaStorage.MANA_CAPABILITY).ifPresent(mana -> {
                        if (msg.key == 0 && mana.getMana() >= 50) { // X - Домен (цена 50)
                            AbilityHandler.executeDomain(player);
                            mana.consume(50);
                        } else if (msg.key == 1 && mana.getMana() >= 10) { // B - Бесконечность (цена 10)
                            AbilityHandler.executeInfinity(player);
                            mana.consume(10);
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
