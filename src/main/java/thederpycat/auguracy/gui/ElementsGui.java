package thederpycat.auguracy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.capabilities.elements.CapabilityElements;
import thederpycat.auguracy.capabilities.elements.IElements;

public class ElementsGui extends AbstractGui
{
    private final int WIDTH = 319;
    private final int HEIGHT = 45;

    private final GuiEventHandler elementsGui;
    private final InventoryScreen gui;

    private ResourceLocation GUI = new ResourceLocation(Auguracy.MODID, "textures/gui/elements_gui.png");

    Minecraft minecraft = Minecraft.getInstance();

    public ElementsGui(GuiEventHandler elementsGui, InventoryScreen gui)
    {
        this.elementsGui = elementsGui;
        this.gui = gui;
    }


    public void renderElementLevels(MatrixStack matrixStack)
    {
        PlayerEntity player = minecraft.player;
        int relX = (minecraft.screen.width - WIDTH) / 2;
        int relY = (minecraft.screen.height  - HEIGHT) / 2;

        //draws texture
        minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack, WIDTH, HEIGHT, 0, 0, relX, relY);

        IElements elements = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);

        //renders level for each element
        int offset = 24;
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        for(int i = 0; i < elements.getAllLevels().length; i++)
        {
            int level = elements.getLevel(i);
            String str = "" + level;
            int width = (screenWidth + offset - this.getFont().width(str)) / 2;
            int height = screenHeight - 268;

            getFont().draw(matrixStack, str, (float)(width + 1), (float)height, 0);
            getFont().draw(matrixStack, str, (float)(width - 1), (float)height, 0);
            getFont().draw(matrixStack, str, (float)width, (float)(height + 1), 0);
            getFont().draw(matrixStack, str, (float)width, (float)(height - 1), 0);
            getFont().draw(matrixStack, str, (float)width, (float)height, 16777215);
            offset += 32;
        }
    }

    private FontRenderer getFont()
    {
        return minecraft.font;
    }
}
