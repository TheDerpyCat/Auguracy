package thederpycat.auguracy;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thederpycat.auguracy.setup.ClientSetup;
import thederpycat.auguracy.setup.ModSetup;
import thederpycat.auguracy.setup.Registration;


@Mod(Auguracy.MODID)
public class Auguracy
{
    public static final String MODID = "auguracy";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    public Auguracy()
    {
        LOGGER.debug("Auguracy says hi");


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

       // ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
       // ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        Registration.init();

        modEventBus.addListener(ModSetup::init);
        modEventBus.addListener(ClientSetup::init);
    }

}
