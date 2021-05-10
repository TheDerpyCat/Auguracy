package thederpycat.auguracy.setup;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.gui.GuiEventHandler;
import thederpycat.auguracy.gui.HUDEventHandler;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Auguracy.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
    public static Optional<GuiEventHandler> elementsGui = Optional.empty();

    public static void init(final FMLClientSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(Registration.MANAFLOWER.get(), RenderType.cutout());

        elementsGui = Optional.of(new GuiEventHandler());
        DeferredWorkQueue.runLater(() ->
        {
            elementsGui.ifPresent(MinecraftForge.EVENT_BUS::register);
        });

        MinecraftForge.EVENT_BUS.addListener(HUDEventHandler::onDrawHUDPost);
    }
}
