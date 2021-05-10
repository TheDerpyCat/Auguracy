package thederpycat.auguracy.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.api.events.ManaUpdateEvent;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.capabilities.mana.IMana;
import thederpycat.auguracy.capabilities.mana.ManaProvider;
import thederpycat.auguracy.network.PacketChannel;
import thederpycat.auguracy.network.packets.SyncManaPacket;

public class ManaCapabilityHandler
{
    public static final ResourceLocation MANA_CAPABILITY = new ResourceLocation(Auguracy.MODID, "mana");

    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        ManaProvider manaProvider = new ManaProvider();
        event.addCapability(MANA_CAPABILITY, manaProvider);
        event.addListener(manaProvider::invalidate);
    }

    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        if(!player.level.isClientSide && player.tickCount % 10 == 0)
        {
            IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
            if(manaCap.getMana() < manaCap.getMaxMana())
            {
                manaCap.setMana(manaCap.getMana() + 1, player);
            }

        }
    }
    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        PlayerEntity player = event.getPlayer();
        IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
        IMana oldManaCap = event.getOriginal().getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
        manaCap.setMana(oldManaCap.getMana(), event.getPlayer());
        manaCap.setMaxMana(oldManaCap.getMaxMana(), event.getPlayer());
    }

    public static void onPlayerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {
            PlayerEntity player = event.getPlayer();
            IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new SyncManaPacket(manaCap.getMana(), manaCap.getMaxMana()), sPlayer);

        }
    }

    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {
            PlayerEntity player = event.getPlayer();
            IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new SyncManaPacket(manaCap.getMana(), manaCap.getMaxMana()), sPlayer);
        }
    }

    public static void onPlayerConnectEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {

            PlayerEntity player = event.getPlayer();
            IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
            if(manaCap.getMana() == 0)
            {
                manaCap.setMana(manaCap.getMaxMana(), event.getPlayer());
            }
            PacketChannel.sendToClient(new SyncManaPacket(manaCap.getMana(), manaCap.getMaxMana()), sPlayer);
        }
    }

    public static void onManaUpdateEvent(ManaUpdateEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if(!player.level.isClientSide)
        {
            IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new SyncManaPacket(manaCap.getMana(), manaCap.getMaxMana()), (ServerPlayerEntity) player);
        }
    }
}
