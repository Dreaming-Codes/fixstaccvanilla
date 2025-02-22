package codes.dreaming.fixstaccvanilla;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;


public class FixStaccClient implements ClientModInitializer {
    public static boolean IsStacced = true;

    public static final String MOD_ID = "fixstacc";

    public static final Identifier ANNOUNCE_STACC_PACKET_ID = new Identifier(MOD_ID, "announce");

    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ANNOUNCE_STACC_PACKET_ID, FixStaccClient::onAnnounceStacc);
    }

    private static void onAnnounceStacc(MinecraftClient minecraftClient, ClientPlayNetworkHandler clientPlayNetworkHandler, PacketByteBuf packetByteBuf, PacketSender packetSender) {
        byte isStacced = packetByteBuf.getByte(0);

        IsStacced = isStacced == (byte) 1;
    }
}
