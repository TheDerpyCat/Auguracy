package thederpycat.auguracy.network.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class LevelUpMessagePacket
{
    private final int level;
    private final int index;

    public LevelUpMessagePacket(int level, int index) {
        this.level = level;
        this.index = index;
    }

    public static LevelUpMessagePacket decode(PacketBuffer buffer)
    {
        return new LevelUpMessagePacket(buffer.readInt(), buffer.readInt());
    }

    public static void encode(LevelUpMessagePacket message, PacketBuffer buffer)
    {
        buffer.writeInt(message.level);
        buffer.writeInt(message.index);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayerEntity player = context.get().getSender();

        context.get().enqueueWork(() ->
        {
            switch(index)
            {
                case 0:
                    sendMessage(player, new TranslationTextComponent("earth.auguracy.level_up", Integer.toString(level)), player.getUUID());
                    break;
                case 1:
                    sendMessage(player, new TranslationTextComponent("fire.auguracy.level_up", Integer.toString(level)), player.getUUID());
                    break;
                case 2:
                    sendMessage(player, new TranslationTextComponent("water.auguracy.level_up", Integer.toString(level)), player.getUUID());
                    break;
                case 3:
                    sendMessage(player, new TranslationTextComponent("air.auguracy.level_up", Integer.toString(level)), player.getUUID());
                    break;
                case 4:
                    sendMessage(player, new TranslationTextComponent("corruption.auguracy.level_up", Integer.toString(level)), player.getUUID());
                    break;
            }

        });
        return true;
    }

    public static void sendMessage(ServerPlayerEntity player, ITextComponent message, UUID uuid)
    {
        player.sendMessage(message, uuid);
    }
}

