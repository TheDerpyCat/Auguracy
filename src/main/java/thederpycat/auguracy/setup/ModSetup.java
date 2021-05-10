package thederpycat.auguracy.setup;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.capabilities.ElementsCapabilityHandler;
import thederpycat.auguracy.capabilities.ManaCapabilityHandler;
import thederpycat.auguracy.capabilities.elements.CapabilityElements;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.commands.ModCommands;
import thederpycat.auguracy.gui.GuiEventHandler;
import thederpycat.auguracy.network.PacketChannel;


@Mod.EventBusSubscriber(modid = Auguracy.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup
{

    public static final ItemGroup AUGURACY_GROUP = new ItemGroup("auguracy")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Registration.MANAFLOWER.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event)
    {
        PacketChannel.registerPackets();
        CapabilityElements.register();
        CapabilityMana.register();

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, ElementsCapabilityHandler::onAttachCapabilityEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onBlockDestroyedByPlayerEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onPlayerCloneEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onLevelUpEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onPlayerConnectEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onPlayerChangedDimensionEvent);
        MinecraftForge.EVENT_BUS.addListener(ElementsCapabilityHandler::onRespawnEvent);

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, ManaCapabilityHandler::onAttachCapabilityEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onPlayerTickEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onPlayerCloneEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onManaUpdateEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onPlayerConnectEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onPlayerChangedDimensionEvent);
        MinecraftForge.EVENT_BUS.addListener(ManaCapabilityHandler::onRespawnEvent);

        MinecraftForge.EVENT_BUS.addListener(GuiEventHandler::onDrawScreen);


    }

    @SubscribeEvent
    public static void serverLoad(RegisterCommandsEvent event)
    {
        ModCommands.register(event.getDispatcher());
    }
}
