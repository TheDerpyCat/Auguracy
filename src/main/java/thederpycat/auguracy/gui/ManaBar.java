package thederpycat.auguracy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.capabilities.mana.IMana;
import thederpycat.auguracy.capabilities.mana.Mana;

public class ManaBar
{
    private static final Logger LOGGER = LogManager.getLogger(Auguracy.MODID);
    public static final ResourceLocation manaBar = new ResourceLocation(Auguracy.MODID, "textures/gui/mana_hud.png");
    private static final int WIDTH = 450;
    private static final int HEIGHT = 310;

    public static void renderManaBar(MatrixStack matrixStack)
    {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity player = minecraft.player;

        minecraft.textureManager.bind(manaBar);
        AbstractGui.blit(matrixStack, WIDTH, HEIGHT, 0, 0, 71, 17, 256, 256);

        IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(new Mana());
        int maxMana = manaCap.getMaxMana();
        int currMana = manaCap.getMana();
        String str1 = "" + currMana;
        String str2 = "" + maxMana;

        int width = 67;
        width *= (double) currMana / (double) maxMana;

        AbstractGui.blit(matrixStack, WIDTH + 2, HEIGHT + 2, 0, 18, width, 30, 256, 256);
        AbstractGui.drawString(matrixStack, minecraft.font, str1 + "/" + str2, 471, 315, 16777215);
    }

}
