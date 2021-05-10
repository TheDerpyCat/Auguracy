package thederpycat.auguracy.network.packets;

import net.minecraft.client.Minecraft;
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
    private final int maxMana;

    public SyncManaPacket(int mana, int maxMana)
    {
        this.mana = mana;
        this.maxMana = maxMana;
    }

    public static SyncManaPacket decode(PacketBuffer buffer)
    {
        return new SyncManaPacket(buffer.readVarInt(), buffer.readVarInt());
    }

    public static void encode(SyncManaPacket message, PacketBuffer buffer)
    {
        buffer.writeVarInt(message.mana);
        buffer.writeVarInt(message.maxMana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context)
    {
        if(context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT)
        {
            PlayerEntity player = Minecraft.getInstance().player;
            context.get().enqueueWork(() ->
            {
               IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
               manaCap.setMana(mana);
               manaCap.setMaxMana(maxMana);
            });
        }
        return true;
    }
}
