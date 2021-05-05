package thederpycat.auguracy.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.network.packets.LevelUpMessagePacket;
import thederpycat.auguracy.network.packets.SyncElementDataPacket;

public class PacketChannel
{
    private static final String COMMS_VERSION = "1";

    private static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Auguracy.MODID, "auguracy"),
            () -> COMMS_VERSION,
            s -> true,
            s -> true);

    private static int id = 0;

    public static void registerPackets()
    {
        INSTANCE.registerMessage(id++, SyncElementDataPacket.class, SyncElementDataPacket::encode, SyncElementDataPacket::decode, SyncElementDataPacket::handle);
        INSTANCE.registerMessage(id++, LevelUpMessagePacket.class, LevelUpMessagePacket::encode, LevelUpMessagePacket::decode, LevelUpMessagePacket::handle);
    }


    //sends packet to a player, called from server side
    public static void sendToClient(Object packet, ServerPlayerEntity player)
    {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    //sends packet to the server, called from client side
    public static void sendToServer(Object packet)
    {
        INSTANCE.sendToServer(packet);
    }

}
