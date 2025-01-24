package codes.dreaming.fixstaccvanilla;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

import static codes.dreaming.fixstaccvanilla.FixStaccVanilla.ANNOUNCE_PACKET_ID;

public class FixStaccClient implements ClientModInitializer {
    public static boolean IsStacced = true;

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ANNOUNCE_PACKET_ID, FixStaccClient::onAnnounce);
    }

    private static void onAnnounce(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        byte isStacced = packetByteBuf.getByte(0);

        IsStacced = isStacced == (byte) 1;
    }
}
