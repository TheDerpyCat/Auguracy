package thederpycat.auguracy.capabilities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.api.events.LevelUpEvent;
import thederpycat.auguracy.capabilities.elements.CapabilityElements;
import thederpycat.auguracy.capabilities.elements.ElementsProvider;
import thederpycat.auguracy.capabilities.elements.IElements;
import thederpycat.auguracy.network.PacketChannel;
import thederpycat.auguracy.network.packets.LevelUpMessagePacket;
import thederpycat.auguracy.network.packets.SyncElementDataPacket;

public class ElementsCapabilityHandler
{
    public static final ResourceLocation ELEMENTS_CAPABILITY = new ResourceLocation(Auguracy.MODID, "elements");

    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

            ElementsProvider provider = new ElementsProvider();
            event.addCapability(ELEMENTS_CAPABILITY, provider);
            event.addListener(provider::invalidate);
    }


    public static void onBlockDestroyedByPlayerEvent(BlockEvent.BreakEvent event)
    {
            PlayerEntity player = event.getPlayer();
            BlockState state = event.getState();
            Block block = state.getBlock();
            {
                if(block == Blocks.DIRT || block == Blocks.GRASS_BLOCK)
                {
                    IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
                    element.addXP(0, 1, player);
                }
            }
    }

    public static void onLevelUpEvent(LevelUpEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if(!player.level.isClientSide)
        {
            IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new LevelUpMessagePacket(event.getLevel(), event.getIndex()), (ServerPlayerEntity) player);
            PacketChannel.sendToClient(new SyncElementDataPacket(element.getAllLevels(), element.getAllXP()), (ServerPlayerEntity) player);
        }
    }

    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        PlayerEntity player = event.getPlayer();
        IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
        IElements oldElement = event.getOriginal().getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
        element.setAllLevels(oldElement.getAllLevels());
        element.setAllXP(oldElement.getAllXP());
    }

    public static void onPlayerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {
            PlayerEntity player = event.getPlayer();
            IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new SyncElementDataPacket(element.getAllLevels(), element.getAllXP()), sPlayer);

        }
    }

    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {
            PlayerEntity player = event.getPlayer();
            IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
            PacketChannel.sendToClient(new SyncElementDataPacket(element.getAllLevels(), element.getAllXP()), sPlayer);

        }
    }

    public static void onPlayerConnectEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) event.getPlayer();
        if(!sPlayer.level.isClientSide)
        {
            PlayerEntity player = event.getPlayer();
            IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
            int[] lvls = element.getAllLevels();
            if(lvls == null)
            {
                int[] arr = {0, 0, 0, 0, 0};
                element.setAllLevels(arr);
                element.setAllXP(arr);
            }
            PacketChannel.sendToClient(new SyncElementDataPacket(element.getAllLevels(), element.getAllXP()), sPlayer);
        }
    }
}
