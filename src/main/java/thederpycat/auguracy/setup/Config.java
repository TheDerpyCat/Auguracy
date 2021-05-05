package thederpycat.auguracy.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config
{
    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_SERVER = "server";

    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;



    static
    {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();

        SERVER_BUILDER.comment("Server settings").push(CATEGORY_SERVER);
        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}
