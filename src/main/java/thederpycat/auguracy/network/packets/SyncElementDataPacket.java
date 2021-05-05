package thederpycat.auguracy.network.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import thederpycat.auguracy.capabilities.elements.CapabilityElements;
import thederpycat.auguracy.capabilities.elements.IElements;

import java.util.function.Supplier;

public class SyncElementDataPacket
{
    private final int[] levels;
    private final int[] xp;

    public SyncElementDataPacket(int[] levels, int[] xp)
    {
        this.levels = levels;
        this.xp = xp;
    }

    public static SyncElementDataPacket decode(PacketBuffer buffer)
    {
        return new SyncElementDataPacket(buffer.readVarIntArray(), buffer.readVarIntArray());
    }

    public static void encode(SyncElementDataPacket message, PacketBuffer buffer)
    {
        buffer.writeVarIntArray(message.levels);
        buffer.writeVarIntArray(message.xp);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context)
    {
        if(context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT)
        {
           PlayerEntity player = context.get().getSender();
            context.get().enqueueWork(() ->
            {
              IElements elements = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
              elements.setAllLevels(levels);
              elements.setAllXP(xp);
            });
        }

        return true;
    }
}
