package thederpycat.auguracy.network.packets;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.capabilities.mana.IMana;

import java.util.function.Supplier;

public class SyncManaPacket
{
    private final int mana;

    public SyncManaPacket(int mana)
    {
        this.mana = mana;
    }

    public static SyncManaPacket decode(PacketBuffer buffer)
    {
        return new SyncManaPacket(buffer.readVarInt());
    }

    public static void encode(SyncManaPacket message, PacketBuffer buffer)
    {
        buffer.writeVarInt(message.mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context)
    {
        if(context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT)
        {
            PlayerEntity player = context.get().getSender();
            context.get().enqueueWork(() ->
            {
               IMana mana = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
               mana.setMana(this.mana);
            });
        }
        return true;
    }
}
